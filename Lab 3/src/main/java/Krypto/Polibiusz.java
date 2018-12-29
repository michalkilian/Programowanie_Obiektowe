package Krypto;

public class Polibiusz implements Algorithm {
    public String crypt(String slowo) {

        String noweSlowo = "";
        slowo = slowo.toUpperCase();
        slowo = slowo.replaceAll("J", "I");

        char allchar[][] = {{'A', 'B', 'C', 'D', 'E'}, {'F', 'G', 'H', 'I', 'K'},
                {'L', 'M', 'N', 'O', 'P'}, {'Q', 'R', 'S', 'T', 'U'}, {'V', 'W', 'X', 'Y', 'Z'}};
        char b;
        for (int i = 0; i < slowo.length(); i++) {
            b = slowo.charAt(i);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (b == allchar[j][k]) {
                        int f = (j + 1);
                        int s = (k + 1);

                        noweSlowo += f;
                        noweSlowo += s;
                        noweSlowo += ' ';

                    }
                }
            }
        }
        return noweSlowo;
    }


    public String decrypt(String slowo) {
        String noweSlowo = "";

        char b = slowo.charAt(1);
        char allchar[][] = {{'A', 'B', 'C', 'D', 'E'}, {'F', 'G', 'H', 'I', 'K'},
                {'L', 'M', 'N', 'O', 'P'}, {'Q', 'R', 'S', 'T', 'U'}, {'V', 'W', 'X', 'Y', 'Z'}};


        noweSlowo += allchar[Character.getNumericValue(slowo.charAt(0)) - 1][Character.getNumericValue(slowo.charAt(1)) - 1];
        return noweSlowo;
    }
}
