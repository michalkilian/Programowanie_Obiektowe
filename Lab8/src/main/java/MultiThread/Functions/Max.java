package MultiThread.Functions;

import java.io.*;
import java.util.Arrays;

public class Max extends Function implements Runnable{

    public Max(File file, int line){
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
            int max = 0;
            for(int numb: numbersInt){
                if (numb > max){
                    max = numb;
                }
            }
            this.value = max;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
