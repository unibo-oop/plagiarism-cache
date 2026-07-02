package migglione.view.api;

/**
 * Interface used as a base to SwingViewImpl implementation.
 */
public interface SwingView {
    /**
     * Method used to change scene with the CardLayout.
     * 
     * @param sceneName is the String name of the scene to set
     */
    void setScene(String sceneName);

    /**
     * To return the current scene being displayed.
     * 
     * @return a String, that is the current scene name
     */
    String getSceneName();

    /**
     * Functional method to quit the application.
     */
    void quit();

    /**
     * Method used to display the end game message.
     * 
     * @param winner is the name of the winner of the game
     * @param player is the name of the player
     * @param pScore is the score of the player
     * @param cScore is the score of the CPU
     */
    void endMessage(String winner, String player, Integer pScore, Integer cScore);

    /**
     * Method used on the first start of the application.
     * 
     * <p>
     * If it's the very first time the user starts the
     * application, a prompt suggesting them to visit
     * the tutorial scene appears
     * 
     * <p>
     * It will not appear again even if the user
     * doesn't immediatly goes to the tutorial
     */
    void showTutorialPrompt();
}
