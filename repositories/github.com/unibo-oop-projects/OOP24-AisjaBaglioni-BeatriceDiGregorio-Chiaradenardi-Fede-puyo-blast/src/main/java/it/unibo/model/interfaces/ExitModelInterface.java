package it.unibo.model.interfaces;

/**
 * Interface for the "Exit" model in the game.
 * It provides methods to check and modify the state of the "Exit" feature.
 */
public interface ExitModelInterface {

    /**
     * Returns whether the "Exit" button has been clicked.
     * 
     * @return true if the "Exit" button has been clicked, false otherwise.
     */
    boolean isExitClicked();

    /**
     * Sets the state of the "Exit" feature.
     * 
     * @param exitClicked true to indicate that the "Exit" button has been clicked, false otherwise.
     */
    void setExitClicked(boolean exitClicked);
}
