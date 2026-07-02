package model.room;

import java.util.List;

import model.hitbox.HitBox;
import model.inanimated.Door;
import model.inanimated.Wall;
import utility.ImageType;

/**
 * Abstract class for rooms.
 *
 */
public abstract class AbstractRoom implements Room {

    private final HitBox h;
    private final List<Door> doors;
    private final List<Wall> walls;
    private final ImageType backImg;

    /**
     * Constructor for a generic room.
     * 
     * @param h
     *            HitBox of room.
     * @param doors
     *            list of actual room's doors.
     * @param walls
     *            list of the walls in this room.
     * @param backImg
     *            background image.
     */
    public AbstractRoom(final HitBox h, final List<Door> doors, final List<Wall> walls, final ImageType backImg) {
        this.h = h;
        this.doors = doors;
        this.walls = walls;
        this.backImg = backImg;
    }

    /**
     * return the list of actual room's doors.
     */
    @Override
    public List<Door> getDoors() {
        return doors;
    }

    /**
     * return the room's HitBox.
     */
    @Override
    public HitBox getHitBox() {
        return h;
    }

    /**
     * Get walls of the room.
     */
    @Override
    public List<Wall> getWalls() {
        return walls;
    }

    /**
     * Getter for background image of the room.
     */
    @Override
    public ImageType getBackgroundImage() {
        return backImg;
    }
}
