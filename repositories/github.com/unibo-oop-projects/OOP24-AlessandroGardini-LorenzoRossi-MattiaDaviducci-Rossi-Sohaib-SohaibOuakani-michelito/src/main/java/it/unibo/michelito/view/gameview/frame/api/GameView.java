package it.unibo.michelito.view.gameview.frame.api;

import it.unibo.michelito.util.GameObject;

import java.util.Set;

/**
 * Interface that models a GameView.
 * It is used to display the game.
 * It is used to get the keys pressed by the user.
 */
public interface GameView {
    /**
     * Method that returns whether the view is showing.
     *
     * @return {@code true} if the view is showing, {@link false} otherwise.
     */
    boolean isViewShowing();

    /**
     * Method that sets the visibility of the view.
     *
     * @param show {@code true} if the view should be visible, {@link false} otherwise.
     */
    void setViewVisibility(boolean show);

    /**
     * Method that displays the game.
     *
     * @param gameObjects set of {@link GameObject} to display.
     * @param lives number of lives.
     * @param levelNumber number of the level.
     */
    void display(Set<GameObject> gameObjects, int lives, int levelNumber);

    /**
     * Method that returns the keys pressed by the user.
     *
     * @return the keys pressed by the user.
     */
    Set<Integer> getPressedKeys();
}
