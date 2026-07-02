package view.menu;

public class VisitorsOutOfBoundException extends IllegalStateException {

    /**
     * This exception is raised if the user tries to set visitors number
     * below 1 or above 300.
     */
    private static final long serialVersionUID = 6821339703443669838L;

    /**
     * 
     * @return string representation of this exception
     */
    @Override
    public String toString() {
        return "Visitors' number must be between 1 and 300! Please, try again ";
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
