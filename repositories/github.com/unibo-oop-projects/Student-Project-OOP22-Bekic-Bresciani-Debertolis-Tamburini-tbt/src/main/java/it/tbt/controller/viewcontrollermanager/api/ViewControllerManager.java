package it.tbt.controller.viewcontrollermanager.api;

import it.tbt.controller.modelmanager.ModelState;
import it.tbt.model.command.api.Command;
import it.tbt.model.GameState;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the ViewControllerManager, which manages the Views and the Controllers.
 */

public interface ViewControllerManager {

    /**
     * @return an Optional List of Commands. If it is empty no Command has been added.
     */
    Optional<List<Command>> getCommands();

    /**
     * Renders the View associated to the GameState and provides it with modelState for the rendering of its data.
     * The hasChanged parameter notifies the manager if the GameState provided has been already rendered thus
     * it is not needed to do additional steps in order to create the view.
     * @param gameState the current game state
     * @param modelState the model state modeling the current game state
     * @param hasChanged boolean representing if a new View is needed or just rendering is sufficient
     */
    void renderView(GameState gameState, ModelState modelState, Boolean hasChanged);

    /**
     * Cleans the commands of the current controller.
     */
    void cleanCommands();

}
