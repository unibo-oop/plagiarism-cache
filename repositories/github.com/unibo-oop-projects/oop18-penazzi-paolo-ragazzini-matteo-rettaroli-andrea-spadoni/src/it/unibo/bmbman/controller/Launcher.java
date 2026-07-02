package it.unibo.bmbman.controller;
import it.unibo.bmbman.view.MainMenuView;
/**
 * start the application.
 *
 */
public final class Launcher {
    private Launcher() { };
    /**
     * Main method of application.
     * @param args parameters
     */
    public static void main(final String[] args) {
         new MainMenuView().loadMainMenuView();
    }
}
