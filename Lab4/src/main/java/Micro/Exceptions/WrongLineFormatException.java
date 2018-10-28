package Micro.Exceptions;

public class WrongLineFormatException extends Exception {
    public WrongLineFormatException(String quote) {
        super(quote);

    }
}