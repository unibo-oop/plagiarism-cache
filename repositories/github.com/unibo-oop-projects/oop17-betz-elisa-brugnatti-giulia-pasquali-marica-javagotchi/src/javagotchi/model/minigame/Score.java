package javagotchi.model.minigame;

/**
 * 
 * @author marica
 *
 */
public interface Score extends java.io.Serializable {

    /**
     * Gets CurrentScore of current javagotchi.
     * 
     * @return current score of current javagotchi
     */
    int getCurrentScore();

    /**
     * Sets current score.
     */
    void setCurrentScore();

    /**
     * Gets BestScore of current javagotchi.
     * 
     * @return best score of current javagotchi
     */
    int getBestScore();

    /**
     * Gets the string of score.
     * 
     * @return a string of score
     */
    String getStringScore();

    /**
     * Gets the string of best score.
     * 
     * @return a string of best score
     */
    String getStringBestScore();
}
