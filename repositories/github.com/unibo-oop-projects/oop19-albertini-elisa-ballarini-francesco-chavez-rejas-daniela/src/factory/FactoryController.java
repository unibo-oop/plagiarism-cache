package factory;

import controller.Controller;
import controller.CustomPieceImpl;
import controller.LoginImpl;
import controller.MainMenuImpl;
import controller.ProfileImpl;
import controller.SettingsImpl;
import manager.ControllerManager;
import playcontroller.PlayControllerImpl;

/**
 * Static Factory class that creates a new controller based on the type of the
 * parameter.
 *
 */
public final class FactoryController {

    private FactoryController() {
    }

    /**
     * @param type    : select which type of controller will be created.
     * @param manager : the controller manager of the application.
     * @return a new controller based on the type parameter.
     */
    public static Controller createController(final EnumFactory type, final ControllerManager manager) {

        Controller chosen;

        switch (type) {

        case MENU:
            chosen = new MainMenuImpl(manager);
            break;
        case SETTINGS:
            chosen = new SettingsImpl(manager);
            break;
        case CUSTOM:
            chosen = new CustomPieceImpl(manager);
            break;
        case GAME:
            chosen = new PlayControllerImpl(manager);
            break;
        case LOGIN:
            chosen = new LoginImpl(manager);
            break;
        case PROFILE:
            chosen = new ProfileImpl(manager);
            break;
        default:
            chosen = new MainMenuImpl(manager);
            break;
        }
        return chosen;
    }

}
