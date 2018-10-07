import java.io.*;

public class    Reader{
    public static void main(String[] argv) throws IOException { //Brakowało wyrzucania wyjątku
        InputStreamReader rd = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(rd);

        System.out.print("Wpisz jakis tekst: ");
        String text = bfr.readLine();
        System.out.println("Wpisales: "+text);
    }
}	  