package startmenu.view;

import javafx.stage.Stage;
import startmenu.controller.MainController;

/**
 * Manages the View part of the Starting menu of the game. It communicates with
 * the controller in order to use the input in the proper way set by the model.
 */
public interface ViewController {

    /**
     * 
     * @param controller the controller of the view
     */
    void setController(MainController controller);

    /**
     * Draws the GUI and refresh it depending on the button selected.
     */
    void draw();

    /**
     * When clicked new game has to communicate it to the controller.
     */
    void newGame();

    /**
     * Opens the Panel where choose the Game Mode.
     */
    void chooseGameMode();

    /**
     * Shows the credits for this game.
     */
    void credits();

    /**
     * @return the Stage where the menu is being set (useful to start the game)
     */
    Stage getPrimaryStage();

}
