package MultiThread;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandoms implements Runnable {
    int line;
    static int[][] numbers = new int[100][10000];

    public GenerateRandoms(int line) {

        this.line = line;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
            numbers[line][i] = randomNum;
        }
    }
}
