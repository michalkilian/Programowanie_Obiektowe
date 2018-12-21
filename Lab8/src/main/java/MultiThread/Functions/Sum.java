package MultiThread.Functions;

import java.io.*;
import java.util.Arrays;

public class Sum extends Function implements Runnable{

    public Sum(int line){
        super(line);
    }

    @Override
    public void run() {

            int sum = 0;
            for(int numb: Function.numbers[line]){
                    sum += numb;
                }
            this.value = sum;
    }
}
