package it.unibo.model;

import it.unibo.model.interfaces.ExitModelInterface;

/**
 * This class represents the model for handling the "Exit" button in the game to return in the menu.
 * It manages the state of whether the exit action has been triggered or not.
 * The "Exit" action can be toggled by the user or the game.
 */
public class ExitModel implements ExitModelInterface {

    /**
     * A flag indicating whether the "Exit" button has been clicked.
     */
    private boolean exitClicked;

    /**
     * Constructor that initializes the "Exit" feature as not clicked by default.
     */
    public ExitModel() {
        this.exitClicked = false;
    }

    /**
     * Returns whether the "Exit" action has been triggered (clicked).
     *
     * @return true if the "Exit" button has been clicked, false otherwise.
     */
    @Override
    public boolean isExitClicked() {
        return exitClicked;
    }

    /**
     * Sets the state of the "Exit" feature.
     *
     * @param exitClicked true if the "Exit" button has been clicked, false otherwise.
     */
    @Override
    public void setExitClicked(boolean exitClicked) {
        this.exitClicked = exitClicked;
    }
}
