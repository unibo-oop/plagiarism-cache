package scores;

import java.io.IOException;

/**
 * 
 * Create Score.
 *
 */
public interface CreateScoreInterface {

    /**
     * 
     * @param hour  hour spent by the player
     * @param min   minutes spent by the player
     * @param sec   seconds spent by the player
     * @param level level played
     * @throws IOException if there are any problem
     * 
     * Get the old best time.
     */
    void calculateScores(int hour, int min, int sec, int level) throws IOException;

    /**
     * 
     * @param level      level played
     * @param firstTime  best time
     * @param secondTime second time
     * @param thirdTime  third time
     * @param hour       hour spent to play
     * @param min        minutes spent to play
     * @param sec        seconds spent to play
     * @param sum        time spent to play in seconds
     * 
     * Compared the time spent to play with best times, in case changed.
     */
    void changeBest(int level, String firstTime, String secondTime, String thirdTime, int hour, int min, int sec,
            int sum);
}
