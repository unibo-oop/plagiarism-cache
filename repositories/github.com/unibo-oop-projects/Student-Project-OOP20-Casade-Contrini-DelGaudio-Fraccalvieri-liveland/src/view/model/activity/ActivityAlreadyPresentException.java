package view.model.activity;

public class ActivityAlreadyPresentException extends Exception {

    /**
     * Exception thrown when an activity already present in the simulation
     * environment is trying to be added.
     */
    private static final long serialVersionUID = -4311764479448220860L;


    /**
     * 
     * @return string representation of this exception
     */
    @Override
    public String toString() {
        return "Activity already present in the simulation! Please, try again.";
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

