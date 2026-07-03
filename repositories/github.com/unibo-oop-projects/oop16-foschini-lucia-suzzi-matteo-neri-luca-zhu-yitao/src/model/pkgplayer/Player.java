package model.pkgplayer;

/**
 * Interface that specify some method for the Player.
 * 
 *
 */
public interface Player {
    /**
     * Method used to get the current player username.
     * 
     * @return Player's name
     */
    String getName();

    /**
     * Method used to get the current player password.
     * 
     * @return Player's password
     */
    String getPass();

    /**
     * Method that returns the player reached level.
     * 
     * @return Player's level
     */
    int getLevel();

    /**
     * Method that updates the current player reached level.
     * 
     * @param newLevel
     *            update level to set
     */
    void setLevel(int newLevel);

    /**
     * Method used to obtain the best score.
     * 
     * @return Player's best score across all levels
     */
    int getBestScore();

    /**
     * Method used to update the user best score.
     * 
     * @param value
     *            new score to set
     */
    void updateScore(final int value);

}
