// Name: Christopher Wiratman
// Net ID: cjw220005



package ObstaclesWarrior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * ObstaclesWarrior
 *
 */
public class Main 
{ 
    public static void main( String[] args) throws IOException
    {

        String filePath, systemOption;
        Position startPosition = new Position(-1,-1);
        Position exitPosition  = new Position(-2,-2);

        Scanner scnr = new Scanner(System.in);

        File file;

        // Prompt user for Board.dat file path;
        do
        {
            // Filepath: "c:\temp\Board.dat"
            System.out.print("Enter the board data file path: ");

            filePath = scnr.nextLine();
            file = new File(filePath);

            if(!file.exists())
            {
                System.out.println("Invalid File");
            }
        }
        while(!file.exists());

        //Calls function to read the board data from file and fill in the 2-D array
        String [][] gameBoard = ReadBoardFromFile(filePath, startPosition, exitPosition);

        //Prompts user to type "Start" or "Exit" to start the game
        System.out.print("Type \"Start\" to start the game or \"Exit\" to exit the game: ");
        systemOption = scnr.next();
            
        switch (systemOption) 
        {
            case "Start":
                break;
            case "Exit":
                System.exit(0);
            default:
                System.err.println("Input Error");
                break;
        }

        //Assigns the current position to be the start position
        Position currentPosition = new Position(startPosition.getX(), startPosition.getY());

        int direction = -1, currentScore = 0,
                                totalMoves = 0;
        double startTime, endTime, elapsedTime;

        //Starts the elapsed time for the game
        startTime = System.currentTimeMillis();

        //Runs the game until the current position becomes the exit position
        while( !((currentPosition.getX() == exitPosition.getX()) && (currentPosition.getY() == exitPosition.getY())) )
        {
            direction = GenerateDirection();
            currentPosition = MoveWarrior(direction, gameBoard, currentPosition);


            //Checks if the current position is a "0" and if so replaces the 0 with
            //with a "#" and assigns the current position the start position.
            if(gameBoard[currentPosition.getY()][currentPosition.getX()].equals("0")) {
                gameBoard[currentPosition.getY()][currentPosition.getX()] = "#";
                currentPosition = new Position(startPosition.getX(), startPosition.getY());
            }

            //Calculates score and increments total moves after every move.
            currentScore = CalculateWarriorScore(currentScore, currentPosition, gameBoard);
            totalMoves++;


            // Prints data for debugging

            /*
            System.out.println("Direction: " + direction + "\n" +
                                "Current Position: x = " + currentPosition.getX() + " y = " + currentPosition.getY() + "\n" +
                                "Current Score: " + currentScore + "\n" +
                                "Total Moves: " + totalMoves);
            */
        
        }

        //Stops and calculates elapsed time
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        //Displays total score, total moves, and the elapsed time from
        //start to end of game.

        DisplayResults(currentScore, totalMoves, (int)elapsedTime);

        int fieldWidth = 5;
        String spacing;

        //Displays game board

        for (int i = 0; i < gameBoard.length; i++)
        {   
            spacing = "  ";

            for(int j = 0; j < gameBoard[0].length; ++j)
            {   
                // Prints the amount of spaces based on the lengths of the strings to account for aligning
                if(!(j == 0))
                {
                    spacing = (" ").repeat(fieldWidth - gameBoard[i][j].length());
                }
                
                // Prints the spacing and the string in the array
                System.out.print(spacing + gameBoard[i][j]);
            }
            System.out.println();
        }

        String outputFilePath = "c:\\temp\\resultboard.dat";

        File outputFile = new File(outputFilePath);

        //Checks if the new "resultBoard.dat" file was created
        if(!outputFile.createNewFile())
        {
            System.out.println("Error file already exists");
        }
        else
        {
            //Writes the rows, columns, start, exit positions, and gameboard data
            //to the new "resultBoard.dat" file
            try(FileWriter outputWriter = new FileWriter(outputFilePath))
            {
                outputWriter.write(gameBoard.length + " " + gameBoard[0].length + "\n"
                                + startPosition.getY() + " " + startPosition.getX() + "\n"
                                + exitPosition.getY() + " " + exitPosition.getX() + "\n");

            } 
            catch(IOException e)
            {
                System.out.println("Error writing to file");
            }

            if(!WriteBoardToFile(outputFilePath, gameBoard))
            {
                System.out.println("There was an error writing to the file");
            } 
        }

        System.out.println("\nPress any key to exit!");

        scnr.next();

        scnr.close();
    }

    



