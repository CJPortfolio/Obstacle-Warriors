package ObstaclesWarrior;

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
                assertEquals("Exit position does not match",false);
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
        String[][] boardArray = {}; 

        Main.WriteBoardToFile("", boardArray);

        
    }

    @Test
    public void GenerateDirectionTest() 
    {
        
    }

    @Test
    public void MoveWarriorTest() 
    {
        
    }

    @Test
    public void CalculateWarriorScoreTest() 
    {
        
    }

    @Test
    public void DisplayResultsTest() 
    {
        
    }   
}
