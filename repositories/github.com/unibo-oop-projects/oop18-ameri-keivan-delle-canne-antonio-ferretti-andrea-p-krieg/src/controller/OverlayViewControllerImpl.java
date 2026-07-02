package controller;

/**
 * The logics of the overlay view.
 */
public class OverlayViewControllerImpl implements OverlayViewController {

    private String textToBeShowed;

    /** {@inheritDoc} **/
    @Override
    public String getText() {
        return this.textToBeShowed;
    }

    /** {@inheritDoc} **/
    @Override
    public void setEndGameDialog(final String playerName) {
        this.textToBeShowed = "CONGRATULATIONS\nPlayer " + playerName + " won!";
    }

}
