package MultiThread;

import MultiThread.Functions.Max;
import MultiThread.Functions.Sum;

import java.io.File;

public class MaxMultiThread {

    public int calculateFunction(int functionType, File file){
        int result = 0;
        if (functionType == 0){
            Max[] maxes = new Max[100];
            for (int i = 0; i < 100; ++i) {
                maxes[i] = new Max(file, i);
                Thread t
                        = new Thread(maxes[i]);
                t.start();
            }
            for (Max maxe : maxes) {
                if (maxe.value > result) {
                    result = maxe.value;
                }
            }
        }
        else if(functionType == 1){
            final Sum[] sums = new Sum[100];

            for (int i = 0; i < 100; ++i) {
                sums[i] = new Sum(file, i);
                Thread t
                        = new Thread(sums[i]);
                t.start();
            }
            for (Sum sum:sums) {
                result += sum.value;
            }
        }

        return result;
    }
}
