package controller.Room;

import java.util.ArrayList;
import java.util.List;

import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomType;

public class ControllerRoomImpl implements ControllerRoom {

    private static final  int SINGLEROOMNUMBER = 5;
    private static final int DOUBLEROOMNUMBER = 5;
    private static final int TRIPLEROOMNUMBER = 5;
    private static final int QUARTUPLEROOMNUMBER = 5;

    private List<Room> rooms = new ArrayList<>();

    /**
     * This method initialize all the room.
     */
    private void initialize() {
        int counter = 1;
        for (; counter <= SINGLEROOMNUMBER; counter++) {
            Room room = new RoomImpl(counter, RoomType.SINGLE);
            this.rooms.add(room);
        }
        for ( ; counter <= SINGLEROOMNUMBER + DOUBLEROOMNUMBER; counter++) {
            Room room = new RoomImpl(counter, RoomType.DOUBLE);
            this.rooms.add(room);
        }
        for ( ; counter <= SINGLEROOMNUMBER + DOUBLEROOMNUMBER + TRIPLEROOMNUMBER; counter++) {
            Room room = new RoomImpl(counter, RoomType.TRIPLE);
            this.rooms.add(room);
        }
        for ( ; counter <= SINGLEROOMNUMBER + DOUBLEROOMNUMBER + TRIPLEROOMNUMBER + QUARTUPLEROOMNUMBER; counter++) {
            Room room = new RoomImpl(counter, RoomType.QUARTUPLE);
            this.rooms.add(room);
        }

        Room suite = new RoomImpl(counter, RoomType.SUITE);
        this.rooms.add(suite);
    }

    public ControllerRoomImpl() {
        this.initialize();
        }

    @Override
    public final List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public final Room getRoom(final int number) {
        return this.rooms.get(number - 1);
    }


    @Override
    public final boolean isPresent(final int number) {
        return (this.getRoom(number) != null);
    }

}
