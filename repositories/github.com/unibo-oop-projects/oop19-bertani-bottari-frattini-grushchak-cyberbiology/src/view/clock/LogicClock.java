package view.clock;

/**
 * Interface related to a clock that takes as its initial instant the instant
 * of time of the instantiation of his object.
 *
 */
public interface LogicClock {

    /**
     * Method that returns the string of seconds elapsed between the initial instant and the current instant.
     * @return the string in "nn" format with the seconds
     */
    String getSecond();

    /**
     * Method that returns the string of minutes elapsed between the initial instant and the current instant.
     * @return  the string in "nn" format with the minutes
     */
    String getMinute();

    /**
     * method that calculates the seconds elapsed from the initial instant of the program to the current instant.
     * @return number of seconds
     */
    int getIntSecond();

}
