package it.tbt.model.roomlink;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.world.api.Room;
import it.tbt.model.world.interaction.Interactable;
import it.tbt.model.entities.SpatialEntity;
import it.tbt.model.entities.SpatialEntityImpl;
import it.tbt.model.party.IParty;

import java.util.List;
import java.util.function.Predicate;

/**
 * Simple implementation of the RoomLink interface where there are 2 Rooms.
 */
public class RoomLinkImpl extends SpatialEntityImpl implements RoomLink, Interactable {

    private final Room room1;
    private final Room room2;

    /**
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     * @param firstRoom
     * @param secondRoom
     */

    @SuppressFBWarnings(value = {
            "EI2"
    },
    justification = "the room link needs the real Room object because it is then given to the Party.")
    public RoomLinkImpl(final String name,
                        final int x,
                        final int y,
                        final int width,
                        final int height,
                        final Room firstRoom,
                        final Room secondRoom) {
        super(name, x, y, width, height);
        this.room1 = firstRoom;
        this.room2 = secondRoom;

    }

    /**
     * This method just chooses the room to travel to based on a predicate.
     */

    @Override
    public Room getRoomOnPredicate(final Predicate<Room> predicate) {
        return List.of(room1, room2).stream().filter(l -> predicate.test(l)).findFirst().get();
    }

    /**
     * The RoomLink changes the Party's Room, it does nothing if any other object tries to interact with it.
     */
    @Override
    public void onInteraction(final SpatialEntity interactionTrigger) {
        if (interactionTrigger instanceof IParty) {
            ((IParty) interactionTrigger).setCurrentRoom(
                    getRoomOnPredicate(l -> l != ((IParty) interactionTrigger).getCurrentRoom()));
        }

    }
}

