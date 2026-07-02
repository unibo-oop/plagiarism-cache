package controller.entities;

import java.util.EventListener;

import common.events.MovementEvent;

/**
 * Represents an entity (such as a Player) that can receive an
 * {@link MovementEvent} from the user.
 */
public interface Comandable extends EventListener {

    /**
     * @param event the movement event received
     */
    void handleInputEvent(MovementEvent event);
}
