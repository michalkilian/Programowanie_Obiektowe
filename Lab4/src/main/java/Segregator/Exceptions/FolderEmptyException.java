package Segregator.Exceptions;

public class FolderEmptyException extends Exception {
    public FolderEmptyException(String quote) {
        super(quote);
    }
}
