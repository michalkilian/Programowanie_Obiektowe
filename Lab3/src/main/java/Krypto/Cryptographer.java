package Krypto;

import java.io.*;

public class Cryptographer {
    public static void cryptfile(String inFile, String outFile, Algorithm algorithm) throws IOException {
        File file = new File(inFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("\\s+");

            for (String word : tokens) {
                writer.append(algorithm.crypt(word));
            }
            writer.append('\n');
        }
        writer.close();
    }

    public static void decryptfile(String inFile, String outFile, Algorithm algorithm) throws IOException {
        File file = new File(inFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split("\\s+");

            for (String word : tokens) {
                writer.append(algorithm.decrypt(word));
            }
            writer.append('\n');
        }
        writer.close();
    }
}
