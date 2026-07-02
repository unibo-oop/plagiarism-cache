package model.match;

import model.game_data.GameData;
/**
 * This interfaces extends {@link ImmutableModel}
 * in order to provide a way to interact with the match.
 */
public interface Match extends ImmutableMatch {

    /**
     * @return {@link GameData} of the player one
     */
    GameData getGameDataP1();

    /**
     * @return {@link GameData} of the player one
     */
    GameData getGameDataP2();

    /**
     * Handels the turn exchange process.
     */
    void changeTurn();

    /**
     * @return {@link GameData} of the current player.
     */
    GameData getPlayer();

    /**
     * @return {@link GameData} of the enemy.
     */
    GameData getEnemy();

}
