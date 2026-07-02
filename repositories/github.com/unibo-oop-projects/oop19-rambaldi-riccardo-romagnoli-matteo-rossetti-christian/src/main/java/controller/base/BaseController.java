package controller.base;

/**
 * BaseController is the controller associated to {@link BaseView}. It allows the communication between the base view
 * and all the menus in order to change them as needed. It also has to start and close the program.
 */
public interface BaseController {

    /**
     * Starts the {@link MainMenuView}.
     */
    void startProgram();

    /**
     * Starts the {@link ChoiceMenuView}.
     */
    void startChoiceMenu();

    /**
     * Starts the {@link GameView}.
     * @param mapChosen
     *      The map chosen by the player.
     * @param name
     *      The name of the player.
     * @param characterChosen
     *      The character chosen by the player.
     */
    void startGame(String mapChosen, String name, String characterChosen);

    /**
     * Starts the {@link LeaderboardView}.
     */
    void startLeaderboard();

    /**
     * Closes the program.
     */
    void closeProgram();
}
