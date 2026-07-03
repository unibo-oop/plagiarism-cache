package laterunner.model.collisions;

/**
 * World's events' manager.
 */
public interface WorldEventListener {

    /**
     * Reacts to a world's event.
     * 
     * @param e
     *          world's event
     */
    void notifyEvent(final WorldEvent e);
}
