package it.unibo.isaccoop.model.collision;

import it.unibo.isaccoop.model.room.Room;

/**
 * Interface for handle collision.
 */
public interface CollisionCheck {
    /**
     *  Method for handle collision.
     *
     *  @param r reference to room.
     */
    void handleCollision(Room r);
}
