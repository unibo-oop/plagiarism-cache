package it.unibo.burraco;

import javax.swing.SwingUtilities;

import it.unibo.burraco.view.scenes.OnConfigurationCompleteListener;
import it.unibo.burraco.view.scenes.SetUpMenuViewImpl;
import it.unibo.burraco.view.scenes.StartMenuViewImpl;

/**
 * Main entry point of the Burraco application.
 * Manages the initial lifecycle of the program, transitioning from the 
 * Main Start Menu to the Game Setup Configuration.
 */
public final class BurracoApp {

    /**
     * Private constructor to prevent instantiation of this utility/main class.
     */
    private BurracoApp() { }

    /**
     * Application entry point.
     *
     * @param args command line arguments (ignored)
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(BurracoApp::showStartMenu);
    }

    private static void showStartMenu() {
        new StartMenuViewImpl(BurracoApp::showSetupMenu).display();
    }

    private static void showSetupMenu() {
        new SetUpMenuViewImpl(new OnConfigurationCompleteListener() {

            /**
             * Triggered when the user fills out player names, target points, 
             * and confirms the launch of a new match.
             * 
             * @param targetScore the points threshold needed to win the game session
             * @param nameP1      the name chosen for Player 1
             * @param nameP2      the name chosen for Player 2
             */
            @Override
            public void onConfigComplete(final int targetScore,
                                         final String nameP1,
                                         final String nameP2) {
                new GameSession(nameP1, nameP2, targetScore).start();
            }

            /**
             * Triggered when the user presses the "Back" button inside the setup view,
             * redirecting them safely back to the primary landing landing screen.
             */
            @Override
            public void onBackClicked() {
                showStartMenu();
            }
        }).display();
    }
}
