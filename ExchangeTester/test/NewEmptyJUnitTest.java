/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sheff
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }

    @Test
    public void profTest() {
        Exchange e = new Exchange(3);
        e.setRate(0, 1, 1.5);
        e.setRate(0, 2, 2);
        e.setRate(1, 2, 1.5);
        e.setRate(2, 0, .3);
        assertTrue(!e.arbitrageExists());
        assertEquals(.45, e.bestExchangeRate(1, 0), .0000001);
        assertEquals(1, e.bestExchangeRate(1, 1), .0000001);
        assertEquals(2.25, e.bestExchangeRate(0, 2), .0000001);

        Exchange e2 = new Exchange(3);
        e2.setRate(0, 1, .5);
        e2.setRate(0, 2, 2);
        e2.setRate(1, 0, 1.9);
        e2.setRate(1, 2, 4);
        e2.setRate(2, 0, .51);
        e2.setRate(2, 1, .22);
        assertTrue(e2.arbitrageExists());
    }
}