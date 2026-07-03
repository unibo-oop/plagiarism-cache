package controller;

import java.util.Map;
import static controller.UserInfo.PASSWORD;
import static controller.UserInfo.USERNAME;
import model.User;
import model.UserImpl;
import view.AddUserViewImpl;
/**
 * 
 * AddUserControllerImpl.
 * add a new controller
 *
 */
public class AddUserControllerImpl implements AddUserController {


    private AddUserViewImpl addView;
/**
 * register user clicked.
 * @param user
 * user
 */
    public void registerUserCliecked(final User user) {
                MainControllerImpl.manager.add(user);
                this.newRegisterLogin();
    }
/**
 * new register login.
 */
    public void newRegisterLogin() {
        this.addView.clearLogin();
        }

    /**
     * update.
     */
    public void update() {
        this.addView = new AddUserViewImpl(this);
        this.addView.init();
    }
    /**
     * @param map
     * map
     */
    public void confirmRegistration(final Map<UserInfo, String> map) {
        System.out.println(map.toString()); 
        if (this.checkFields(map)) {
                User user;
                try {
                    user = new UserImpl(map.get(USERNAME), map.get(PASSWORD));
                    MainControllerImpl.manager.add(user);
                    this.addView.showMessage("User successfully created", this.addView);
                    this.addView.quit();

                } catch (Exception e) {
                    this.addView.showMessage("Error creating user", this.addView);
                }
        }
    }
    private boolean checkFields(final Map<UserInfo, String> userChoose) {
        for (final Map.Entry<UserInfo, String> entry : userChoose.entrySet()) {
            if (!entry.getKey().getValidity().test(entry.getValue())) {
                this.addView.showMessage("Username or Password invalid", this.addView);
                return false;
            }
        } return true;
    }
}
