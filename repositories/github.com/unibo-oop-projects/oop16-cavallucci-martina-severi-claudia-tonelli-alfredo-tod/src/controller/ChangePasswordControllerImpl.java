package controller;

import java.security.NoSuchAlgorithmException;

import model.PasswordUtility;
import model.User;
import view.ChangePasswordViewImpl;

/**
 * 
 * change password.
 *
 */
public class ChangePasswordControllerImpl implements ChangePasswordController {
    private ChangePasswordViewImpl changeView;
    /**
     * 
     * @param oldPass
     * oldPass
     * @param newPass
     * newPass
     */
    public void confirmChange(final String oldPass, final String newPass) {
                User user;
                String pw = null;
                try {
                    pw = PasswordUtility.hashPassword(oldPass);
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                }
                user = MainControllerImpl.manager.currentUser();
                if (MainControllerImpl.manager.currentUser().getPassword().equals(pw)) {
                    try {
                        MainControllerImpl.manager.changePassword(user, newPass);
                        this.changeView.showMessage("Password modificata", this.changeView);
                        this.changeView.quit();
                    } catch (NoSuchAlgorithmException e) {
                        this.changeView.showMessage("Error. Please controll that the passwords are valid.", this.changeView);
                        e.printStackTrace();
                    }
                } else {
                    this.changeView.showMessage("Password errata", this.changeView);
                }
                this.changeView.clearFields();
    }
    /**
     * .
     */
    public void update() {
        String name;
        name = MainControllerImpl.manager.currentUser().getUsername();
        this.changeView = new ChangePasswordViewImpl(this, name);
        this.changeView.init();
    }
}