    public static String[][] ReadBoardFromFile(String fileName, 
                                               Position startPosition, 
                                               Position exitPosition)
    {
        //Assume that the data below is read from a file with your actual implementation.
        //You don't need to write anything in your methods here to be able to write your
        //unit test methods.This code is added just to enable you to run the provided unit test. 

        /*

        String[][] gameBoard =  {
            {"0", "#", "#", "#"},
            {"#", "-3", "#", "-5"},
            {"#", "#", "#", "a#"},
            {"#", "#", "-1", "#"},
        };
        */

        //startPosition.setX(0);
        //startPosition.setY(2);
        //exitPosition.setX(2);
        //exitPosition.setY(2);
 
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

        //Reads the values for rows and columns
        rows = lineScanner.nextInt();
        columns = lineScanner.nextInt();
        lineScanner.close();

        lineScanner = new Scanner(boardScanner.nextLine());
        lineScanner.useDelimiter(" ");

        //Reads the values for start position
        startPosition.setY(lineScanner.nextInt());
        startPosition.setX(lineScanner.nextInt());
        lineScanner.close();

        lineScanner = new Scanner(boardScanner.nextLine());
        lineScanner.useDelimiter(" ");

        //Reads the values for exit position
        exitPosition.setY(lineScanner.nextInt());
        exitPosition.setX(lineScanner.nextInt());
        lineScanner.close();
        
        //Reads gameboard data and stores into 2-D array
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
        
        return gameBoard;
    } 
     
    
    public static boolean WriteBoardToFile(String fileName, 
                                           String[][] boardArray)
    {
        
        File checkFile = new File(fileName);

        //Checks if file can be written to and if not, returns false
        if(!checkFile.canWrite())
        {
            return false;
        }

        //Writes gameboard data to file
        try(FileWriter outputFile = new FileWriter(fileName, true))
        {
            for (int i = 0; i < boardArray.length; i++)
            {   
                
                for(int j = 0; j < boardArray[0].length; ++j)
                {   
                    outputFile.write(boardArray[i][j] + " ");
                }
                outputFile.write("\n");

            }

            outputFile.close();
        }
        //If exception occurs, writes out error message and returns false
        catch(IOException e)
        {
            System.out.println("Error writing to file");
            return false;
        }

        return true;
    } 
    
    public static int GenerateDirection()
    {
        //Generates random direction between 0 and 7
        int direction = (int)(Math.random() * 8);
        return direction;
    } 
    
    public static Position MoveWarrior(int direction, 
                                       String[][] boardArray, 
                                       Position currentPosition)
    {
        int rows = boardArray.length;

        // Adds and/or subtracts the the current position's x and y values
        // based on the direction.

        //0-UP, 1-DOWN, 2-LEFT, 3-RIGHT, 4-UPRIGHT, 5-DOWNRIGHT, 6-UPLEFT, 7-DOWNLEFT
        Position newPosition;
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        switch (direction) {
            
            case 0: //UP
                newPosition = new Position(x + 0, y - 1);
                break;
            case 1: //DOWN
                newPosition = new Position(x + 0, y + 1);
                break;
            case 2: //LEFT
                newPosition = new Position(x - 1, y + 0);
                break;
            case 3: //RIGHT
                newPosition = new Position(x + 1, y + 0);
                break;
            case 4: //UPRIGHT
                newPosition = new Position(x + 1, y - 1);
                break;
            case 5: //DOWNRIGHT
                newPosition = new Position(x + 1, y + 1);
                break;
            case 6: //UPLEFT
                newPosition = new Position(x - 1, y - 1);
                break;
            case 7: //DOWNLEFT
                newPosition = new Position(x - 1, y + 1);
                break;
            default:
                return new Position(-1, -1);
        }


        //Checks if the new position is outside the board, if so, returns the original position
        if(newPosition.getX() < 0 || newPosition.getY() < 0 || 
           newPosition.getY() >= rows || newPosition.getX() >= rows) 
        {
            return currentPosition;
        }
        
        return newPosition;
    } 
    
    public static int CalculateWarriorScore(int currentScore, 
                                            Position currentPosition, 
                                            String[][] boardArray)
    {
        
        int scoreModifier = 1;
        String currentSpotStr = boardArray[currentPosition.getY()][currentPosition.getX()];


        //Assigns the score modifier the value of the obstacle if it is an
        //integer.

        try (Scanner scnr = new Scanner(currentSpotStr))
        {
            scoreModifier = scnr.nextInt();

        } catch (Exception e) {

            //Used to ignore obstacles that aren't integers
        }

        return currentScore + scoreModifier;
        
    } 
    
    public static void DisplayResults(int currentScore, 
                                      int numberOfMoves, 
                                      int timeElapsed) 
    {

        //Displays the total score, number of moves, and the total time
        //elapsed from the game
        System.out.println
        ("\nThe warrior made " + numberOfMoves + " valid moves in " + timeElapsed + 
        " milliseconds. The final score is " + currentScore + " point(s).\n");

    }  


}
