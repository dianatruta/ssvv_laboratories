package exceptions;

public class InvalidMemberException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidMemberException() {
        super();
    }

    public InvalidMemberException(String msg) {
        super(msg);
    }

}
