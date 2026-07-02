package controller.ui;

import application.Battleships;
import controller.Controller;
import controller.users.AccountManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.dialog.DialogType;
import view.scene.SceneName;

/**
 * Represents the controller of account creation and removal.
 *
 */
public class ProfileController {

    @FXML
    private Button signInButton, cancelAccountButton, profileBackButton;

    @FXML
    private TextField signInUsername, signInPassword, removeUsername, removePassword;

    private final AccountManager accountMng;
    private final Controller controller;


    public ProfileController() {
        this.accountMng = Battleships.getController().getAccountManager();
        this.controller = Battleships.getController();
    }

    @FXML
    public final void turnBack() {
        Battleships.getController().changeScene(SceneName.MAIN);
    }

    @FXML
    public final void accountSignIn() {
        if (!signInUsername.getText().equals("") && !signInPassword.getText().equals("")) {
            try {
                this.accountMng.createAccount(String.valueOf(signInUsername.getText()).trim(), 
                        String.valueOf(signInPassword.getText()).trim());
                controller.launchDialog(DialogType.INFORMATION, "Account Created", "Your account has been created.", null);
            } catch (Exception e) {
                controller.launchDialog(DialogType.INFORMATION, "Account Create Exception", e.getMessage(), null);
            }
        } else if (signInUsername.getText().equals("") && signInPassword.getText().equals("")) {
            controller.launchDialog(DialogType.WARNING, "Account Creation", "Please, insert username and password!", null);
        } else {
            controller.launchDialog(DialogType.INFORMATION, "Account Creation", "Please, insert a valid username and password", 
                    "Username and/or password may be absent.");
        }
        signInUsername.clear();
        signInPassword.clear();
    }

    @FXML
    public final void accountDelete() {
        if (!removeUsername.getText().equals("") && !removePassword.getText().equals("")) {
            try {
                this.accountMng.removeAccount(String.valueOf(removeUsername.getText()).trim(), 
                        String.valueOf(removePassword.getText()).trim());
                controller.launchDialog(DialogType.INFORMATION, "Account Removed", "Your account has been deleted.", null);
            } catch (Exception e) {
                controller.launchDialog(DialogType.INFORMATION, "Account Remove Exception", e.getMessage(), null);
            }
        } else if (removeUsername.getText().equals("") && removePassword.getText().equals("")) {
            controller.launchDialog(DialogType.WARNING, "Account Creation", "Please, insert existing username and password", null);
        } else {
            controller.launchDialog(DialogType.INFORMATION, "Account Creation", "Please, insert an existing username and/or a valid password", null);
        }
        removeUsername.clear();
        removePassword.clear();
    }

}
