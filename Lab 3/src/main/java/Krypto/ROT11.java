package Krypto;

public class ROT11 implements Algorithm {

    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public String crypt(String slowo) {
        boolean shifted = false;
        String noweSlowo = "";
        for (int i = 0; i < slowo.length(); ++i) {
            for (int j = 0; j < alphabet.length; ++j) {
                if (slowo.charAt(i) == alphabet[j]) {
                    if (j < alphabet.length / 2) {
                        noweSlowo += alphabet[(j + 11) % 26];
                        shifted = true;
                    } else {
                        noweSlowo += alphabet[(j + 11) % 26 + 26];
                        shifted = true;
                    }
                }
            }
            if (!shifted) {
                noweSlowo += slowo.charAt(i);
            }
            shifted = false;
        }
        return noweSlowo;
    }

    public String decrypt(String slowo) {
        boolean shifted = false;
        int newIndex;
        String noweSlowo = "";
        for (int i = 0; i < slowo.length(); ++i) {
            for (int j = 0; j < alphabet.length; ++j) {
                if (slowo.charAt(i) == alphabet[j]) {
                    if (j < alphabet.length / 2) {
                        newIndex = j - 11;
                        if (newIndex < 0) newIndex += 26;
                        noweSlowo += alphabet[newIndex];
                        shifted = true;
                    } else {
                        newIndex = j - 11;
                        if (newIndex < 0) newIndex += 26;
                        noweSlowo += alphabet[newIndex % 26 + 26];
                        shifted = true;
                    }
                }
            }
            if (!shifted) {
                noweSlowo += slowo.charAt(i);
            }
            shifted = false;
        }
        return noweSlowo;
    }
}
