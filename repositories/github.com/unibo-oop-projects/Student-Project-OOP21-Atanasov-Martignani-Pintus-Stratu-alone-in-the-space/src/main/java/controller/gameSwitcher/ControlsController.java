package controller.gameSwitcher;

import java.io.IOException;

import controller.inputController.InputController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import utilities.InputCommands;

/**
 * 
 */
public class ControlsController extends BasicFXMLController {

    @FXML
    private Text keyToSetText;

    @FXML
    private Text controlsUpText;

    @FXML
    private Text controlsDownText;

    @FXML
    private Text controlsLeftText;

    @FXML
    private Text controlsRightText;

    @FXML
    private Text controlsAttackText;

    @FXML
    private Text controlsPowerUpText;

    private InputController inputController;
    private KeyCode lastKeyPressed;

    /**
     * Constructor.
     *
     * @param sceneController
     * @throws IOException
     */
    public ControlsController(SceneController sceneController) throws IOException {
        super(sceneController);
    }

    /**
     * Initialize the GUI.
     */
    @FXML
    private void initialize() {
        this.inputController = super.getSceneController().getInputController();
        this.keyToSetText.setText(KeyCode.A.toString());
        this.refreshControls();
    }

    /**
     * Show the chosen input on the GUI.
     */
    private void refreshControls() {
        this.controlsUpText.setText(this.inputController.getKeyMapped(InputCommands.UP).toString());
        this.controlsDownText.setText(this.inputController.getKeyMapped(InputCommands.DOWN).toString());
        this.controlsLeftText.setText(this.inputController.getKeyMapped(InputCommands.LEFT).toString());
        this.controlsRightText.setText(this.inputController.getKeyMapped(InputCommands.RIGHT).toString());
        this.controlsAttackText.setText(this.inputController.getKeyMapped(InputCommands.ATTACK).toString());
        this.controlsPowerUpText.setText(this.inputController.getKeyMapped(InputCommands.POWER_UP).toString());
    }

    /**
     * Changes the up movement key.
     * 
     * @param event
     */
    @FXML
    void changeUpKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommands.UP);
        this.refreshControls();
    }

    /**
     * Changes the down movement key.
     * 
     * @param event
     */
    @FXML
    void changeDownKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommands.DOWN);
        this.refreshControls();
    }

    /**
     * Changes the left movement key.
     * 
     * @param event
     */
    @FXML
    void changeLeftKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommands.LEFT);
        this.refreshControls();
    }

    /**
     * Changes the up movement key.
     * 
     * @param event
     */
    @FXML
    void changeRightKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommands.RIGHT);
        this.refreshControls();
    }

    /**
     * Changes the attack key.
     * 
     * @param event
     */
    @FXML
    void changeAttackKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommands.ATTACK);
        this.refreshControls();
    }

    /**
     * Changes the power up key.
     * 
     * @param event
     */
    @FXML
    void changePowerUpKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommands.POWER_UP);
        this.refreshControls();
    }

    /**
     * Detects the key pressed to be set as new.
     * 
     * @param event
     */
    @FXML
    void detectKey(final KeyEvent event) {
        this.lastKeyPressed = event.getCode();
        this.keyToSetText.setText(event.getCode().toString());
    }

    /**
     * Returns to main menu GUI.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void returnToMenu(final ActionEvent event) throws IOException {
        // super.buttonPressedSound();
        super.getSceneController().switchToMainMenu();
    }

}
