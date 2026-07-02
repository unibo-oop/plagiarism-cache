package tmw.controller.menu;

/**
 * Interface that manage action and display related with object in fxml file
 * EndLevelView.
 *
 */
public interface EndLevelController {

    /**
     * set the scene of end level screen.
     */
    void start();

    /**
     * show the final score obtained during the game.
     */
    void finalScore();

    /**
     * go back to main menu.
     */
    void goToMenu();

    /**
     * go to the next level or to the credit scene.
     */
    void next();

}
