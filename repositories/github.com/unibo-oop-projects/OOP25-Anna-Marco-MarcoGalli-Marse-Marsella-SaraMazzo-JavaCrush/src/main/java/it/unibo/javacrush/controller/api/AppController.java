package it.unibo.javacrush.controller.api;

import java.util.Optional;

/**
 * Interface representing the main controller of the application, 
 * responsible for managing the interactions between the menu and the levels.
 */
public interface AppController {

    /**
     * Handle a specific event and executes the corresponding command.
     * 
     * @param event the event to handle
     */
    void notifyEvent(Event event);

    /**
     * Get the current GameController, if present.
     * 
     * @return an Optional containing the current GameController, or an 
     *      empty Optional if there is no active game
     */
    Optional<GameController> getCurrentGameController();

}
