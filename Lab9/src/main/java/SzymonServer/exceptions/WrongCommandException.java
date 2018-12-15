package SzymonServer.exceptions;

public class WrongCommandException extends Throwable {
    public WrongCommandException(String message) {
        super(message);
    }
}
