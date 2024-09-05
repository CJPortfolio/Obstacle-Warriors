package ObstaclesWarrior;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ObstaclesWarrior
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        String filePath, executeOption;
        Position startPosition = new Position(-1, -1);
        Position exitPosition  = new Position(-2, -2);


        filePath = GetValidFilePath();
        ReadBoardFromFile(filePath, startPosition, exitPosition);





        // System.out.println("Type \"Start\" to start the game or \"Exit\" to exit the game: " );
        // executeOption = scnr.nextLine();

    
    }

    public static String GetValidFilePath() 
    {
        Scanner scnr = new Scanner(System.in);
        FileInputStream fileInputStream = null;
        boolean validFilePath = false;
        String filePath;
        do
        {   
            System.out.println("Enter the board data file path");
            filePath = scnr.nextLine();

            try {
                // Open the input file
                fileInputStream = new FileInputStream(filePath);
            } catch (FileNotFoundException ex) {
                validFilePath = false;
            }
        }
        while(!validFilePath);

        scnr.close();
        
        return filePath;
    }

    // Takes in a string in the format "x y" and returns a position object with x y coordinates 
    public static Position PositionFromString(String str) 
    {   
        Scanner scanner = new Scanner(str);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Position(x, y);
    }
    
    public static String[][] ReadBoardFromFile(String fileName, 
                                               Position startPosition, 
                                               Position exitPosition)
    {
        String[][] gameBoard = null; 
        Scanner fileScanner = null;
        String fileLineString;

        //Handle the exception just in case for some reason the valid fileName fails to open
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            fileScanner = new Scanner(fileInputStream);
        } catch (Exception e) {
            //Assume that the fileName passed to this function is valid and this will never fail
            System.out.println("There was a problem opening the file for some reason");
        }
        
        //Get the board dimensions from the file and create the gameboard array
        Position dims = PositionFromString(fileScanner.nextLine());
        gameBoard = new String[dims.getX()][dims.getY()];

        //Get the start and exit positions from the file
        startPosition = PositionFromString(fileScanner.nextLine());
        exitPosition  = PositionFromString(fileScanner.nextLine());

        //TODO Read the rest of the board from the file into the gameBoard array
        while (fileScanner.hasNext()) {
            fileLineString = fileScanner.nextLine();
        }

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
