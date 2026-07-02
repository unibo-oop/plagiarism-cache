package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import login.LoginTypes;
import login.Player;
import login.PlayerImpl;
import manager.ControllerManager;
import utility.UtilsPassword;
import utility.UtilsPlayer;
import view.View;
import view.ViewLoginImpl;
import view.ViewNewUserLoginImpl;
import view.ViewUserLoginImpl;

/**
 * Class that implements {@link ControllerLogin}.
 * 
 * @see ControllerLogin
 */
public class LoginImpl implements ControllerLogin {

    private View loginView;
    private final ControllerManager manager;

    /**
     * @param controllerManager : the controller manager of the application.
     */
    public LoginImpl(final ControllerManager controllerManager) {
        this.loginView = new ViewLoginImpl(this);
        this.manager = controllerManager;
        this.manager.setView(loginView);
    }

    @Override
    public final void switchView(final LoginTypes type) {
        switch (type) {
        case OLD:
            this.loginView = new ViewUserLoginImpl(this);
            break;
        case NEW:
            this.loginView = new ViewNewUserLoginImpl(this);
            break;
        case GENERAL:
            this.loginView = new ViewLoginImpl(this);
        default:
            break;
        }
        this.manager.setView(this.loginView);
    }

    @Override
    public final void tryLog(final String checkUsername, final String checkPassword) {
        if (UtilsPlayer.isPlayerInDatabes(checkUsername)) {
            final Player checkPlayer = UtilsPlayer.readPlayerFromJSON(checkUsername);
            try {
                if (UtilsPassword.authenticate(checkPassword, checkPlayer.getEncryptedPassword(),
                        checkPlayer.getSalt())) {
                    this.manager.setPlayer(Optional.of(checkPlayer));
                    this.backToMenu();
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                System.out.println("[LoginImpl] Fail UtilsPassword.authenticate() method");
                e.printStackTrace();
            }
        }
    }

    /**
     * @throws IllegalStateException if there is another player with the same name in the database.
     * 
     * @throws IllegalArgumentException if the age of the new player is minor of zero.
     */
    @Override
    public final void createPlayer(final String newName, final String password, final String newCountry, final int age) {
        if (UtilsPlayer.isPlayerInDatabes(newName)) {
            throw new IllegalStateException("There is a same player in the database");
        } else if (age <= 0) {
            throw new IllegalArgumentException();
        } else {
            final Player newPlayer = new PlayerImpl(newName, password, newCountry, age);
            newPlayer.writeOnFile();
            this.manager.setPlayer(Optional.of(newPlayer));
        }
    }

    @Override
    public final Optional<Player> getPlayer() {
        return this.manager.getPlayer();
    }

    @Override
    public final ControllerManager getManager() {
        return this.manager;
    }

}
