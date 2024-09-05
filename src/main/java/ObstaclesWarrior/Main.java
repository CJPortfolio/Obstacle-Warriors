package ObstaclesWarrior;

import java.util.Scanner;

/**
 * ObstaclesWarrior
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        // Prompt user to enter file path for board data
        Scanner scnr = new Scanner(System.in);
        boolean readData;
        string filePath, executeOption;
        do
        {   
            System.out.print("Enter the board data file path");
            filePath = scanner.nextLine();
            System.out.print("Type \"Start\" to start the game or \"Exit\" to exit the game: " );
            executeOption = scanner.nextLine();

        }
        while(!readData);
    }
    
    public static String[][] ReadBoardFromFile(String fileName, 
                                               Position startPosition, 
                                               Position exitPosition)
    {
        //Assume that the data below is read from a file with your actual implementation.
        //You don't need to write anything in your methods here to be able to write your
        //unit test methods.This code is added just to enable you to run the provided unit test. 

        String[][] gameBoard =  {
                                    {"0", "#", "#", "#"},
                                    {"#", "-3", "#", "-5"},
                                    {"#", "#", "#", "#"},
                                    {"#", "#", "-1", "#"},
                                }; 


        startPosition.setX(0);
        startPosition.setY(2);
        exitPosition.setX(2);
        exitPosition.setY(2);

        return gameBoard;
    } 
    
    public static boolean WriteBoardToFile(String fileName, 
                                           String[][] boardArray)
    {
        
        return true;
    } 
    
    public static int GenerateDirection()
    {

        return 0;
    } 
    
    public static Position MoveWarrior(int direction, 
                                       String[][] boardArray, 
                                       Position currentPosition)
    {
        return new Position(0, 0);
    } 
    
    public static int CalculateWarriorScore(int currentScore, 
                                            Position currentPosition, 
                                            String[][] boardArray)
    {
        return 0; 
    } 
    
    public static void DisplayResults(int currentScore, 
                                      int numberOfMoves, 
                                      int timeElapsed ) 
    {

    }  
}
