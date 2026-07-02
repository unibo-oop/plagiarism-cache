package model.room;

import java.util.List;

import model.hitbox.HitBox;
import model.inanimated.Door;
import model.inanimated.Inanimated;
import model.inanimated.Wall;
import utility.ImageType;

/**
 * ShopRoom implement.
 *
 */
public class ShopRoom extends AbstractRoom {

    private final List<Inanimated> items;

    /**
     * Constructor of the room.
     * 
     * @param h
     *            ShopRoom's HitBox.
     * @param doors
     *            ShopRoom's doors.
     * @param items
     *            List of power up.
     * @param walls
     *            list of the wall of the room.
     * @param backImg
     *            background image.
     */
    public ShopRoom(final HitBox h, final List<Door> doors, final List<Inanimated> items, final List<Wall> walls,
            final ImageType backImg) {
        super(h, doors, walls, backImg);
        this.items = items;
    }

    /**
     * Getter for list of power up items in the shop.
     * @return List of items that you can buy.
     */
    public List<Inanimated> getItems() {
        return this.items;
    }

}
