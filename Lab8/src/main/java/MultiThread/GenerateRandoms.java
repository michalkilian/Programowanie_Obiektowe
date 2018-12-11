package MultiThread;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandoms implements Runnable{
    File file;

    public GenerateRandoms(File file){
        this.file = file;
    }

    @Override
    public void run() {
        try {
            int[] numbers = new int[10000];
            for(int i = 0; i < 10000; ++i){
                int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
                numbers[i] = randomNum;
            }
            synchronized (numbers){
                System.out.println("Wpisywanie");
                PrintWriter outputWriter = new PrintWriter("randoms.txt", "UTF-8");
                for(int i = 0; i < 10000; ++i){
                    outputWriter.write(numbers[i]+" ");
                }
                outputWriter.write("\n");
                outputWriter.close();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
