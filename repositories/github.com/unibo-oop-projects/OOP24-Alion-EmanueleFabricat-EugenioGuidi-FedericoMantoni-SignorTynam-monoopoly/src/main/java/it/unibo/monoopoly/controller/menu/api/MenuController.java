package it.unibo.monoopoly.controller.menu.api;

import java.util.List;

import it.unibo.monoopoly.controller.main.api.MainController;

/**
 * Represents the controller responsible for initialize the model of the
 * application.
 */
public interface MenuController {

    /**
     * Create and initialize all classes of the model and call
     * {@link MainController} to start the game.
     * 
     * @param namePlayers the list of the names of the players written by the user
     */
    void goGame(List<String> namePlayers);

}
