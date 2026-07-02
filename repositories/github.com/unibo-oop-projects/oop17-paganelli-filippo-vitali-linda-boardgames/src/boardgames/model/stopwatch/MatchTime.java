package boardgames.model.stopwatch;

/**
 * This interface implements basic methods to 
 * model time in the Stopwatch class.
 */
public interface MatchTime {
    
    /**
     * Method used to get the seconds, 
     * in a range from 0 to 59.
     * 
     * @return the current seconds
     */
    int getSeconds();
    
    
    /**
     * Method used to get the minutes, 
     * in a range from 0 to 59.
     * 
     * @return the current minutes
     */
    int getMinutes();
    
    
    /**
     * Method used to get the hours, 
     * in a range from 0 to 23.
     * 
     * @return the current hours
     */
    int getHours();
    
    
    /**
     * Method used check whether the values
     * (seconds/minutes) are correct and 
     * respect the standard range limits.
     * 
     * @param s, the current seconds
     * @param m, the current minutes
     */
    void checkMatchTime(final int s, final int m);
    
    
    /**
     * Method used to print the current
     * time with a simple string.
     * @return current time with a string
     */
    String getCurrentMatchTime();
}
