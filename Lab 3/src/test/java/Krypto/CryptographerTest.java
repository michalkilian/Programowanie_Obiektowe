package Krypto;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CryptographerTest {

    String inFile;
    String outFile;
    BufferedReader br;

    @Test
    public void cryptFileWithRot() throws IOException {
        inFile = "in_to_crypt.txt";
        outFile = "out_crypted_Rot.txt";
        File file = new File(outFile);
        Cryptographer.cryptfile(inFile, outFile, new ROT11());
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        assertEquals("Should read 5 lines", 5, lines.size());
        assertEquals("Line 1", "epde ", lines.get(0));
        assertEquals("Line 2", "epde EPDE ", lines.get(1));
        assertEquals("Line 3", " ", lines.get(2));
        assertEquals("Line 4", "lmnopqrstuvwxyzabcdefgijk ", lines.get(3));
        assertEquals("Line 5", "LMNOPQRSTUVWXYZABCDEFGIJK ", lines.get(4));
    }

    @Test
    public void decryptFileWithRot() throws IOException {
        inFile = "in_to_decrypt_Rot.txt";
        outFile = "out_decrypted_Rot.txt";
        File file = new File(outFile);
        Cryptographer.decryptfile(inFile, outFile, new ROT11());
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        assertEquals("Should read 5 lines", 5, lines.size());
        assertEquals("Line 1", "test ", lines.get(0));
        assertEquals("Line 2", "test TEST ", lines.get(1));
        assertEquals("Line 3", " ", lines.get(2));
        assertEquals("Line 4", "abcdefghijklmnopqrstuvxyz ", lines.get(3));
        assertEquals("Line 5", "ABCDEFGHIJKLMNOPQRSTUVXYZ ", lines.get(4));
    }

    @Test
    public void cryptFileWithPoli() throws IOException {
        inFile = "in_to_crypt.txt";
        outFile = "out_crypted_Poli.txt";
        File file = new File(outFile);
        Cryptographer.cryptfile(inFile, outFile, new Polibiusz());
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        assertEquals("Should read 5 lines", 5, lines.size());
        assertEquals("Line 1", "44 15 43 44  ", lines.get(0));
        assertEquals("Line 2", "44 15 43 44  44 15 43 44  ", lines.get(1));
        assertEquals("Line 3", " ", lines.get(2));
        assertEquals("Line 4", "11 12 13 14 15 21 22 23 24 24 25 31 32 33 34 35 41 42 43 44 45 51 53 54 55  ", lines.get(3));
        assertEquals("Line 5", "11 12 13 14 15 21 22 23 24 24 25 31 32 33 34 35 41 42 43 44 45 51 53 54 55  ", lines.get(4));
    }

    @Test
    public void decryptFileWithPoli() throws IOException {
        inFile = "in_to_decrypt_Poli.txt";
        outFile = "out_decrypted_Poli.txt";
        File file = new File(outFile);
        Cryptographer.decryptfile(inFile, outFile, new Polibiusz());
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        assertEquals("Should read 5 lines", 4, lines.size());
        assertEquals("Line 1", "T E S T ", lines.get(0));
        assertEquals("Line 2", "T E S T T E S T ", lines.get(1));
        assertEquals("Line 3", "A B C D E F G H I I K L M N O P Q R S T U V X Y Z ", lines.get(2));
        assertEquals("Line 4", "A B C D E F G H I I K L M N O P Q R S T U V X Y Z ", lines.get(3));
    }
}