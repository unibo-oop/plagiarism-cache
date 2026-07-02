package model.match;

import model.game_data.ImmutableGameData;

/**
 * This interface models all of the match's data.
 * All the data are in read-only format
 */
public interface ImmutableMatch {

    /**
     * Getter for the {@link ImmutableGameData} of the player one.
     * @return {@link ImmutableGameData}
     */
    ImmutableGameData getGameDataP1();

    /**
     * Getter for the {@link ImmutableGameData} of the player two.
     * @return {@link ImmutableGameData}
     */
    ImmutableGameData getGameDataP2();

    /**
     * Getter for the grid size.
     * @return the size of the grid.
     */
    int getGridSize();

    /**
     * @return if is the turn of the first player.
     */
    boolean isFirstPlayerTurn();

    /**
     * @return if the match is ended.
     */
    boolean isEnded();

    /**
     * @return {@link ImmutableGameData} of the current player.
     */
    ImmutableGameData getPlayer();

    /**
     * @return {@linkplain ImmutableGameData} of the enemy.
     */
    ImmutableGameData getEnemy();
}
