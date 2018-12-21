package MultiThread.Functions;

import java.io.*;
import java.util.Arrays;

public class Max extends Function implements Runnable {

    public Max(int line) {
        super(line);
    }

    @Override
    public void run() {

        int max = 0;
        for (int numb : Function.numbers[line]) {
            if (numb > max) {
                max = numb;
            }
        }
        this.value = max;
    }
}
