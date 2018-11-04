package pytania;

//java org.junit.runner.JUnitCore pytania.ATests

import org.junit.Test;
import org.junit.Assert;

public class ATests {

    @Test
    public void metCaseOneTest() {

        A a = new A();

        Assert.assertEquals(a.met(1), "pierwszy");
    }

    @Test
    public void metCaseTwoTest() {

        A a = new A();

        Assert.assertEquals(a.met(2), "drugi");
    }

    @Test
    public void metCaseOtherTest() {

        A a = new A();

        Assert.assertEquals(a.met(3), "inny");
    }


}
