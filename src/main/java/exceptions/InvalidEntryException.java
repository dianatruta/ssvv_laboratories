package exceptions;

public class InvalidEntryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidEntryException() {
        super();
    }

    public InvalidEntryException(String msg) {
        super(msg);
    }

}
