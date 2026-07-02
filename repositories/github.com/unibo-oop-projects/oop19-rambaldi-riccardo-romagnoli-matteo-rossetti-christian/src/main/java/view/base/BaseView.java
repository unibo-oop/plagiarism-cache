package view.base;

/**
 * BaseView setup the stage on which everything is drawn. This interface allows to show the main menu,
 * the choice menu, the leaderboard menu and the game screen.
 */
public interface BaseView {

    /**
     * Shows the main menu of the game.
     */
    void startMainMenu();

    /**
     * Shows the choice menu of the game.
     */
    void showChoiceMenu();

    /**
     * Starts the game.
     * @param mapChosen
     *      The map chosen by the player.
     * @param name
     *      The name of the player.
     * @param characterChosen
     *      The character chosen by the player.
     */
    void displayGame(String mapChosen, String name, String characterChosen);

    /**
     * Shows the leaderboard of the game.
     */
    void showLeaderboard();
}
