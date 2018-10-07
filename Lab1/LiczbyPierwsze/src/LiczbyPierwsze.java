import java.util.ArrayList;
import java.util.List;

public class LiczbyPierwsze {
    /**
     *
     * @param number
     * @return zwraca listę liczb pierwszych mniejszych od podanej liczby
     */
    public static List searchPrimes(int number){
        List primeList = new ArrayList();
        for (int i = 2; i < number; ++i){
            if (LiczbyPierwsze.chcekPrime(i)){
                primeList.add(i);
            }
        }
        return primeList;
    }

    /**
     *
     * @param prime_number
     * @return prawda jeśli podany numer jest liczbą pierwszą fałsz w przeciwnym wypadku
     */
    public static boolean chcekPrime(int prime_number){
        for (int i = 2; i <= Math.sqrt(prime_number); ++i){
            if(prime_number % i == 0){
                return false;
            }
        }
        return true;
    }
}
