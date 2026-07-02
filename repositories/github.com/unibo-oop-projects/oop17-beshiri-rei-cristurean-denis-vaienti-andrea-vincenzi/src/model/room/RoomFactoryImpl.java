package model.room;

import java.util.List;
import model.animated.Enemy;
import model.hitbox.HitBox;
import model.inanimated.Button;
import model.inanimated.Door;
import model.inanimated.Inanimated;
import model.inanimated.Wall;
import utility.ImageType;

/**
 * Implement Room Factory.
 *
 */
public class RoomFactoryImpl implements RoomFactory {

    /**
     * Create the MainRoom.
     */
    @Override
    public Room createMainRoom(final HitBox hitbox, final List<Door> doors, final Button button,
            final List<Wall> walls) {
        return new MainRoom(hitbox, doors, button, walls, ImageType.BACKGROUND_MAIN_ROOM);
    }

    /**
     * Create the BossRoom.
     */
    @Override
    public Room createBossRoom(final HitBox hitbox, final List<Door> doors, final Enemy boss, final List<Wall> walls) {
        return new BossRoom(hitbox, doors, boss, walls, ImageType.BACKGROUND_MAIN_ROOM);
    }

    /**
     * Create the ShopRoom.
     */
    @Override
    public Room createShopRoom(final HitBox hitbox, final List<Door> doors, final List<Inanimated> items,
            final List<Wall> walls) {
        return new ShopRoom(hitbox, doors, items, walls, ImageType.BACKGROUND_SHOP_ROOM);
    }

}
