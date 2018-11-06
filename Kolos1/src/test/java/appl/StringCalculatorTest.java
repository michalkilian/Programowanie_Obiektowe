package appl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by student on 2018-11-06.
 */
public class StringCalculatorTest {

    StringCalculator calc;

    @Before
    public void createCalc(){
       calc = new StringCalculator("test");
    }

    @Test
    public void testAdd() throws Exception {
        calc.add("Test");
        assertEquals("testTest", calc.getResult());
    }

    @Test
    public void testMultiply() throws Exception {
        calc.multiply(3);
        assertEquals("testtesttest", calc.getResult());

    }

    @Test(expected = excp.BadInputException.class)
    public void testSubtractThrowBadInputException() throws Exception{
        calc.subtract("ab");
    }
}