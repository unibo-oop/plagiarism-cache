package net.pokemonbt.controller.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import net.pokemonbt.controller.resources.GameSession;
import net.pokemonbt.controller.resources.LoadoutManager;
import net.pokemonbt.controller.resources.GameSession.GameSpeed;
import net.pokemonbt.utility.Refreshable;

/**
 * Controller for the view of the settings.
 */
public final class SettingsController implements Refreshable {
    @FXML
    private Button deleteFileButton;
    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> enemySelector;
    @FXML
    private ComboBox<String> gameSpeed;

    @FXML
    private ComboBox<Integer> enemyTeamSize;

    @FXML
    private TextField playerNameField;
    @FXML
    private TextField enemyNameField;

    /**
     * Set's the view's elements.
     */
    public void initialize() {
        // set-up enemy's difficulty selector.
        enemySelector.getItems()
        .addAll(GameSession.getDifficultyList());

        // set-up the game's speed
        for (final var speed : GameSpeed.values()) {
            gameSpeed.getItems().add(speed.displayAs());
        }

        // set-up enemy's team size selector.
        for (
            int i = GameSession.getMinTeamSize(); 
            i <= GameSession.getMaxTeamSize(); 
            i++
        ) {
            enemyTeamSize.getItems().add(i);
        }
    }

    /**
     * Used by any input object to signal to the view that a value for the 
     *      session has changed.
     */
    @FXML
    public void valueModified() {
        saveButton.setDisable(false);
    }

    /**
     * Called by the button: it deletes the save file.
     */
    @FXML
    public void onDelete() {
        deleteFileButton.setDisable(
            LoadoutManager.deleteSaveFile()
        );
    }

    /**
     * Closes the current scene and goes back to the menu.
     */
    @FXML
    public void onClose() {
        if (SceneManager.isInBattle()) {
            SceneManager.setScene("/layouts/battle.fxml");
        } else {
            SceneManager.clearAllCache();
            SceneManager.setScene("/layouts/menu.fxml");
        }
    }

    /**
     * Used by the "save" button actually change the values
     *      of the {@link GameSession} and write them in the settings' file.
     */
    @FXML
    public void saveSettings() {
        if (
            !playerNameField.getText().isBlank()
            && !enemyNameField.getText().isBlank()
        ) {
            GameSession.setPlayerName(playerNameField.getText());
            GameSession.setEnemyName(enemyNameField.getText());

            GameSession.setSpeed(
                GameSpeed.stringToSpeed(gameSpeed.getValue())
                .orElseGet(() -> GameSpeed.NORMAL)
            );

            GameSession.setEnemyTeamSize(enemyTeamSize.getValue());

            GameSession.setEnemyDifficulty(
                enemySelector.getValue()
            );

            saveButton.setDisable(LoadoutManager.saveSettings());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        // sets the button based on the presence of the file.
        deleteFileButton.setDisable(
            LoadoutManager.saveFileMissing()
        );

        gameSpeed.setValue(GameSession.getSpeed().displayAs());

        playerNameField.setText(GameSession.getPlayerName());
        enemyNameField.setText(GameSession.getEnemyName());

        enemySelector.setDisable(SceneManager.isInBattle());
        enemySelector.setValue(GameSession.getCurrentEnemyDifficulty());

        enemyTeamSize.setDisable(SceneManager.isInBattle());
        enemyTeamSize.setValue(GameSession.getEnemyTeamSize());
    }
}
