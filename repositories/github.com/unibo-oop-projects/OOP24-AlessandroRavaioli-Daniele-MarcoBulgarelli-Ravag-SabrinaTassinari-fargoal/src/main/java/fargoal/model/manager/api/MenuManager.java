package fargoal.model.manager.api;

/**
 * Interface that work to make the menu screen
 * work at the best.
 */
public interface MenuManager extends SceneManager {
    /**
     * Method that change the selection from Exit
     * to Start Game.
     */
    void increaseSelected();

    /**
     * Method that change the selection from Start Game
     * to Exit.
     */
    void decreaseSelected();

    /**
     * Method that select the word the player is 
     * currently on. It starts the game if on "Start Game"
     * and otherwise close the game if on "Exit".
     */
    void select();
}
