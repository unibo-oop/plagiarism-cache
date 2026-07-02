package model.leaderboard;

import java.io.Serializable;
/*
 * This interface represent the correct method to create the
 * player of the game. */
public interface PlayerBuilder extends Serializable {

    /**
     * Used to set the player's alias, alias can be contain all type of words.
     * @param alias - used to set the player's alias.
     * @return the alias property.
     */
    PlayerBuilder alias(String alias);

    /**
     * Used to set the player's score.
     * @param score - Used to set the player's score
     * @return the score property.
     */
    PlayerBuilder score(int score);

    /**
     * Used to set the player's life.
     * @param life - Used to set the player's life
     * @return the life property.
     */
    PlayerBuilder life(int life);

    /**
     * Used to set the player's max life property.
     * @param value - Used to set the player's max life property
     * @return the max life property.
     */
    PlayerBuilder maxLife(int value);
    /**
     * Used to build a correct version of Player Class.
     * @return a correct version of Player whit validation data.
     */
    Player build();
}
