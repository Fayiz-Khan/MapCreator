
/**
 * MapReader class takes the files being created by the MapCreator class and reads them to search for where an 'S' appears in the file 
 * Proceeds to then output the indexes where 'S' is found 
 *
 * @author (Fayiz Khan)
 * @version (Wednesday, November 22, 2023)
 */

//importing libraries 
import java.io.*;
import java.util.*; 

public class MapReader 
{
    //method reads the file after being passed the file name as a parameter to then indicate at what indexes a sock is found at 
    public static void readMap(String strFileName)
    {
        //try block 
        try 
        {
            //String to hold the line of the file 
            String strLine = "";
            //variable bytRow of type byte will indicate which row the sock was found at 
            byte bytRow = 0;
            
            //Creating scanner 
            Scanner scanner = new Scanner(new FileReader(strFileName));
            
            //outputting which file is being read
            System.out.println("\nFor " + strFileName + ":"); 
            
            //if statement checking to see whether the file is empty to begin with 
            if (!scanner.hasNextLine())
            {
                System.out.println("Error: File is empty");
            }
            
            //while loop will go and read every line of the file and then stop when there are no more lines to be read 
            while (scanner.hasNextLine())
            {
                //setting strLine to the next line of the file 
                strLine = scanner.nextLine();

                //iterating through loop 
                for (byte bytCol = 0; bytCol < strLine.length(); bytCol++)
                {
                    //checking to see if an 'S' appears then outputting the index of it when found
                    if (strLine.charAt(bytCol) == 'S') {
                        System.out.println("Sock found at: [" + bytRow + "][" + bytCol + "]");
                    }
                }
    
                //incrementing row 
                bytRow++;
            }
            
            //closing scanner 
            scanner.close();
            System.out.println(); 
            
            //catch blocks to error catch 
        }  catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file for reading");
        } catch (NoSuchElementException e) {
            System.out.println("Error: EOF encountered, file may be corrupt");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from file");
        }
    }
}

    
