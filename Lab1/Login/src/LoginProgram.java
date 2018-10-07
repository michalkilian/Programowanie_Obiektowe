/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author Szymon
 */
public class LoginProgram {
    public static void main(String[] argv){
      Login log = new Login ("ala", "makota");
	  try{
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(rd);

        System.out.println("Proszę podać login i hasło");
		String login = bfr.readLine();
        String haslo = bfr.readLine();

        if (log.check(login, haslo)){
        	System.out.println("OK");
		  }
		  else {
		      System.out.println("Podano nieprawidłowy login lub hasło");
        }
        /*TODO: sprawdzenie czy podane hasło i login zgadzaja sie z tymi
         przechowywanymi w obiekcie log Jeśli tak, to ma zostać
         wyswietlone "OK", jesli nie - prosze wyswietlić informacje o błedzie
         */

	  }catch(IOException e){e.printStackTrace();}

    }
}
