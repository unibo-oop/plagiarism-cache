package boardgames.model.stopwatch;

/**
 * This interface creates the methods for a 
 * stopwatch, using the MatchTimeImpl class.
 */
public interface Stopwatch {

    /**
     * This metod gives us the current time.
     * 
     * @return the current time
     */
    MatchTime getCurrentTime();
    
    
    /**
     * This method make the clock tick once,
     * advancing of one second each time.
     */
    void advanceOneSecond();
}
