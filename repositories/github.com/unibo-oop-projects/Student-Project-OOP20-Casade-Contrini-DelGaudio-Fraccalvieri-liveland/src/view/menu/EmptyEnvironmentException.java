package view.menu;

public class EmptyEnvironmentException extends Exception {

    /**
     * This exception is thrown if the user tries to start the simulation
     * with no any activity set.
     */
    private static final long serialVersionUID = -8750281820375052378L;

    /**
     * 
     * @return string representation of this exception
     */
    @Override
    public String toString() {
        return "You must add at least one activity to launch the simulation! ";
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
