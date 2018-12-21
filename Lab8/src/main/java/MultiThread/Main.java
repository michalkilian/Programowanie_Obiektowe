package MultiThread;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("randoms.txt");
        GenerateRandoms[] gen = new GenerateRandoms[100];
        for (int i = 0; i < 100; ++i) {
            gen[i] = new GenerateRandoms(i);
            Thread t
                    = new Thread(gen[i]);
            t.start();
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("randoms.txt"), "utf-8"))) {
            for (int i = 0; i < 100; ++i) {
                for (int j = 0; j < 10000; ++j) {
                    writer.write(String.valueOf(GenerateRandoms.numbers[i][j]) + ' ');
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj 0 aby obliczyć max lub 1 aby obliczyć sumę");
        int functionType = scanner.nextInt();

        MaxMultiThread thread = new MaxMultiThread();
        try {
            System.out.println(thread.calculateFunction(functionType, file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
