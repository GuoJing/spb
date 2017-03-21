package exceptions;

/**
 * Created by guojing on 2017/3/21.
 */
public class NotFoundException extends Exception {
    public NotFoundException() {}

    public NotFoundException(String message) { super(message); }
}
