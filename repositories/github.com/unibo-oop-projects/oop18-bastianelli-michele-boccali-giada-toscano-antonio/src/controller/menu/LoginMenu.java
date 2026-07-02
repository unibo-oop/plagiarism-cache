package controller.menu;

import java.util.Set;

import javax.crypto.Cipher;

import common.CommonStrings;
import common.EventBusConnection;
import common.MsgStrings;
import common.events.SceneEvent;
import controller.FileUserManager;
import enumerators.SceneType;
import javafx.scene.Scene;
import model.user.CurrentUser;
import model.user.User;
import model.user.UserData;
import model.user.UserProfile;
import utils.CryptDecryptString;
import view.menu.LoginView;

/**
 * The login menu controller class. This class create the LoginView and manage
 * MsgEvent.
 */
public class LoginMenu extends EventBusConnection implements Controller {

    private final LoginView view;

    /**
     * LoginMenu constructor. Create the LoginView view
     */
    public LoginMenu() {
        super();
        this.view = new LoginView(this);
    }

    @Override
    public final void sendMsg(final String msg) {
        switch (msg) {
        case MsgStrings.LOGIN:
            if (login()) {
                getBus().post(new SceneEvent(SceneType.MENU));
                getBus().unregister(this);
            }
            break;
        case MsgStrings.REGISTER:
            if (newProfile()) {
                getBus().post(new SceneEvent(SceneType.MENU));
                getBus().unregister(this);
            }
            break;
        default:
            break;
        }
    }

    @Override
    public final Scene getScene() {
        return this.view.getScene();
    }

    /**
     * Create a UserProfile with the credential given by input. The password is
     * crypted.
     * 
     * @return The UserProfile created.
     */
    private UserProfile getInputFromView() {
        final String key = CommonStrings.KEY;
        final String pass = CryptDecryptString.execCryptDecrypt(Cipher.ENCRYPT_MODE, key, view.getPassword());
        return new UserProfile(view.getUsername(), pass);
    }

    /**
     * Check if fields in LoginView are filled. 
     * @return true if username and password are present
     */
    private boolean isInputPresent() {
        return ((view.getUsername().isEmpty() || view.getPassword().isEmpty()) ? false : true);
    }

    private boolean login() {
        if (isInputPresent()) {
            final UserProfile user = getInputFromView();
            final Set<User> s = FileUserManager.load();

            if (!s.isEmpty()) {
                if (FileUserManager.searchUserProfile(s, user)) {
                    return true;
                } else {
                    view.setErrorMsg("Wrong username and/or password");
                }
            } else {
                view.setErrorMsg("Create a profile before login!");
            }
        } else {
            view.setErrorMsg("Username and Password can't be empty");
        }
        return false;
    }

    private boolean newProfile() {
        if (isInputPresent()) {
            final UserProfile profile = getInputFromView();
            final Set<User> s = FileUserManager.load();

            if (s.isEmpty() || !FileUserManager.searchUsername(s, profile.getUsername()).isPresent()) {
                CurrentUser.getInstance().setUser(new User(profile, new UserData()));
                FileUserManager.update(CurrentUser.getInstance().getUser());
                return true;
            } else if (FileUserManager.searchUsername(s, profile.getUsername()).isPresent()) {
                view.setErrorMsg("Profile already exist!");
            }
        } else {
            view.setErrorMsg("Username and Password can't be empty");
        }
        return false;
    }

}
