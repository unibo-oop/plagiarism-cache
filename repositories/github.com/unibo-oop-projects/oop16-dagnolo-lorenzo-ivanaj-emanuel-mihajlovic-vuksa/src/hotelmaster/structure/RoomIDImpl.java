package hotelmaster.structure;

/**
 * Basic implementation of {@link RoomID}.
 */
public class RoomIDImpl implements RoomID {

    private final int floor;
    private final int numberOnFloor;
    private static final char ROOM_FLOOR_SEPARATOR = ':';

    RoomIDImpl(final int floor, final int numberOnFloor) throws IllegalArgumentException {
        if (floor < 0 || numberOnFloor < 0) {
            throw new IllegalArgumentException("A RoomID cannot have negative fields");
        }
        this.floor = floor;
        this.numberOnFloor = numberOnFloor;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public int getNumberOnFloor() {
        return numberOnFloor;
    }

    @Override
    public String getFullID() {
        final StringBuilder fullID = new StringBuilder();
        final int highestRoomNumber = Hotel.instance().getFloorView().values().stream().max(Integer::compare).get()
                .intValue();
        final int highestFloorNumber = Hotel.instance().getFloorView().size();
        int roomZeros = (int) (highestRoomNumber == 0 ? 1 : (Math.log10(highestRoomNumber) + 1))
                - (int) (numberOnFloor == 0 ? 1 : (Math.log10(numberOnFloor) + 1));
        int floorZeros = (int) (highestFloorNumber == 0 ? 1 : (Math.log10(highestFloorNumber) + 1))
                - (int) (floor == 0 ? 1 : (Math.log10(floor) + 1));
        final String trailingZero = "0";
        while (floorZeros > 0) {
            floorZeros--;
            fullID.append(trailingZero);
        }
        fullID.append(floor);
        fullID.append(ROOM_FLOOR_SEPARATOR);
        while (roomZeros > 0) {
            roomZeros--;
            fullID.append(trailingZero);
        }
        fullID.append(numberOnFloor);
        return fullID.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + floor;
        result = prime * result + numberOnFloor;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RoomIDImpl)) {
            return false;
        }
        final RoomIDImpl other = (RoomIDImpl) obj;
        return this.toString().equals(other.toString());
    }

    @Override
    public int compareTo(final RoomID other) {
        return this.toString().compareTo(other.toString());
    }

}
