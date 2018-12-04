package Workers;

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            int n = scanner.nextInt();
            switch (n){
                case 1:
                    DB db = new DB();
                    db.printAllWorkers();
                    break;
                case 2:
                    addWorker();
                    break;
                case 3:
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("\nWyświetlanie pracowników - podaj 1");
        System.out.println("Dodanie nowego pracownika - podaj 2");
        System.out.println("Wyjście - podaj 3");
    }

    private static void addWorker() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj pesel pracownika: ");
        String pesel = br.readLine();
        System.out.println("Podaj imie pracownika: ");
        String imie = br.readLine();
        System.out.println("Podaj nazwisko pracownika: ");
        String nazwisko = br.readLine();
        System.out.println("Podaj wynagrodzenie pracownika: ");
        double wynagrodzenie = Double.parseDouble(br.readLine());
        Worker worker = new Worker(pesel, imie, nazwisko, wynagrodzenie);
        DB db = new DB();
        db.addWorker(worker);
    }
}
