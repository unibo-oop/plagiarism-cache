package virtualworld;

public class OutofBoundariesException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "Something tried to place an object outside the Boundaries";

    public OutofBoundariesException() {
        super(MESSAGE);
    }
}
