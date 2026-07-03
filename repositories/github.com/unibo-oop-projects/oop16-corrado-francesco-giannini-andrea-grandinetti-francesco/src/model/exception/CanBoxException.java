package model.exception;

/**
 *
 */
public class CanBoxException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final int num;
    /**
     * 
     * @param num number.
     */
    public CanBoxException(final int num) {
        super();
        this.num = num;
    }
    /**
     * 
     * @return number
     */
    public int getNum() {
        return this.num;
    }
}
