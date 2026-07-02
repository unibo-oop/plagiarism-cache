package model.room;

import java.util.List;

import model.animated.Enemy;
import model.hitbox.HitBox;
import model.inanimated.Button;
import model.inanimated.Door;
import model.inanimated.Inanimated;
import model.inanimated.Wall;

/**
 * Interface for room factory.
 * 
 */
public interface RoomFactory {

    /**
     * Create Main Room.
     * 
     * @param hitbox
     *            MainRoom's HitBox.
     * @param doors
     *            MainRoom's doors.
     * @param button
     *            MainRoom's button for start the round.
     * @param walls
     *            Walls of the room.
     * @return a MainRoom.
     */
    Room createMainRoom(HitBox hitbox, List<Door> doors, Button button, List<Wall> walls);

    /**
     * Create Boss room.
     * 
     * @param hitbox
     *            BossRoom's HitBox.
     * @param doors
     *            BossRoom's doors.
     * @param boss
     *            The boss of the game.
     * @param walls
     *            Walls of the room.
     * @return a BossRoom.
     */
    Room createBossRoom(HitBox hitbox, List<Door> doors, Enemy boss, List<Wall> walls);

    /**
     * Create shop room.
     * 
     * @param hitbox
     *            ShopRoom's HitBox.
     * @param doors
     *            ShopRoom's doors.
     * @param items
     *            The items that you can buy.
     * @param walls
     *            Walls of the room.
     * @return a ShopRoom.
     */
    Room createShopRoom(HitBox hitbox, List<Door> doors, List<Inanimated> items, List<Wall> walls);
}
