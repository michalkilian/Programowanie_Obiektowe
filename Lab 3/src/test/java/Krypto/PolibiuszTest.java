package Krypto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolibiuszTest {

    Polibiusz polibiusz;

    @Before
    public void createPoli(){
        this.polibiusz = new Polibiusz();
    }

    @Test
    public void cryptEmpty() {
        assertEquals("", polibiusz.crypt(""));
    }

    @Test
    public void cryptOneWord(){
        assertEquals("44 15 43 44 ", polibiusz.crypt("test"));
    }

    @Test
    public void cryptInvalidLetter(){
        assertEquals("", polibiusz.crypt("4"));
    }


    @Test(expected = StringIndexOutOfBoundsException.class) //Polibiusz decrypt by one character
    public void decryptEmpty() {
        assertEquals("", polibiusz.decrypt(""));
    }

    @Test
    public void decryptOneLetter(){
        assertEquals("T", polibiusz.decrypt("44"));
        assertEquals("E", polibiusz.decrypt("15"));
        assertEquals("S", polibiusz.decrypt("43"));

    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void decryptInvalidLetter(){
        assertEquals("", polibiusz.decrypt("A"));
    }
}