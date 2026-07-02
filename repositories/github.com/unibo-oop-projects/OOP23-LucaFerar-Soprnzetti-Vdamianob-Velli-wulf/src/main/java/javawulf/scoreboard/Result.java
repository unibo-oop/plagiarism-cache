package javawulf.scoreboard;

/**
 * Result represents the score of a finished game.
 */
public interface Result {

    /**
     * 
     * @return The Username of the player
     */
    String getUserName();

    /**
     * 
     * @return The Score of the finished game
     */
    int getScore();

    /**
     * 
     * @return If the player got all the amulets and did not die
     */
    boolean hasWon();

}
