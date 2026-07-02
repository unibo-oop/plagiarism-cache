package it.unibo.monoopoly.controller.main.api;

import java.util.Optional;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.view.main.api.MainView;

/**
 * The main controller of the application.
 */
public interface MainController {

    /**
     * Gets the actual {@link ControllerState}.
     * 
     * @return the actual {@link ControllerState}
     */
    ControllerState getControllerState();

    /**
     * Gets the actual {@link Event}.
     * 
     * @return the actual {@link Event} from the {@link MainModel}.
     */
    Optional<Event> getActualEvent();

    /**
     * Initialize nextPhase of the game creating the {@link ControllerState}
     * according to the actual {@link ModelState}.
     */
    void nextPhase();

    /**
     * Gets the data to update the {@link MainView}.
     * 
     * @return the data to update the {@link MainView}
     */
    ViewUpdateDTO getViewUpdateData();
}
