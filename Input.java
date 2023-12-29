
/**
 * Class Input is designed to take in all the input for the MapCreator Objects
 * Takes in the amount of rows and columns the user wishes to have within the range of 3-11 and forces them to enter a value due to error trapping 
 * Values for the length and rows are then returned as bytes 
 * Another method takes in the user input to see whether they want to go to another house, with error trapping. 
 * 
 * @author (Fayiz Khan)
 * @version (Wednesday, November 22, 2023)
 */

//importing Scanner library 
import java.util.Scanner; 

public class Input
{    
    //This method uses user input and then returns the number of rows between 3-11 that the user has chosen 
    public static byte getAmount (String prompt)

    {
        //variable bytValue of type byte to store the amount that the user inputs
        byte bytValue = 0;
        //variable bolTryCatch of type boolean to error trap 
        boolean bolTryCatch; 

        //do while loop that runs until the user enters a value between 3 and 11 
        do {
            //try block 
            try{
                //prompting the user 
                System.out.println(prompt); 
                
                //taking in input and storing it in bytValue
                bytValue = new Scanner (System.in).nextByte(); 
                bolTryCatch = true; 
                
                //if condition to make sure that the value is not less than 3 
                if (bytValue < 3)
                
                {
                    //sets boolean to false so it will loop
                    bolTryCatch = false; 
                    //alerts user of their mistake 
                    System.out.println("You need to enter a value of at least 3!"); 
                
                }
                
                //condition to make sure that the value is not greater than 11 
                else if (bytValue > 11)
                {
                    //sets boolean to false so it will loop
                    bolTryCatch = false; 
                    //alerts user of their mistake 
                    System.out.println("You can't enter a value greater than 11!"); 
                
                }
            }

            //catch block for if they didn't enter a valid byte 
            catch (Exception e)

            {
                //set boolean to false so it will loop
                bolTryCatch = false; 
                System.out.println("Invalid input. Enter a number between 3 and 11"); 
                
            }
            //condition for do while loop 
        } while(bolTryCatch == false); 

        //returning the value of bytRow 
        return bytValue; 
    }

    
    //This method is to prompt the user to see if they would like to go to another house 
    //it then returns a boolean that symbolizes their choice 
    public static boolean anotherMap()
    {
        //variable strAnswer of type String is used to hold the user's response of yes or no 
        String strAnswer;
        //variable bolAnotherMap of type boolean will be returned to represent true or false depending on the choice of the user 
        boolean bolAnotherMap = true;
        //variable bolErrorTrap of type boolean for error trapping purposes 
        boolean bolErrorTrap;

        //do while loop that runs until the user inputs yes or no (while bolErrorTrap == false)
        do
        {
            //prompting the user 
            System.out.println("Would you like to go to another house? Type yes or no: ");
            //taking in input and storing it in strAnswer 
            strAnswer = new Scanner(System.in).nextLine();
            bolErrorTrap = true;
            
            //returning true if user entered yes (not case sensitive)
            if (strAnswer.equalsIgnoreCase("Yes"))
            {
                return bolAnotherMap = true;
            }
            //returning false if user entered no (not case sensitive)
            else if (strAnswer.equalsIgnoreCase("No"))
            {
                return bolAnotherMap = false;
            }
            else 
            {
                //error trapping and letting the user know what their mistake was
                System.out.println("You did not pick a valid option: ");
                //setting bolErrorTrap to false ensuring that the loop does not exit and that the user is prompted again 
                bolErrorTrap = false;
            }
        }
        //condition of do while loop
        while(bolErrorTrap == false);

        //returning bolAnotherMap 
        return bolAnotherMap;

    }
}


