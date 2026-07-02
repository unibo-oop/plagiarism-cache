package view.controllers;

import java.io.File;
import java.io.IOException;

import controller.LoginControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.UserPlayer;
import model.UserPlayerImpl;
import view.DimensionCalculatorImpl;
import view.SceneType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * The fx controller for the login scene.
 *
 */
public class LoginFxImpl extends AbstractSceneController implements LoginFx, FxNormalScene {

    private static final double IMG_PERCENTAGE = 0.003;
    private static final double TITLE_PERCENTAGE = 0.1;
    private static final double FIELDS_PERCENTAGE = 0.06;
    private static final double BUTTON_PERCENTAGE = 0.07;
    private static final double ERROR_PERCENTAGE = 0.05;

    private final LoginControllerImpl loginController = new LoginControllerImpl(this);

    @FXML
    private Label errorLbl;

    @FXML
    private TextField txtPlayer;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private GridPane grid;

    @FXML
    private GridPane gridFields;

    @FXML
    private GridPane gridBtn;

    @FXML
    private GridPane gridLbl;

    @FXML
    private ImageView logo;

    private UserPlayer initPlayer() {
        return new UserPlayerImpl.PlayerBuilder().username(this.txtPlayer.getText())
                .password(this.txtPassword.getText()).build();
    }

    private boolean checkEmptyFields() {
        return this.txtPassword.getText().isBlank() || this.txtPlayer.getText().isBlank();
    }

    private void switchToMenu() {
        this.getView().switchScene(SceneType.MAIN_MENU.getFilePath());
    }

    @FXML
    private void loginPlayer() throws IOException { // NOPMD
        if (!this.checkEmptyFields()) {
            final UserPlayer player = this.initPlayer();
            this.loginController.loginUser(player);
        } else {
            this.errorLbl.setText(MessageError.EMPTY_FIELD.toString());
        }
    }

    @FXML
    private void signUpPlayer() { // NOPMD
        if (!this.checkEmptyFields()) {
            final UserPlayer player = this.initPlayer();
            this.loginController.registerUser(player);
        } else {
            this.errorLbl.setText(MessageError.EMPTY_FIELD.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showAlert() {
        final Alert alert = new Alert(AlertType.ERROR, MessageError.EXISTING_USERNAME.toString(), ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserPlayer(final File userFileName) {
        this.getController().initializePlayer(userFileName);
        this.switchToMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError() {
        this.errorLbl.setText(MessageError.INCORRECT_ENTRY.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();

        this.getRoot().getScene().getWindow().widthProperty().addListener((obs, oldVal, newVal) -> {
            calculator.setNodeFontStyle(grid, TITLE_PERCENTAGE, (double) newVal / 2);
            calculator.setNodeFontStyle(gridFields, FIELDS_PERCENTAGE, (double) newVal / 2);
            calculator.setNodeFontStyle(gridBtn, BUTTON_PERCENTAGE, (double) newVal / 2);
            calculator.setNodeFontStyle(gridLbl, ERROR_PERCENTAGE, (double) newVal / 2);
            calculator.setImageViewDimension(logo, IMG_PERCENTAGE, (double) newVal / 2);
        });

        final double halfStageSize = stage.getWidth() / 2;
        calculator.setNodeFontStyle(grid, TITLE_PERCENTAGE, halfStageSize);
        calculator.setNodeFontStyle(gridFields, FIELDS_PERCENTAGE, halfStageSize);
        calculator.setNodeFontStyle(gridBtn, BUTTON_PERCENTAGE, halfStageSize);
        calculator.setNodeFontStyle(gridLbl, ERROR_PERCENTAGE, halfStageSize);
        calculator.setImageViewDimension(logo, IMG_PERCENTAGE, halfStageSize);
    }
}
