package pl.edu.agh;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import main.java.pl.edu.agh.datastructures.Vector;

/**
 * Main class of the program.
 *
 * @author Michał Wypych
 */
public class Main {
    private Pattern lParen = Pattern.compile("\\(");
    private Pattern delimeter = Pattern.compile(",\\s*");

    /**
     * Prints program help to standard output.
     */
    private void printHelp() {
        System.out.println("Składnia: vectors VECTOR VECTOR");
        System.out.println("Dodawanie dwóch wektorów");
        System.out.println("VECTOR specyfikacja wektora w formie (LICZBA_ZMIENNOPRZECINKOWA, LICZBA_ZMIENNOPRZECINKOWA, ... LICZBA_ZMIENNOPRZECINKOWA)");
        System.out.println("Przykład: vectors (1.89, 6.456) (0.001, 6.781)");

        return;
    }

    /**
     * Parses single argument as a vector.
     *
     * <em>arg</em> must satisfy the following form
     * "(double,double,...,double)"
     * @param arg
     * @return
     */
    private Vector parseArg(String arg) {

        try ( Scanner s = new Scanner(arg) ) {
            s.findInLine(lParen);
            s.useDelimiter(delimeter);
            s.useLocale(Locale.US);

            ArrayList<Double> array = new ArrayList<>();
            while( s.hasNextDouble() ) {
                double d = s.nextDouble();
                array.add(d);
            }

            return Vector.fromArray(array);
        }
    }

    /**
     * Main entry point of the program.
     *
     * Checks number of parameters provided to program
     * and prints sum of the two vector.
     *
     * @param args two vectors has to be provided as parameters.
     */
    public static void main(String[] args) {
        Main main = new Main();
        if( args.length != 2 ) {
            main.printHelp();
        }

        Vector v = main.parseArg(args[0]);
        Vector v2 = main.parseArg(args[1]);
        System.out.print("suma: ");
        System.out.print(v);
        System.out.print(" + ");
        System.out.print(v2);
        System.out.print(" = ");
        v.add(v2);
        System.out.println(v);
    }

}

