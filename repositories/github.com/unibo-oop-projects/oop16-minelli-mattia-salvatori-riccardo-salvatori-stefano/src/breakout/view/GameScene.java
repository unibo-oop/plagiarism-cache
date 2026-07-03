package breakout.view;

import java.util.List;

import breakout.controller.Input;
import javafx.scene.image.Image;

/**
 * This interface defines all the main methods that a game scene must
 * implements.
 */
public interface GameScene {

    /**
     * Draw an image in the screen.
     * 
     * @param image
     *            The image to draw
     * @param positionX
     *            X position
     * @param positionY
     *            Y position
     * @param width
     *            width of rectangle to fit
     * @param height
     *            height of rectangle to fit
     */
    void draw(final Image image, final double positionX, final double positionY, final double width,
            final double height);

    /**
     * Update the value of the score.
     * 
     * @param newScore
     *            The new score to display
     */
    void updateScore(final int newScore);

    /**
     * Update the life count of the player.
     * 
     * @param newLife
     *            Lifes remaining
     */
    void updateLife(final int newLife);

    /**
     * Update the Level name.
     * 
     * @param newLevel
     *            The value of the new Level
     */
    void updateLevel(final String newLevel);

    /**
     * Shows a string in the center of the canvas for a certain amount of
     * time(in millisecond).
     * 
     * @param text
     *            the text to show
     * @param time
     *            the amount of time the text is visible
     */
    void showText(final String text, final long time);

    /**
     * This method determines what to do when the game is paused.
     */
    void pause();

    /**
     * This method determines what to do when the game is resumed.
     */
    void unPause();

    /**
     * @return the list of inputs from the user
     */
    List<Input> getInputs();

}
