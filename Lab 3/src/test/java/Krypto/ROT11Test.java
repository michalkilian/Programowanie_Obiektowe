package Krypto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ROT11Test {

    ROT11 rot11;

    @Before
    public void createPoli(){
        this.rot11 = new ROT11();
    }

    @Test
    public void cryptEmpty() {
        assertEquals("", rot11.crypt(""));
    }

    @Test
    public void cryptOneWord(){
        assertEquals("epde", rot11.crypt("test"));
        assertEquals("EPDE", rot11.crypt("TEST"));
    }

    @Test
    public void cryptInvalidLetter(){
        assertEquals("1", rot11.crypt("1"));
    }
    @Test
    public void decryptEmpty() {
        assertEquals("", rot11.decrypt(""));
    }

    @Test
    public void decryptOneLetter(){
        assertEquals("T", rot11.decrypt("E"));
        assertEquals("E", rot11.decrypt("P"));
        assertEquals("S", rot11.decrypt("D"));

    }
    @Test
    public void decryptOneWord(){
        assertEquals("TEST", rot11.decrypt("EPDE"));
        assertEquals("test", rot11.decrypt("epde"));
    }

    @Test
    public void decryptInvalidLetter(){
        assertEquals("1", rot11.decrypt("1"));
    }
}