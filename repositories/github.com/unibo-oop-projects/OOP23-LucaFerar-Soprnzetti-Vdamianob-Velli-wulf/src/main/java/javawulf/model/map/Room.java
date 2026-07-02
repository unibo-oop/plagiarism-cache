package javawulf.model.map;

/**
 * Room implementation of area.
 */
public final class Room extends AbstractSpace {

    /**
     * Default rooms' tile-type.
     */
    public static final TileType DEFAULT_TYPE = TileType.ROOM;

    /**
     * 
     * @param width of the room
     * @param height of the room
     */
    public Room(final int width, final int height) {
        super(width, height);
    }

}
