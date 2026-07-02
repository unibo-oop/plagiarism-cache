package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * PlayerGraphic interface.
 *
 */
public interface PlayerGraphic {

    /**
     * Starts player's UP animation.
     */
    void charUp();

    /**
     * Starts player's DOWN animation.
     */
    void charDown();

    /**
     * Starts player's LEFT animation.
     */
    void charLeft();

    /**
     * Starts player's RIGHT animation.
     */
    void charRight();

    /**
     * Sets player's starting position in the grid.
     * 
     * @param column
     *            in which the player has to be placed in the grid
     * @param row
     *            in which the player has to be placed in the grid
     * @param grid
     *            of the current level
     */
    void playerSetup(int column, int row, GridPane grid);

    /**
     * Stops the player's current animation.
     */
    void stopAnimation();

    /**
     * Getter of the player's current image based on his last direction.
     * 
     * @return charFrames
     */
    ImageView getCharFrames();
}
