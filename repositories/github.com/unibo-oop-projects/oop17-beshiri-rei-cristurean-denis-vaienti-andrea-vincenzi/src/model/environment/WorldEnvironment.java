package model.environment;

import java.util.List;

import model.animated.Animated;
import model.hitbox.HitBox;
import model.inanimated.Button;
import model.inanimated.Door;
import model.inanimated.Inanimated;
import model.room.Room;

/**
 * Create the world map with rooms and destination doors.
 */
public interface WorldEnvironment {

    /**
     * @return list of rooms in the world.
     */
    List<Room> createWorld();

    /**
     * @return rightDoorFromMainToShop.
     */
    Door getRightDoorFromMainToShop();

    /**
     * @return leftDoorFromShopToMain.
     */
    Door getLeftDoorFromShopToMain();

    /**
     * @return rightDoorFromShopToBoss.
     */
    Door getRightDoorFromShopToBoss();

    /**
     * @return rightDoorFromBossToShop.
     */
    Door getLeftDoorFromBossToShop();

    /**
     * @return boss.
     */
    Animated getBoss();

    /**
     * @return button of the main room.
     */
    Button getButton();

    /**
     * @return list of items.
     */
    List<Inanimated> getItems();

    /**
     * @return the HB of the room.
     */
    HitBox getRoomHB();
}
