package exceptions;

/**
 * Created by guojing on 2017/3/21.
 */
public class NotFoundException extends Exception {
    /**
     * NotFoundException.
     */
    public NotFoundException() {

    }

    /**
     * NotFoundException with message.
     * @param message message
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
