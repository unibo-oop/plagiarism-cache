package casim.ui.view.codi;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Defines the CoDi layer handler method interface.
 */
public interface CoDiLayerHandler {
    /**
     * Sets up the event handler that allows to change layer in CoDi simulation.
     * 
     * @return the event handler that handles the layer change.
     */
    EventHandler<KeyEvent> changeLayerHandler();
}
