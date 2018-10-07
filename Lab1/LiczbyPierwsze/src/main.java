import javaIn.JIn;

public class main {
    public static void main(){
        System.out.println("Podaj liczbę do sprawdzenia: ");
        int number = JIn.getInt();
        System.out.println("Liczby pierwsze mniejsze od podanej: " + LiczbyPierwsze.searchPrimes(number));
    }
}
