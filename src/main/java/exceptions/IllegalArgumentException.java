package exceptions;

/**
 * Created by guojing on 2017/3/27.
 */
public class IllegalArgumentException extends Exception {
    /**
     * IllegalArgumentException constructor.
     */
    public IllegalArgumentException() {

    }

    /**
     * IllegalArgumentException with message.
     * @param message error message
     */
    public IllegalArgumentException(final String message) {
        super(message);
    }
}
