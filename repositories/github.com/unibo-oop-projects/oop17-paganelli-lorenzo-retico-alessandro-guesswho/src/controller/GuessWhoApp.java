package controller;

import view.StartingView;

/**
 * Contains main.
 */
public final class GuessWhoApp {

    /**
     * Allows to start application.
     * @param args ignored
     */
    public static void main(final String[] args) {
        new StartingView(); //new ControllerImpl(Modality.MULTIPLAYER, Difficulty.HARD);
    }

    private GuessWhoApp() {
    }

}
