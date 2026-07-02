package bzzbomber.model;

import java.io.IOException;
import java.util.List;

/**
 * contains the score of the top player.
 */
public interface TopScore {
    /**
     * Add at the list a new Score(name, point).
     * 
     * @param score
     *            the Score that the player has done.
     */
    void addScore(Score score);

    /**
     * sort the list from high to lower points .
     * 
     * @return list of top Score.
     */
    List<Score> getScore();

    /**
     * 
     * @param fileName
     *            the name of the file to save the top Score.
     * @throws IllegalStateException
     *             Signals that a method has been invoked at an illegal or
     *             inappropriate time.
     * 
     * @throws IOException
     *             exceptions produced by failed or interrupted I/O operations.
     * 
     */
    void saveOnFile(String fileName) throws IllegalStateException, IOException;

    /**
     * 
     * @param fileName
     *            the name of the file to save the top Score.
     */
    void readFromFile(String fileName);
}
