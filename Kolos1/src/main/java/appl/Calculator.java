package appl;

import excp.BadInputException;
import excp.NothingToSubtractFromException;
import excp.TooBigNumberException;

import java.io.IOException;

/**
 * Created by student on 2018-11-06.
 */
public abstract class Calculator {
    public abstract void saveToFile(String x, String filename) throws IOException;

    public abstract void add(String string);

    public abstract void subtract(String string) throws NothingToSubtractFromException, BadInputException;

    public abstract void multiply(int number) throws TooBigNumberException;
}
