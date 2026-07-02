package view.menu.fair;

/**
 * This exception is thrown when a fair is trying to be added
 * in the simulation environment without having the fair type set.
 */
public class FairTypeUnsetException extends NullPointerException {

    private static final long serialVersionUID = 4868954055094397767L;

    /**
     * 
     * @return string representation of this exception
     */
    @Override
    public String toString() {
        return "\t***You must select the fair type!***\t";
    }

    /**
     * 
     * @return string representation of this exception
     */
    @Override
    public String getMessage() {
        return this.toString();
    }

}
