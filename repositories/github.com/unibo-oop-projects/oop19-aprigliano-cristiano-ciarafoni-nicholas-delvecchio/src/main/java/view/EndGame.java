package view;

import javafx.scene.control.Button;

/**
 * This interface models the EndGame view.
 */
public interface EndGame {

    /**
     * This method creates a new Button in the view when Mario collides.
     * with an obstacle object
     * @return the GameOver button
     */
    Button gameOver();

    /**
     * Get the GameOver button.
     * @return the GameOver button
     */
    Button getButton();

    /**
     * This method set the name and the score of the player.
     * @param name user name
     * @param playerScore player score
     */
    void setResult(String name, int playerScore);
}
