package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.viewmanager.ViewManager;

/**
 * CreditsController to control Credits scene. It uses items from javafx.
 */
public class CreditsController extends AbstractController {

    @FXML
    private Button back;

    /**
     * Initialize the CreditsController.
     * 
     * @param loader the {@link ViewManager} shared by all the controllers.
     */
    public CreditsController(final ViewManager loader) {
        super(loader);
    }

    /*
     * It reloads the menu scene in the gui.
     */
    @FXML
    private void backToMenu() {
        this.getViewManager().openMenuScene();
    }
}
