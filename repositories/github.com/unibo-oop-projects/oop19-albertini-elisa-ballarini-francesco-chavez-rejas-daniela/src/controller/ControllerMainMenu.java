package controller;

import java.util.Optional;

import factory.EnumFactory;
import login.Player;

/**
 * Specific interface for the controller of the Main menu of the application.
 */
public interface ControllerMainMenu extends Controller {

    /**
     * @param typeController : type of controller that will take over the application.
     */
    void changeController(EnumFactory typeController);

    /**
     * @return an optional that contains the logged player if he is present.
     */
    Optional<Player> getPlayer();

}
