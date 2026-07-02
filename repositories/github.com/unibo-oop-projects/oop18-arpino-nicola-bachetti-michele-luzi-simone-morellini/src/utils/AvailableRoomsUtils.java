package utils;

import java.util.Optional;

import tmw.controller.world.Room1;
import tmw.controller.world.Room2;
import tmw.controller.world.WorldController;
import tmw.controller.world.WorldDispenser;

/**
 * Utilities class that contains all available rooms. Provides methods to get a
 * random room to load.
 *
 * @version 1.3
 */
public final class AvailableRoomsUtils {

    private AvailableRoomsUtils() {
    }

    /**
     * Static method usefully to get a specific roomDispenser based on a room
     * parameter.
     * 
     * @param room       {@link Rooms} room to load
     * @param controller {@link WorldController} world controller to which
     *                   communicate changes
     * @return {@link WorldDispenser} the specific dispenser for the room
     */
    public static Optional<WorldDispenser> getRoom(final Rooms room, final WorldController controller) {

        switch (room) {

        case ROOM1:
            return Optional.ofNullable(new Room1(controller, Rooms.ROOM1));

        case ROOM2:
            return Optional.ofNullable(new Room2(controller, Rooms.ROOM2));

        default:
            return Optional.empty();
        }
    }
}

