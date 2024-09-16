// Name: Christopher Wiratman
// Net ID: cjw220005
package ObstaclesWarrior;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test
 */
public class MainTest {

    @Test
    public void ReadBoardFromFileTest() 
    {
        //Create start and exit positions to pass to the method. 
        //These objects will be set with actual values from the
        //board file by your code inside the ReadBoardFromFile() method
        Position    actualStartPosition     = new Position(0, 0); 
        Position    actualExitPosition      = new Position(0, 0);
        
        //These are the expected values for the start and exit postions
        Position    expectedStartPosition   = new Position(0, 2); 
        Position    expectedExitPosition    = new Position(2, 2);
 
        //Create the expected array with the data
        String[][]  expectedBoardArray = {
                                            {"0", "#", "#", "#" },
                                            {"#", "-3", "#", "-5" },
                                            {"#", "#", "#", "#" },
                                            {"#", "#", "-1", "#" },
                                         }; 

        //Invoke the ReadBoardFromFile() method and capture the returned array
        String[][] actualBoardArray = Main.ReadBoardFromFile("c:\\temp\\Board.dat", 
                                                              actualStartPosition, 
                                                              actualExitPosition);
        
        //Check if the start and exit positions match                                                     
        if((expectedStartPosition.getX() != actualStartPosition.getX())||
           (expectedStartPosition.getY() != actualStartPosition.getY()))
           {
                assertTrue("Start position does not match", false); 
           }
           
        if((expectedExitPosition.getX() != actualExitPosition.getX())||
           (expectedExitPosition.getY() != actualExitPosition.getY()))
           {
                assertEquals("Exit position does not match", false);
           }

        //Compare the actualBoardArray with the testBoardArray. 
        //Size and data must match.
        //Make sure the number of rows match
        assertArrayEquals("Board array read from file does not match expected array",
                          expectedBoardArray,
                          actualBoardArray );


    }

    @Test
    public void WriteBoardToFileTest()
    {
        // Mock board data

        String [][] boardToWrite = {
            {"0", "#", "#", "#"},
            {"#", "-3", "#", "-5"},
            {"#", "#", "#", "#"},
            {"#", "#", "-1", "#"},
        }; 

        // Expected start and exit positions

        Position expectedStartPosition = new Position(0, 2);
        Position expectedExitPosition = new Position(2,2);

        String filePath = "C:\\temp\\resultboard.dat";

        /*
        3 3
        2 1
        1 1
        5 # #
        # 3 #
        # # #
        */

        // Checks if the WriteBoardToFile can write to the resultboard.dat file

        assertTrue("File could not be written to with data", Main.WriteBoardToFile(filePath, boardToWrite));
        

        //Calls the function the to open file and read it into an array

        Position startPosition = new Position(0,0);
        Position exitPosition = new Position(0,0);
        String[][] boardFromFile =  Main.ReadBoardFromFile(filePath, startPosition, exitPosition);
        
        // Checks if start positions match

        assertTrue("Start position does not match",
        (expectedStartPosition.getX() == startPosition.getX()) &&
        (expectedStartPosition.getY() == startPosition.getY()));

        // Checks if exit positions match
        
        assertTrue("Exit position does not match",
        (expectedExitPosition.getX() == exitPosition.getX()) && 
        (expectedExitPosition.getY() == startPosition.getY()));

        // Checks if the written array was equal to the array assigned to be written

        assertArrayEquals("Board from file did not match the expected board", boardToWrite, boardFromFile);


    }   

    @Test
    public void GenerateDirectionTest() 
    {
        // Checks that GenerateDirectionTest()'s number generation is in 
        // range of the specified numbers: 1-7
        int numToTest;
        for (int i = 0; i < 100; i++) {
            numToTest = Main.GenerateDirection();
            assert(numToTest >= 1 && numToTest <= 7);
        }
    }


