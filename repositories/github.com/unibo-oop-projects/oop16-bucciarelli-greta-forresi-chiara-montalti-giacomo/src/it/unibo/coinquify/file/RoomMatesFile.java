package it.unibo.coinquify.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import it.unibo.coinquify.roommates.model.RoomMate;

/**
 * Read and write room mates.
 */
public final class RoomMatesFile {
    private RoomMatesFile() {
    }

    /**
     * 
     * @param roomMates
     *            of house
     * @throws IOException
     *             if there're problems with the file
     */
    public static void writeRoomMates(final Set<RoomMate> roomMates) throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FilePathsConstants.ROOM_MATE)));

        for (final RoomMate rm : roomMates) {
            oos.writeObject(rm);
        }
        oos.writeObject(null);
        oos.close();
    }

    /**
     * 
     * @return set of all room mates
     */
    public static Set<RoomMate> readRoomMates() {
        final Set<RoomMate> roomMates = new HashSet<>();
        try {
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FilePathsConstants.ROOM_MATE)));
            for (Object obj = ois.readObject(); obj != null; obj = ois.readObject()) {
                roomMates.add((RoomMate) obj);
            }
            ois.close();
            return roomMates;
        } catch (Exception e) {
            return roomMates;
        }
    }
}
