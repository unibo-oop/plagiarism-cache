package it.unibo.oop.crossline.game.attributes;

/**
 * This interface represents an entity that can be destroyed.
 */
public interface Destructible extends Physical {

    /**
     * Get if the current instance is queued for destruction.
     * @return a boolean representing the queue state, (true is enqueued)
     */
    boolean isQueuedForDestruction();

    /**
     * Enqueue the instance for destruction.
     */
    void queueForDestruction();

}
