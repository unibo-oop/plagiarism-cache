package spacesurvival.model.worldevent;

/**
 * 
 * Listener for every event of the world.
 *
 */
public interface WorldEventListener {

    /**
     * Notify the world that an event occurred.
     * @param ev the evento tonotify
     */
    void notifyEvent(WorldEvent ev);
}
