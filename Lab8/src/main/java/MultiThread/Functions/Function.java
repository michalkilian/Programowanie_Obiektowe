package MultiThread.Functions;

import java.io.File;

public class Function {
    public int value;
    File file;
    int line;

    public Function(File file, int line){
        this.file = file;
        this.line = line;
    }
}
