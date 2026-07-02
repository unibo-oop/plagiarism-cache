package view.menu.fair;

/**
 * This exception is thrown when an activity is trying to be added
 * in the simulation environment with parameters in a wrong format.
 */
public class WrongParametersException extends NumberFormatException {

    private static final long serialVersionUID = 5723831699450759311L;


    /**
     * 
     * @return string representation of this exception
     */
    @Override
    public String toString() {
        return "Any of the parameters inserted are not in the right form!\n ";
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
