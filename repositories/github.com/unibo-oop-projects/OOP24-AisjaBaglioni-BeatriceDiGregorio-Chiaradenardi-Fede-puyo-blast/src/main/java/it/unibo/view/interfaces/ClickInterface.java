package it.unibo.view.interfaces;

import it.unibo.model.Point2DI;

/**
 * This interface is used to check whether the user
 * has click inside the area of a button in the game.
 */
public interface ClickInterface {
    /**
     * Checks whether the player's click is inside the area of a button.
     * 
     * @param pos the position where the player clicked
     * @return @return {@code true} if the button is clicked, {@code false}
     *         otherwise.
     */
    boolean isClicked(Point2DI pos);

    /**
     * This method is triggered when a button is clicked,
     * depending on the specific implementation of the button.
     */
    void doAction();

}