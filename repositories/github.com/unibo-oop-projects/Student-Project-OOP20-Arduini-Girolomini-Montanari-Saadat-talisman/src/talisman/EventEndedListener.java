package talisman;

import java.util.EventListener;

/**
 * Used to notify when an event ends.
 * 
 * @author Alberto Arduini
 *
 */
public interface EventEndedListener extends EventListener {
    /**
     * Called on event end.
     */
    void eventEnded();
}
