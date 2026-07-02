package it.tbt.model.roomlink;

import it.tbt.model.world.api.Room;
import it.tbt.model.entities.SpatialEntity;

import java.util.function.Predicate;

/**
 * Interface for RoomLink object, which connect two or more Room objects.
 */
public interface RoomLink extends SpatialEntity {
    /**
     * @param predicate
     * @return a Room based on the predicate passed.
     */
    Room getRoomOnPredicate(Predicate<Room> predicate);

}
