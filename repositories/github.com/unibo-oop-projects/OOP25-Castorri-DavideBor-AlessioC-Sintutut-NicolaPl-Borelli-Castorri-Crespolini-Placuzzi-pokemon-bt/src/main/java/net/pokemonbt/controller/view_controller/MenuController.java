package net.pokemonbt.controller.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.pokemonbt.controller.resources.LoadoutManager;

/**
 * Controller for the Menu scene layout.
 */
public final class MenuController {

    @FXML
    private Button loadBtn;

    /**
     * Constructor for the Menu Controller.
     */
    public MenuController() {
        // This constructor is intentionally empty for jar execution.
    }

    /**
     * Initializes menu.
     */
    @FXML
    public void initialize() {
        loadBtn.setDisable(LoadoutManager.saveFileMissing());
    }

    /**
     * Handles the battle button, loads the party selection scene.
     */
    @FXML
    public void onBattle() {
        SceneManager.setScene("/layouts/partySelect.fxml");
    }

    /**
     * Tries to Load a previous game, disables the button if it is not possible.
     */
    @FXML
    public void onLoading() {
        final var loadedBE = LoadoutManager.loadGame();
        if (loadedBE.isPresent()) {
            SceneManager.clearAllCache();
            SceneManager.setSceneWithBE("/layouts/battle.fxml", loadedBE.get());
        } else {
            loadBtn.setDisable(true);
        }
    }

    /**
     * Used by a {@link Button} to open the settings.
     */
    @FXML
    public void onSettings() {
        SceneManager.setScene("/layouts/settings.fxml");

    }

    /**
     * Closes the application.
     */
    @FXML
    public void onExit() {
        SceneManager.closeStage();
    }
}
