package clashclass.commons;

import clashclass.ecs.GameObject;

/**
 * Observer interface for position change notifications.
 */
@FunctionalInterface
public interface PositionChangeObserver {
    /**
     * Called when a GameObject's position changes.
     *
     * @param gameObject the GameObject that moved
     * @param oldPosition the previous position
     * @param newPosition the new position
     */
    void onPositionChanged(GameObject gameObject, Vector2D oldPosition, Vector2D newPosition);
}
