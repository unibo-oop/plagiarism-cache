package scores;

/**
 * 
 * EditFile.
 *
 */
public interface EditFileInterface {

    /**
     * 
     * @param oldHour Hour of last best time
     * @param oldMin  minutes of last best time
     * @param oldSec  seconds of last best time
     * @param newHour new best hour
     * @param newMin  new best minutes
     * @param newSec  new best seconds
     * 
     * Change the old time with the new when there are already time.
     */
    void changeFileRanking(int oldHour, int oldMin, int oldSec, int newHour, int newMin, int newSec);

    /**
     * 
     * @param oldTime time in seconds of last best
     * @param newTime time in seconds of new best
     * 
     * Change the time of support file, where is all in seconds, when there are
     * already times.
     */
    void changeFileTimes(int oldTime, int newTime);

    /**
     * 
     * @param old  String that shows that there are no best time
     * @param hour new best hour
     * @param min  new best minutes
     * @param sec  new best seconds
     * 
     * Change the old time with the new when there are no time.
     */
    void changeFileFirstRanking(String old, int hour, int min, int sec);

    /**
     * 
     * @param old String that shows that there are no times
     * @param sum new best time in seconds
     * 
     * Change the support file when there are no time.
     */
    void changeFileFirstTimes(String old, int sum);
}
