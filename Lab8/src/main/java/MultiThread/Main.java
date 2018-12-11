package MultiThread;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file  = new File("randoms.txt");
        GenerateRandoms[] gen = new GenerateRandoms[100];
        for (int i = 0; i < 100; ++i) {
            gen[i] = new GenerateRandoms(file);
            Thread t
                    = new Thread(gen[i]);
            t.start();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj 0 aby obliczyć max lub 1 aby obliczyć sumę");
        int functionType = scanner.nextInt();

        MaxMultiThread thread = new MaxMultiThread();
        thread.calculateFunction(functionType, file);
    }
}
