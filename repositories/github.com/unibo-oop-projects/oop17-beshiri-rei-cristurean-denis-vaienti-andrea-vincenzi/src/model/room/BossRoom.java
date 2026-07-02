package model.room;

import java.util.List;

import model.animated.Enemy;
import model.hitbox.HitBox;
import model.inanimated.Door;
import model.inanimated.Wall;
import utility.ImageType;

/**
 * BossRoom implement.
 *
 */
public class BossRoom extends AbstractRoom {

    private final Enemy boss;

    /**
     * Constructor for this class.
     * 
     * @param h
     *            BossRoom's HitBox.
     * @param doors
     *            BossRoom's doors.
     * @param boss
     *            The boss of the game.
     * @param walls
     *            list of walls in the room.
     * @param backImg
     *            background image.
     */
    public BossRoom(final HitBox h, final List<Door> doors, final Enemy boss, final List<Wall> walls,
            final ImageType backImg) {
        super(h, doors, walls, backImg);
        this.boss = boss;
    }

    /**
     * Get boss in main room.
     * 
     * @return the game's boss.
     */
    public Enemy getBoss() {
        return this.boss;
    }
}
