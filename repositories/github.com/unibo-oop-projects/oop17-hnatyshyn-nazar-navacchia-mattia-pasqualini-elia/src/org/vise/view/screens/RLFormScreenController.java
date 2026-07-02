package org.vise.view.screens;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.vise.controller.RLFormController;
import org.vise.view.FXEnvironment;
import org.vise.view.FXEnvironment.PlayerMode;
import org.vise.view.UI;
import org.vise.view.UIRLForm;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * Rapresent the Controller of the RLForm Screen. It's a key component in JavaFX development.
 *
 */
public class RLFormScreenController implements UI, UIRLForm {
    /**
     * 
     * @author nazarhnatyshyn
     *          A form could be in login or registration mode.
     */
    public enum FormMode { LOGIN, REGISTRATION }
    private FormMode formMode;
    private final RLFormController rlfController;
    private final FXEnvironment environment;

    @FXML
    private Button btnSubmit, btnChangeFormMode;

    @FXML
    private TextField txtUsername, txtEmail, txtPassword;

    @FXML
    private ImageView imageViewLogo;

    /**
     * Constructor.
     * @param environment
     *          The environment.
     * @param rlfController
     *          Form controller.
     */
    public RLFormScreenController(final FXEnvironment environment, final RLFormController rlfController) {
        super();
        this.environment = environment;
        this.environment.loadScreen(FXMLScreens.RLFORM, this);
        this.rlfController = rlfController;
    }

    /**
     * Submit form and login/register.
     * @throws IOException 
     *          IO Exception.
     * @throws JSONException
     *          JSON Exception.
     */
    public void btnSubmitClicked() throws JSONException, IOException {
        try {
            if (this.rlfController.isNetworkingAvailable()) {
                if (this.formMode == FormMode.LOGIN) {
                    if (!this.txtEmail.getText().toString().isEmpty() && !this.txtPassword.getText().toString().isEmpty()) {
                        this.rlfController.login(this.txtEmail.getText().toString(), this.txtPassword.getText().toString());
                    } else {
                        showNotification("Tutti i campi sono obbligatori");
                    }
                } else {
                    if (!this.txtUsername.getText().toString().isEmpty() && !this.txtEmail.getText().toString().isEmpty() && !this.txtPassword.getText().toString().isEmpty()) {
                        this.rlfController.registration(this.txtUsername.getText().toString(), this.txtEmail.getText().toString(), this.txtPassword.getText().toString());
                    } else {
                        showNotification("Tutti i campi sono obbligatori");
                    }
                }
            } else {
                showNotification("Errore di connessione al server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change form mode Login - Registration, Registration - Login.
     */
    public void changeFormMode() {
        if (this.formMode == FormMode.LOGIN) {
            changeFormModeComponents(FormMode.REGISTRATION, "Registrati", "Hai già un Account? Effettua il Login.");
            this.txtUsername.requestFocus();
            this.environment.setPlayerMode(PlayerMode.ONLINE);
        } else {
            changeFormModeComponents(FormMode.LOGIN, "Login", "Non hai un Account? Registrati.");
            this.txtEmail.requestFocus();
        }
    }

    /**
     * 
     * @return
     *          A current FormMode.
     */
    public FormMode getFormMode() {
        return this.formMode;
    }

    /**
     * Display player in offline mode.
     * @throws IOException 
     *          IOException.
     */
    @FXML
    public void goToOfflineMode() throws IOException {
        this.environment.setPlayerMode(PlayerMode.OFFLINE);
        this.clearInputFields();
        try {
            this.environment.displayScreen(FXMLScreens.PLAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UI#show()
     */
    @Override
    public void show() {
        try {
            this.environment.displayScreen(FXMLScreens.RLFORM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UI#showNotification(java.lang.String)
     */
    @Override
    public void showNotification(final String textNotification) {
        final Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Vise Music");
        alert.setContentText(textNotification);
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        // Adding custom css to alert.
        final DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("Dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("Dialog");
        // Show alert and wait.
        alert.showAndWait();
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UIRLForm#goToPlayerAfterLogin()
     */
    @Override
    public final void goToPlayerAfterLogin() {
        this.environment.setPlayerMode(PlayerMode.ONLINE);
        try {
            this.environment.displayScreen(FXMLScreens.PLAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.vise.view.UI#preShowOperation()
     */
    @Override
    public void preShowOperation() {
        imageViewLogo.setImage(new Image(RLFormScreenController.class.getResource("/icons/logo.png").toExternalForm()));
        this.formMode = FormMode.LOGIN;
        try {
            this.rlfController.automaticLogin();
        } catch (JSONException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        this.clearInputFields(false);
    }

    private void clearInputFields(final Boolean saveUserEmail) {
        if (!saveUserEmail) {
            this.txtEmail.clear(); 
        }
        this.txtUsername.clear(); 
        this.txtPassword.clear();
    }

    private void changeFormModeComponents(final FormMode formType, final String btnSubmitText, final String btnChangeFormModeText) {
        this.formMode = formType;
        this.btnSubmit.setText(btnSubmitText);
        this.btnChangeFormMode.setText(btnChangeFormModeText);
        this.txtUsername.setDisable(!this.txtUsername.isDisable());
        this.txtUsername.setEditable(!this.txtUsername.isEditable());
    }
}
