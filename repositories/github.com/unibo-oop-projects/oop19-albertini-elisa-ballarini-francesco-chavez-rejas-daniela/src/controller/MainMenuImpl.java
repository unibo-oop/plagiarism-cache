package controller;

import java.util.Optional;

import factory.EnumFactory;
import login.Player;
import manager.ControllerManager;
//import view.ViewMainMenu;
import view.ViewMainMenuImpl;

/**
 * Class that implements {@link ControllerMainMenu}.
 * 
 * @see ControllerMainMenu
 */
public class MainMenuImpl implements ControllerMainMenu {

    private final ControllerManager manager;

    /**
     * @param controllerManager : controller manager of the application.
     */
    public MainMenuImpl(final ControllerManager controllerManager) {
        this.manager = controllerManager;
        this.manager.setView(new ViewMainMenuImpl(this));
    }

    @Override
    public final void changeController(final EnumFactory typeController) {
        this.manager.setController(typeController);
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
