package controller;

/**
 * The logics of the overlay view.
 */
public interface OverlayViewController {

    /**
     * @param playerName the name of the winner
     */
    void setEndGameDialog(String playerName);

    /**
     * @return the text to be showed
     */
    String getText();
}
