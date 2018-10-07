package javaIn;
import java.util.Scanner;



import java.io.*;

public class JIn {
    public static String getString() {
        String text = null;
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        return text;
    }
    public static int getInt(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}