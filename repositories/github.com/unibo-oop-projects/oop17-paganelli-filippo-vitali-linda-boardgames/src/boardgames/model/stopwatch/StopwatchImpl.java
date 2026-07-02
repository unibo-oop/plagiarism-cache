package boardgames.model.stopwatch;

/**
 * This class generates a stopwatch thanks to the
 * interface Stopwatch. Models the concept of time.
 */
public class StopwatchImpl implements Stopwatch {
    
    private MatchTime myTime;
    
    /**
     * Constructor for the class.
     */
    public StopwatchImpl(final MatchTime time) {
        this.myTime = time;
    }

    @Override
    public MatchTime getCurrentTime() {
        return this.myTime;
    }

    @Override
    public void advanceOneSecond() {
        this.myTime = new MatchTimeImpl(myTime.getHours(), myTime.getMinutes(), myTime.getSeconds()+1);
    }
}
