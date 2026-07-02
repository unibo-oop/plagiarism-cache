package controller.command;

import model.character.Character;
import model.player.Player;
/**
 * Modeling interface for the Action of the game. 
 */
public interface Action {
    /**
     * Gets the Question.
     * @return the Question
     */
    String getQuestion();
    /**
     * Check if a character meets some requirements.
     * @param character the character to check
     * @return true if meets, false otherwise
     */
    boolean filter(Character character);
    /**
     * Update players model.
     * @param player the player 
     * @param answer the answer given to the question
     */
    void updateModel(Player player, boolean answer);
    /**
     * Ask if the game is finish.
     * @return true if the game is finish, false otherwise
     */
    boolean isFinish();
}
