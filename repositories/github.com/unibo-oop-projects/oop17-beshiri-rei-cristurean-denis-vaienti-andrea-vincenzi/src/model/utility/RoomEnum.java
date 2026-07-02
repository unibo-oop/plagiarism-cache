package model.utility;

/**
 * Enumeration that represent rooms in game.
 */
public enum RoomEnum {

    /**
     * Boss room.
     */
    BOSSROOM(2),

    /**
     * Main room.
     */
    MAINROOM(0),

    /**
     * Shop room.
     */
    SHOPROOM(1);

    private final int x;

    /**
     * @param x the index.
     */
    RoomEnum(final int x) {
        this.x = x;
    }

    /**
     * @return the index of the room.
     */
    public int getIndex() {
        return x;
    }
}
