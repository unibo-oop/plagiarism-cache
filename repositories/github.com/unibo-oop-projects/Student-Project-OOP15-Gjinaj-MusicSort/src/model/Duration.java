package model;


/**
 * Class for song's duration.
 * @author Rrok Gjinaj
 *
 */

public  class Duration{
	
    private final int min;
    private final int sec;
    /**
     * Constructor the seti min and sec
     * @param min
     * @param sec
     */
    public Duration(final int min, final int sec) {
        this.min = min;
        this.sec = sec;
    }
    /**
     * get minutes
     * @return min int
     */
    public int getMin(){
        return this.min;
    }
    /**
     * get second
     * @return sec int
     */
    public int getSec(){
        return this.sec;
    }
}
