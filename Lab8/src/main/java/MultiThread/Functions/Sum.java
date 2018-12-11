package MultiThread.Functions;

import java.io.*;
import java.util.Arrays;

public class Sum extends Function implements Runnable{

    public Sum(File file, int line){
        super(file, line);
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            for(int i = 0; i < line; ++i)
                br.readLine();
            String lineIWant = br.readLine();
            String[] numbers = lineIWant.split(" ");
            int[] numbersInt = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            for(int numb: numbersInt){
                    sum += numb;
                }
            this.value = sum;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
