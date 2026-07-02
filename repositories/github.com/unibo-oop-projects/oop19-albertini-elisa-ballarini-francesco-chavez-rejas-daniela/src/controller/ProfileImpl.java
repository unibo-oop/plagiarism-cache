package controller;

import java.util.Optional;

import login.Player;
import manager.ControllerManager;
import view.ViewProfileImpl;

/**
 * Class that implements {@linkplain ControllerProfile}.
 * 
 * @see ControllerProfile
 */
public class ProfileImpl implements ControllerProfile {

    private final ControllerManager manager;
    private final Player currentPlayer;

    /**
     * @param manager : controller manager of the application.
     */
    public ProfileImpl(final ControllerManager manager) {
        this.manager = manager;
        this.currentPlayer = this.manager.getPlayer().get();
        this.manager.setView(new ViewProfileImpl(this));
    }

    @Override
    public final Player getPlayer() {
        return this.currentPlayer;
    }

    /**
     * @throws IllegalArgumentException if the field of country is blank or age is < 0.
     */
    @Override
    public final void saveChanges(final String newCountry, final int newAge) { //throws IllegalArgumentException {
        if (newCountry.isBlank() || newAge < 0) {
            throw new IllegalArgumentException();
        } else {
            if (!this.currentPlayer.getCountry().equals(newCountry) || this.currentPlayer.getAge() != newAge) {
                this.currentPlayer.setCountry(newCountry);
                this.currentPlayer.setAge(newAge);
                this.currentPlayer.writeOnFile();
            }
        }
    }

    @Override
    public final void logout() {
        this.manager.setPlayer(Optional.empty());
        this.backToMenu();
    }

    @Override
    public final ControllerManager getManager() {
        return this.manager;
    }

}
