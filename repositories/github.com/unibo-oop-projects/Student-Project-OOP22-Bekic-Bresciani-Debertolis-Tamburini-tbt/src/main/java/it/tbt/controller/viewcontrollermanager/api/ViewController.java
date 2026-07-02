package it.tbt.controller.viewcontrollermanager.api;

import it.tbt.model.command.api.Command;
import java.util.List;

/**
 * Interface for the input hub for the current view/state.
 */

public interface ViewController extends InputListener {
    /**
     * @return the list of Commands intercepted.
     */
    List<Command> getCommands();

    /**
     * Cleans the Commands this ViewController currently has.
     */
    void clean();
}
