package pytania;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class MammothTests {

        private Mammoth mammoth;


        @BeforeClass
        public static void createFile() throws IOException {
            File file = new File("file.txt");
            file.createNewFile();
        }
        @AfterClass
        public static void deleteFile() throws IOException{
            File file = new File("file.txt");
            file.delete();
        }

        @Before
        public void createMammoth() {
           this.mammoth = new Mammoth();
        }
        //zwykle nie spodziewamy się żadnych wyjątków
        @Rule public ExpectedException thrown = ExpectedException.none();


        @Test
        public void correctFoodTest(){

            mammoth.eat(new Food());
        }

        @Test (expected=InadequateFoodException.class)
        public void throwsInadequateFoodException() throws InadequateFoodException {
        mammoth.eat(new Meat());
    }

        @Test public void throwsInadequateFoodExceptionWithMessage() {
        try {

            mammoth.eat(new Meat());
        } catch( InadequateFoodException ex ) {
            Assert.assertEquals(ex.getMessage(), "I don't like meat");

        }
    }
    }
