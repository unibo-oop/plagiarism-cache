package model.data;

import java.io.Serializable;

/**
 * Represents an high score realized by a player.
 */
public interface HighScore extends Serializable {

    /**
     * 
     * @return the name of the player that realizes the high score.
     */
    String getName();

    /**
     * 
     * @return the score realized.
     */
    int getScore();

}
