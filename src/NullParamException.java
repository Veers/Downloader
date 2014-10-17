
public class NullParamException extends Exception {
    public NullParamException() {
        super();
    }

    public NullParamException(String message) {
        super(message);
    }

    public NullParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullParamException(Throwable cause) {
        super(cause);
    }

}