    @Test
    public void MoveWarriorTest() 
    {
        
        // Mock Board Data

        String [][] boardArray = {
            {"0", "#", "#", "#"},
            {"#", "-3", "#", "-5"},
            {"#", "#", "#", "#"},
            {"#", "#", "-1", "#"},
        }; 

        Position actualPosition;

       
        Position [] expectedPositions = { 
            new Position(0, 0), // UP
            new Position(0, 1), // DOWN
            new Position(0, 0), // LEFT
            new Position(1, 0), // RIGHT
            new Position(0, 0), // UPRIGHT
            new Position(1, 1), // DOWNRIGHT
            new Position(0, 0), // UPLEFT
            new Position(0, 0)  // DOWNLEFT
        };                         

        // Checks for all different directions of top
        // left position with assumed expected positions

        for (int i = 0; i < 7; i++) {
            actualPosition = Main.MoveWarrior(i, boardArray, new Position(0, 0));
            
            assertTrue("Position " + i + " (" + actualPosition.getX() + ", " + actualPosition.getY() +  ") does not match expected (" + expectedPositions[i].getX() + ", " + expectedPositions[i].getY() + ")",
            (expectedPositions[i].getX() == actualPosition.getX()) &&
            (expectedPositions[i].getY() == actualPosition.getY()));
        }

       
        


        // Bottom right corner expected positions

        expectedPositions = new Position[]{ 
            new Position(3, 2), // UP
            new Position(3, 3), // DOWN
            new Position(2, 3), // LEFT
            new Position(3, 3), // RIGHT
            new Position(3, 3), // UPRIGHT
            new Position(3, 3), // DOWNRIGHT
            new Position(2, 2), // UPLEFT
            new Position(3, 3),  // DOWNLEFT
        };   
        
        // Checks for all different directions of bottom
        // right position with assumed expected positions

        for (int i = 0; i < 7; i++) {
            actualPosition = Main.MoveWarrior(i, boardArray, new Position(3, 3));
            
            assertTrue("Position " + i + " (" + actualPosition.getX() + ", " + actualPosition.getY() +  ") does not match expected (" + expectedPositions[i].getX() + ", " + expectedPositions[i].getY() + ")",
            (expectedPositions[i].getX() == actualPosition.getX()) &&
            (expectedPositions[i].getY() == actualPosition.getY()));
        }

        //Mock data with start and exit positions

        String [][] boardArray1 = {
            {"0", "#", "#", "1"},
            {"#", "-3", "#", "-5"},
            {"#", "#", "#", "#"},
            {"#", "2", "-1", "#"},
        };

        Position currentPosition = new Position(0, 1);

        Position expectedPosition = new Position(3, 0);

        // Calls function to get an assumed start position from direction

        actualPosition = Main.MoveWarrior(0, boardArray1, currentPosition);

        // Tests current position is start position

        assertEquals(actualPosition.getX(), expectedPosition.getX());
        assertEquals(actualPosition.getY(), expectedPosition.getY());

        // Tests 0 was changed to a #

        assertEquals("#", boardArray1[0][0]);


        
        

        // Main.MoveWarrior(direction, String[][] boardArray, Position currentPosition)
    }

    @Test
    public void CalculateWarriorScoreTest() 
    {
        // Call function to get score
        
        String [][] boardArray = {{"5", "#", "#"},
                                  {"#", "3", "#"},
                                  {"#", "#", "#"}};

        int actualScore = 30, expectedScore = 200;
        Position currentPosition = new Position (1 , 2);

        // Gets actual score
        
        actualScore = Main.CalculateWarriorScore(actualScore, currentPosition, boardArray);

        // Checks expected score

        assertEquals("Scores do not match",actualScore,expectedScore);
    }

    @Test
    public void DisplayResultsTest() 
    {
        // Assumed variable inputs

        int finalScore = 180, 
            totalMoves = 46, 
            totalTime = 360;
        
        // Expected output
        
        String expectedOutput = "The warrior made " + totalMoves + " valid moves in " + totalTime + 
        " milliseconds. The final score is " + finalScore + " points.\n";
        
        //Saves original printstream

        PrintStream originalOutStream = System.out; 

        // Hijack the output stream with our own stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Call the function and capture its ouptut 
        Main.DisplayResults(finalScore, totalMoves, totalTime);
        String actualOutput = outContent.toString();
        
        // Check that the captured output matches the expected output 
        assertEquals("Output does not match expected", expectedOutput, actualOutput);

        // Un-hijack the stream and return it to System.out
        System.setOut(originalOutStream);
    }   
}
