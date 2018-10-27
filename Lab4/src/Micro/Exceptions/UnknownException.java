package Micro.Exceptions;

public class UnknownException extends Exception{
    public UnknownException(String quote){
        super(quote);
    }
}
