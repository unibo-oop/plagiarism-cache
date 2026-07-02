package it.unibo.scat.view.menu.api;

/**
 * Interface for the main menu panel.
 * Defines methods to switch between different menu screens.
 */
public interface MenuPanelInterface {

    /**
     * Displays the leaderboard screen.
     */
    void showLeaderboardPanel();

    /**
     * Displays the credits screen.
     */
    void showCreditsPanel();

    /**
     * Displays the settings screen.
     */
    void showSettingsPanel();

    /**
     * Displays the username screen.
     */
    void showUsernamePanel();
}
