package model;

import view.TrainingArea;

/**
 * The model interface of the whole game.
 * 
 */
public interface Model {

    /**
     * Set the logged in user as the player of the game session.
     * 
     * @param player 
     *               the current player.
     */
    void setUserPlayer(UserPlayer player);

    /**
     * Set the selected training area.
     * 
     * @param area 
     *             the selected {@link TrainingArea}.
     */
    void setArea(TrainingArea area);

    /**
     * Getter for the selected difficultyLevel.
     * 
     * @return the selected {@link DifficultyLevel}.
     */
    DifficultyLevel getDifficultyLevel();

    /**
     * Set the selected minigame and difficulty.
     * 
     * @param minigame 
     *                 the selected minigame
     * 
     * @param difficultyLevel 
     *                        the selected {@link DifficultyLevel}.
     */
    void setMiniGame(String minigame, DifficultyLevel difficultyLevel);

    /**
     * Add the score to the current user.
     * 
     * @param score
     *          the score to add
     */
    void addScore(Integer score);

    /**
     * Get the current user.
     * 
     * @return
     *          the current user
     */
    UserPlayer getUserPlayer();
}
