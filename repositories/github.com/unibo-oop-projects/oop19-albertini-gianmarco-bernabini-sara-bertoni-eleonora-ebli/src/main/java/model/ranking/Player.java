package model.ranking;

import model.components.GameTimer;

/**
 * 
 * Useful functionalities to create a player with a nickname, his final score and his game timer.
 *
 */
public interface Player extends Comparable<Player> {

    /**
     * Returns the nickname of this player.
     * 
     * @return the nickname of the player
     */
    String getNickname();

    /**
     * Returns the time of the game of this player.
     * 
     * @return the time of the game
     */
    GameTimer getGameTimer();

    /**
     * Returns the final score of this player.
     * 
     * @return the final score
     */
    int getFinalScore();

    /**
     * Returns the current position of this player in the leaderboard.
     * 
     * @return the current position of the player
     */
    int getPosition();
}
