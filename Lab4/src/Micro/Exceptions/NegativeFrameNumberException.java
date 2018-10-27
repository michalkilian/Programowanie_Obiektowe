package Micro.Exceptions;

public class NegativeFrameNumberException extends Exception {
    public NegativeFrameNumberException(String quote){
        super(quote);

    }
}