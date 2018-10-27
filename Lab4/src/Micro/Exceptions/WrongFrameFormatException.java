package Micro.Exceptions;

public class WrongFrameFormatException extends Exception {
    public WrongFrameFormatException(String quote){
        super(quote);

    }
}