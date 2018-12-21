package MultiThread;

import MultiThread.Functions.Function;
import MultiThread.Functions.Max;
import MultiThread.Functions.Sum;

import java.io.*;

public class MaxMultiThread {

    public int calculateFunction(int functionType, File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int line = 0;

        while ((st = br.readLine()) != null) {
            int numberOfNumbers = 0;
            for (String number : st.split(" ")) {
                Function.numbers[line][numberOfNumbers] = Integer.parseInt(number);
                numberOfNumbers += 1;
            }
            line += 1;
        }


        int result = 0;
        if (functionType == 0) {
            Max[] maxes = new Max[100];
            for (int i = 0; i < 100; ++i) {
                maxes[i] = new Max(i);
                Thread t
                        = new Thread(maxes[i]);
                t.start();
            }
            for (Max maxe : maxes) {
                if (maxe.value > result) {
                    result = maxe.value;
                }
            }
        } else if (functionType == 1) {
            final Sum[] sums = new Sum[100];

            for (int i = 0; i < 100; ++i) {
                sums[i] = new Sum(i);
                Thread t
                        = new Thread(sums[i]);
                t.start();
            }
            for (Sum sum : sums) {
                result += sum.value;
            }
        }

        return result;
    }
}
