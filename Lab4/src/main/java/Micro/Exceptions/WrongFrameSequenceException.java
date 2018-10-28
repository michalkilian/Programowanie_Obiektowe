package Micro.Exceptions;

public class WrongFrameSequenceException extends Exception {
    public WrongFrameSequenceException(String quote) {
        super(quote);
    }
}
