package controller;

import model.LoginModel;
import model.LoginModelImpl;
import model.UserPlayer;
import view.controllers.LoginFxImpl;

/**
 * Login controller class. Handles the logic for login access
 * 
 *
 */
public class LoginControllerImpl implements LoginController {

    private final LoginModel model;
    private final LoginFxImpl view;

    /**
     * Constructor of {@link LoginControllerImpl}.
     * @param view the login view
     */
    public LoginControllerImpl(final LoginFxImpl view) {
        this.model = new LoginModelImpl();
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loginUser(final UserPlayer player) {
        if (this.model.loginUser(player)) {
            this.view.setUserPlayer(this.model.getUserFile());
        } else {
            this.view.showError();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerUser(final UserPlayer player) {
        if (this.model.registerUser(player)) {
            this.view.setUserPlayer(this.model.getUserFile());
        } else {
            this.view.showAlert();
        }
    }

}
