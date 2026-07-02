package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.utilities.Action;
import it.unibo.oop.utilities.Direction;

/**
 * Interface for a view.
 */
public interface View {

    /**
     * @return the {@link LevelInterface}
     */
    LevelInterface getLevelView();

    /**
     * @return the {@link Direction}
     */
    Direction getMovement();

    /**
     * @return the {@link Action}
     */
    Action getAction();

    /**
     * @param state
     *            object used to choose which view should be shown.
     */
    void showView(AppState state);

    /**
     * Hides the view or some parts of it.
     */
    void hideView();

    /**
     * Shows the previous view.
     */
    void showLast();

    /**
     * Resets view or some parts of it.
     */
    void reset();
}