/**
 * Class MapCreator creates a text file with a certain amount of 'F's and 'S''s depending on the parameters being sent 
 * Class also allows for user to call functions in order to receive their input and populate the number of rows and columns based on the user's liking
 * 
 * @author (Fayiz Khan)
 * @version (Wednesday, November 22, 2023)
 */

//importing libraries 
import java.io.*;
import java.util.Random;

public class MapCreator
 {

    //bytMapCounter is a static variable of type byte to serve as a counter and increment the file number by 1
    static byte bytMapCounter = 0;
    //static and constant variable of type float with value 1/6 which will be used to maintain an approximate 1:6 ratio of socks : total amount of socks and floor
    static final float RATIO_OF_SIX = 1/6f;
    //static variable and constant to represent the default size of a row. Is used if the user hardcodes and passes a byte less than 3 or greater than 11
    //in the test class 
    static final byte ROW_SIZE = 6;
    //static variable and constant to represent the default size of a column. Is used if the user hardcodes and passes a byte less than 3 or greater than 11
    //in the test class
    static final byte COLUMN_SIZE = 10;
    //default value of 10 socks that goes along with the default size and row mentioned above 
    static final byte NUM_SOCKS = 10;

    //2D array of type char to hold 'S' or 'F'
    char[][] chrGrid;
    
    //variable of type byte that represents the number of socks that needs be generated
    byte bytNumSocks;
    
    //used for when there are no parameters being passed into the constructor and the user must be prompted for input 
    //these variables of type byte store the amount of rows and columns the users wishes to generate 
    byte bytRow, bytColumn; 

    //constructor to initialize the values for the rows and columns
    //initialize the number of socks and the grid 
    public MapCreator ()
    {
        this.bytRow = Input.getAmount("Enter the length of the row (max value is 11 and minimum value is 3): "); 
        this.bytColumn = Input.getAmount("Enter the length of the column (max value is 11 and minimum value is 3): ");
        this.bytNumSocks = calculateSocks(this.bytRow, this.bytColumn);
        this.chrGrid = new char [this.bytRow][this.bytColumn];
        
        //calling randomizeSocks method to randomize the placement of socks in the grid
        randomizeSocks(this.bytNumSocks);
        //calling readMap function to read the map 
        MapReader.readMap(printMapToFile()); 

    }


    //constructor with parameters if the user chooses to hardcode values in the test class 
    public MapCreator(byte rows, byte columns)
    {
        //chose to code it in this is and else format for clarity and maintainability purposes
        //this way default values are at a centralized location and can be easily modified if needed 
        //so,it more accessible to easily make changes to the default values if it is necessary in the future 
        
        //if amount of rows or columns is not in range, creates a default 6 x 10 grid 
        if (rows < 3 || columns <3 || rows > 11 || columns > 11)
        {
            this.chrGrid = new char[ROW_SIZE][COLUMN_SIZE];
            this.bytNumSocks = NUM_SOCKS;
            invalidParameters();
        }
        
        //if parameters passed into the array are withing range, creates a grid of the user's choice 
        else 
        {
            this.chrGrid = new char[rows][columns];
            this.bytNumSocks = calculateSocks(rows,columns);
            
        }

        //calling randomizeSocks method to randomize the placement of socks in the grid
        randomizeSocks(this.bytNumSocks);
        //calling readMap function to read the map 
        MapReader.readMap(printMapToFile()); 
    }

    //recursive method to randomize the placement of socks in the grid
    //parameter numSocks is to tell the method how many socks it needs to generate  
    public void randomizeSocks(byte numSocks) 
    {
        //creating random x and y values for rows and columns 
        Random rand = new Random();
        byte bytRandomX = (byte) rand.nextInt(this.chrGrid.length);
        byte bytRandomY = (byte) rand.nextInt(this.chrGrid[0].length);

        //base case 
        //when numSocks is equal to 0, the rest of the grid is populated with 'F' 
        if (numSocks == 0) 
        {
            //calling the generateFloor method when base case is reached 
            generateFloor();
            return ;
        }

        //checks to see if an 'S' has been previously generated. If there is no existing 'S' at that relevant index, then it fills it with one
        if (this.chrGrid[bytRandomX][bytRandomY] != 'S') 
        {
            this.chrGrid[bytRandomX][bytRandomY] = 'S';
            randomizeSocks(--numSocks);
        } 
        else 
        {
            //if 'S' already exists at that index, it calls the method again without subtracting 1 from numSocks 
            randomizeSocks(numSocks);
        }
    }

    //method generateFloor() creates an 'F' wherever an 'S' doesn't exist 
    public void generateFloor() 
    {
        for (int i = 0; i < this.chrGrid.length; i++) 
        {
            for (int j = 0; j < this.chrGrid[0].length; j++) 
            {
                if (this.chrGrid[i][j] != 'S') {
                    this.chrGrid[i][j] = 'F';
                }
            }
        }
    }

    //method printMapToFile to create a file with the specifications of variable chrGrid
    //returns the name of the file so that it can be passed into the MapReader later 
    public String printMapToFile()
    {
        //variable strFileName of type String to hold the name of the file 
        String strFileName = "";

        //try block 
        try 
        {
            //creating print writer object 
            //increments bytMapCounter by 1 whenever new file needs to be created
            PrintWriter out = new PrintWriter(new FileWriter("map" + (++bytMapCounter) + ".txt"));

            //iterates through a for each loop and printing each index 
            for (char[] row : this.chrGrid) 
            {
                for (char cell : row)
                {
                    out.print(cell);
                }
                out.println();
            }

            //outputting where the map was saved to 
            System.out.println("Map saved to map" + bytMapCounter + ".txt");
            
            //closing 
            out.close();

            //setting strFileName to the name of the file created
            strFileName = "map" + bytMapCounter + ".txt";

            //catch blocks for error catching 
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file for writing");
        } catch (IOException e) {
            System.out.println("Error: Cannot write to file");
        }

        //retunrning strFileName which holds the name of the file 
        return strFileName;
    }

    //static void method that is called in the constructor if incorrect values are hardcoded 
    public static void invalidParameters() 
    {
        System.out.println("Invalid parameters were passed through. Rows and columns value has been set to the default 6 x 10 grid");
    }
    
    //static method that returns a byte to calculate the number of socks needs to be generated 
    //approx ratio of 1:6 and rounds up 
    public static byte calculateSocks (byte rows, byte columns)
    {
        return (byte) Math.ceil((rows * columns) * RATIO_OF_SIX); 

    }
}
