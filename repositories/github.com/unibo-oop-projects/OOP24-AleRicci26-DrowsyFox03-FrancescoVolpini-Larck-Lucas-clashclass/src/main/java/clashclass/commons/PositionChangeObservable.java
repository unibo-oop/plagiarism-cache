package clashclass.commons;

/**
 * Observable interface for position change notifications.
 * Follows the existing observer pattern used in the battle system.
 */
public interface PositionChangeObservable {
    /**
     * Adds an observer for position changes.
     *
     * @param observer the observer to add
     */
    void addPositionChangeObserver(PositionChangeObserver observer);

    /**
     * Removes an observer for position changes.
     *
     * @param observer the observer to remove
     */
    void removePositionChangeObserver(PositionChangeObserver observer);

    /**
     * Notifies all observers of a position change.
     *
     * @param oldPosition the previous position
     * @param newPosition the new position
     */
    void notifyPositionChange(Vector2D oldPosition, Vector2D newPosition);
}
