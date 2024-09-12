// Name: Christopher Wiratman
// Net ID: cjw220005

package ObstaclesWarrior;

import java.io.File;
import java.util.Scanner;

/**
 * ObstaclesWarrior
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        String filePath, systemOption;
        Position startPosition = new Position(-1, -1);
        Position exitPosition  = new Position(-2, -2);
        
        // Prompt user for Board.dat file path;




        Scanner scnr = new Scanner(System.in);


        

        // FileInputStream fileStream
        File file;
        do
        {
            System.out.println("Enter the board data file path: ");

            filePath = scnr.nextLine();
            file = new File(filePath);

            if(!file.exists())
            {
                System.out.println("Invalid File");
            }
        }
        while(!file.exists());

        ReadBoardFromFile(filePath, startPosition, exitPosition);
        

        while (true) {
            System.out.print("Type \"Start\" to start the game or \"Exit\" to exit the game: " );

            systemOption = scnr.next();

            if(systemOption.equals("Start"))
            {
                break;
            }
            else if (systemOption.equals("Exit"));
            {
                System.exit(0);
            }

            System.out.println("Invalid Choice");
        }
        /*do
        {
        System.out.println("Enter the board file path: ");
        filePath = scnr.next();
        System.out.print("Type \"Start\" to start the game or \"Exit\" to exit the game: ");
        systemOption = scnr.next();
        if(systemOption.equals("Start"))
        {
        }
        else if (systemOption.equals("Exit"))
        {
        System.exit(0);
        }
        else
        {
        System.err.println("Input Error");
        }
        }
        while()
        File boardFile = new File(filePath);
        try
        {
        try(Scanner boardScanner = new Scanner(boardFile))
        {
        boardScanner.next();
        }
        }
        catch(Exception e)
        {
        System.err.println("Error with file");
        }
        // System.out.println("Type \"Start\" to start the game or \"Exit\" to exit the game: " );
        // executeOption = scnr.nextLine();
        */

        scnr.close();
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
        
        /* 
        int rows, columns;
        
        File boardFile = new File(fileName);
        Scanner boardScanner;
        try
        {
            boardScanner = new Scanner(boardFile);
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found");
            return null;
        }


        Scanner lineScanner = new Scanner(boardScanner.nextLine());
        lineScanner.useDelimiter(" ");

        rows = lineScanner.nextInt();
        columns = lineScanner.nextInt();

        lineScanner = new Scanner(boardScanner.nextLine());
    
        startPosition.setY(lineScanner.nextInt());
        startPosition.setX(lineScanner.nextInt());

        lineScanner = new Scanner(boardScanner.nextLine());

        exitPosition.setY(lineScanner.nextInt());
        exitPosition.setX(lineScanner.nextInt());

        String[][] gameBoard = new String[rows][columns];

        for(int i = 0; i < rows; ++i)
        {
            lineScanner = new Scanner(boardScanner.nextLine());
            for(int j = 0; j < columns; ++j)
            {
                if(lineScanner.hasNext()) {
                    gameBoard[i][j] = lineScanner.next();
                }
            }
            
        }
        lineScanner.close();
        boardScanner.close();

        
        */
        return gameBoard;
    } 
    
    public static boolean WriteBoardToFile(String fileName, 
                                           String[][] boardArray)
    {
        
        return true;
    } 
    
    public static int GenerateDirection()
    {
        //return a random number from 1-7 (inclusive)
        return 3;
    } 
    
    public static Position MoveWarrior(int direction, 
                                       String[][] boardArray, 
                                       Position currentPosition)
    {
        //0-UP, 1-DOWN, 2-LEFT, 3-RIGHT, 4-UPRIGHT, 5-DOWNRIGHT, 6-UPLEFT, 7-DOWNLEFT
        Position newPosition;
        switch (direction) {
            case 0: //UP
                newPosition = new Position(currentPosition.getX()+0, currentPosition.getY()-1);
                break;
            case 1: //DOWN
                newPosition = new Position(currentPosition.getX()+0, currentPosition.getY()+1);
                break;
            case 2: //LEFT
                newPosition = new Position(currentPosition.getX()-1, currentPosition.getY()+0);
                break;
            case 3: //RIGHT
                newPosition = new Position(currentPosition.getX()+1, currentPosition.getY()+0);
                break;
            case 4: //UPRIGHT
                newPosition = new Position(currentPosition.getX()+1, currentPosition.getY()-1);
                break;
            case 5: //DOWNRIGHT
                newPosition = new Position(currentPosition.getX()+1, currentPosition.getY()+1);
                break;
            case 6: //UPLEFT
                newPosition = new Position(currentPosition.getX()-1, currentPosition.getY()-1);
                break;
            case 7: //DOWNLEFT
                newPosition = new Position(currentPosition.getX()+1, currentPosition.getY()+1);
                break;
            default:
                return new Position(-1, -1);
        }


        //Check if the new position is outside the board, if so, return the original position
        if(newPosition.getX() < 0 || newPosition.getY() < 0 || 
           newPosition.getY() >= boardArray.length || newPosition.getX() >= boardArray[0].length) {
            return currentPosition;
        }

        //Check if the new position is on a 0, if so return to start position and replace the 0 with a #
        if(boardArray[newPosition.getY()][newPosition.getX()].equals("0")) {
            boardArray[newPosition.getY()][newPosition.getX()] = "#";
            newPosition = new Position(3, 0); //TODO
        }

        return newPosition;
    } 
    
    public static int CalculateWarriorScore(int currentScore, 
                                            Position currentPosition, 
                                            String[][] boardArray)
    {
        
        currentScore = 200;

        int calculatedScore = currentScore;

        

        /* 
        int healthModifier = 1;
        String currentSpotStr = boardArray[currentPosition.getY()][currentPosition.getX()];
        boolean spotWasAnObstacle = false;

        try (Scanner scnr = new Scanner(currentSpotStr))
        {
            healthModifier = scnr.nextInt();
            scnr.close();
        } catch (Exception e) {
            // The thing at the current board position was not an integer
            spotWasAnObstacle = true;
        }

        if(spotWasAnObstacle) {
            //change the current spot on the board to not be an obstacle
            boardArray[currentPosition.getY()][currentPosition.getX()] = "#";
        }

        return currentScore + healthModifier;
        */

        return calculatedScore;
    } 
    
    public static void DisplayResults(int currentScore, 
                                      int numberOfMoves, 
                                      int timeElapsed) 
    {
        System.out.print
        ("The warrior made " + numberOfMoves + " valid moves in " + timeElapsed + 
        " milliseconds. The final score is " + currentScore + " points.\n");

        
        

    }  
}
