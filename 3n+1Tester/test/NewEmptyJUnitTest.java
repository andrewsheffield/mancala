/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sheff
 */
public class NewEmptyJUnitTest {
    
    @Test
    public void sampleTest()
    {
        Collatz c = new Collatz();

        assertEquals(16, Collatz.cycleLength(22));
        
        assertEquals(16, Collatz.maximumCycle(22, 22));
        
        assertEquals(1, Collatz.maximumCycle(1, 1));

        assertEquals(20, Collatz.maximumCycle(1, 10));

        assertEquals(125, Collatz.maximumCycle(100, 200));

        assertEquals(89, Collatz.maximumCycle(201, 210));

        assertEquals(174, Collatz.maximumCycle(900, 1000));
    }
    
    @Test
    public void backwardTest(){

        assertEquals(20, Collatz.maximumCycle(10, 1));

        assertEquals(125, Collatz.maximumCycle(200, 100));

        assertEquals(89, Collatz.maximumCycle(210, 201));

        assertEquals(174, Collatz.maximumCycle(1000, 900));
    }
    
    @Test
    public void overflowTest() {
        boolean overflow = false;
        try {
            Collatz.cycleLength(1000000);
        } catch (StackOverflowError e) {
            overflow = true;
        }
        assertFalse(overflow);
        try {
            Collatz.cycleLength(999999999);
        } catch (StackOverflowError e) {
            overflow = true;
        }
        assertFalse(overflow);
        
    }
    
}