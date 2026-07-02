package tmw.controller.menu;

/**
 * Interface that controls action related to the fxml file MenuView.
 * 
 *
 */
public interface MenuController {

    /**
     * Set the menu scene in the stage.
     */
    void start();

    /**
     * permit to chose one of the game level.
     */
    void toLevelMenu();

    /**
     * permit to access the options screen.
     */
    void toOption();

    /**
     * permit to leave the game.
     */
    void quit();

}
