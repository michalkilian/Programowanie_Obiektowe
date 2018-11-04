package pytania;

/**
 * Created by student on 2018-10-30.
 */
class InadequateFoodException extends RuntimeException {
    InadequateFoodException(String msg) {
        super(msg);
    }
}

class Food {
}

class Meat extends Food {
}

public class Mammoth {
    public void eat(Food food) {
        if (food instanceof Meat) {
            throw new InadequateFoodException("I don't like meat");
        }
    }
}