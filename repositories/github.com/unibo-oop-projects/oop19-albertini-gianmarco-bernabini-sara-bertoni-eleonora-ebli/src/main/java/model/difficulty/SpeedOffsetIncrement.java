package model.difficulty;

/**
 * 
 * This class gives values to increment speed at each call.
 *
 */
public interface SpeedOffsetIncrement {

    /**
     * Method to get the next increment.
     * 
     * @return the increment.
     */
    double getIncrement();
}
