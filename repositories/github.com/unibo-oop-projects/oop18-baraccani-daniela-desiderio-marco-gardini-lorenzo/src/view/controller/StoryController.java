package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.viewmanager.ViewManager;

/**
 * StoryController to control Story scene. It uses items from javafx.
 */
public class StoryController extends AbstractController {

    @FXML
    private Button back;

    @FXML
    private Button newplayer;

    /**
     * Initialize the StoryController.
     * 
     * @param loader the {@link ViewManager} shared by all the controllers.
     */
    public StoryController(final ViewManager loader) {
        super(loader);
    }

    /*
     * It reloads the menu scene in the gui.
     */
    @FXML
    private void backToMenu() {
        this.getViewManager().openMenuScene();
    }

    /*
     * It opens the Character scene.
     */
    @FXML
    private void newPlayer() {
        this.getViewManager().openCharacterScene();
    }

}
