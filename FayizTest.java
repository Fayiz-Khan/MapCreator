
/**
 * Test class creates MapCreator objects and adds them into an array list 
 * User has the option to continue going to more houses and hiding socks until they wish to stop 
 * Methods implemented to take information from the array list and compile statistics such as total amount of socks hidden and average socks per map 
 * Output in terminal indicates where the map is being stored in a file and at what indexes a sock was found 
 *
 * @author (Fayiz Khan)
 * @version (Wednesday, November 22, 2023)
 */
//importing ArrayList library 
import java.util.ArrayList; 
public class FayizTest 
{
    public static void main(String[] args) 
    {
        //ArrayList of type MapCreator to store the maps being created 
        ArrayList <MapCreator> maps = new ArrayList <MapCreator> (); 

        //this is to demonstrate how my code accounts for different cases and how I thought about future scalability and functionality 
                
        //This shows how you can hardcode values and pass these parameters into map2 with no issues 
        MapCreator map2 = new MapCreator((byte) 5, (byte) 3);
        
        //If no parameters are given, then as can be observed in this case, map3 will automatically call for input 
        MapCreator map3 = new MapCreator ();

        //adding these objects to the array list 
        maps.add(map2); 
        maps.add(map3); 
        
        //fun output to the user 
        System.out.println("Let's go hide some socks!"); 
        //do while loop that runs until the user decides to say no when prompted to in the anotherMap method that belongs to the input class 
        do{
            //Adding the object to the array list 
            maps.add(new MapCreator ()); 

        }while(Input.anotherMap() == true); 
        
        //If incorrect values are passed through in the test class directly like in this instance with -3,
        //code accounts for this and sets MapCreator to a default 6 x 10 grid which can be observed once you run the test class 
        maps.add(new MapCreator((byte) -3, (byte) 4)); 
        
        //displays statistics of the map 
        displayMapStatistics(maps);
    }

    //void method that calculates a range of statistics about the maps and then outputs them 
    public static void displayMapStatistics(ArrayList<MapCreator> maps) 
    {
        //variable shrTotalSocks of type short to store the total amount of socks 
        short shrTotalSocks = 0;
        //variable shrTotalRows of type short to store the total amount of rows 
        short shrTotalRows = 0;
        //variable shrTotalColumns of type short to store the total amount of columns 
        short shrTotalColumns = 0;

        //variable fltAverageSocks of type float to store the average amount of socks per map 
        float fltAverageSocks; 
        //variable fltAverageRows of type float to store the average amount of rows per map 
        float fltAverageRows; 
        //variable fltAverageColumns of type float to store the average amount of columns per map 
        float fltAverageColumns; 

        //checks to see if array list is empty 
        //if it is empty, it prints out a message and exits the method 
        if (maps.isEmpty()) 
        {
            System.out.println("No maps available for analysis.");
            return;
        }

        //iterating through the array list 
        for (MapCreator map : maps)
        {
            //counting the total amount of socks by calling in the countSocks method and passing map as a parameter 
            shrTotalSocks += countSocks(map);
            //counting the total amount of rows 
            shrTotalRows += map.chrGrid.length;
            //counting the total amound of columns 
            shrTotalColumns += map.chrGrid[0].length;
        }

        //calculating the average amount of socks per map 
        fltAverageSocks = (float) shrTotalSocks / maps.size();
        //calculating the average amount of rows per map 
        fltAverageRows = (float) shrTotalRows / maps.size();
        //calculating the average amount of columns per map 
        fltAverageColumns = (float) shrTotalColumns / maps.size();

        //outputting the results 
        System.out.println(); 
        System.out.println(); 
        System.out.println("Statistics for the maps with averages being to two decimal places:");
        System.out.println("Total Houses Explored: " + maps.size());
        System.out.println("Total Socks: " + shrTotalSocks);
        System.out.println("Total Rows: " + shrTotalRows);
        System.out.println("Total Columns: " + shrTotalColumns);

        //using a printf to round to two decimal places 
        System.out.printf("Average Socks per Map: %.2f\n", fltAverageSocks);
        System.out.printf("Average Rows per Map: %.2f\n", fltAverageRows);
        System.out.printf("Average Columns per Map: %.2f\n", fltAverageColumns);
    }

    //static method counts the amount of types 'S' appears to know the total count for socks in the array list 
    public static short countSocks(MapCreator map)
    {
        //short shrSockCount to count the # of socks 
        short shrSockCount = 0;
        //iterating through the array list 
        for (char[] row : map.chrGrid)
        {
            for (char cell : row) 
            {
                if (cell == 'S') 
                {
                    //incrementing if 'S' is found 
                    shrSockCount++;
                }
            }
        }
        //returning the count 
        return shrSockCount;
    }
}

