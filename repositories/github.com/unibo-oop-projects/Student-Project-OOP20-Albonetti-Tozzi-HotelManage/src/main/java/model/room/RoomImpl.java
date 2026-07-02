package model.room;


public class RoomImpl implements Room {

    private final int roomNumber;
    private final RoomType type;


    public RoomImpl(final int roomNumber, final RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
    }

    @Override
    public final int getNumber() {
        return this.roomNumber;
    }

    @Override
    public final RoomType getType() {
       return this.type;
    }

    @Override
    public final String toString() {
        return "RoomImpl [roomNumber=" + roomNumber + ", type=" + type + "]";
    }
}
