package Krypto;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            String inFile = args[0];
            String outFile = args[1];

            Scanner scanner = new Scanner(System.in);
            System.out.println("Napisz 1 jeśli chcesz szyfrować 2 jeśli deszyfrować");
            int n = scanner.nextInt();
            int m;
            switch (n) {
                case 1:
                    System.out.println("Aby użyć algorytmu Rot 11 napisz 1, aby użyć szachownicy Polibiusza napisz 2");
                    m = scanner.nextInt();
                    switch (m) {
                        case 1:
                            Cryptographer.cryptfile(inFile, outFile, new ROT11());
                            break;
                        case 2:
                            Cryptographer.cryptfile(inFile, outFile, new Polibiusz());
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Aby użyć algorytmu Rot 11 napisz 1, aby użyć szachownicy Polibiusza napisz 2");
                    m = scanner.nextInt();
                    switch (m) {
                        case 1:
                            Cryptographer.decryptfile(inFile, outFile, new ROT11());
                            break;
                        case 2:
                            Cryptographer.decryptfile(inFile, outFile, new Polibiusz());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

        }
    }
}