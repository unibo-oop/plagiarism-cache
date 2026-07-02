package game.theme;

import java.io.IOException;

import graphics.Sprite;
import graphics.SpriteSheet;

/**
 * Class to store any type of sprite.
 */
public class SpritesManager {

    private static final int Y_LOCATION = 1;
    private static final int WALL_X_LOCATION = 1;
    private static final int BREAKABLE_WALL_X_LOCATION = 2;
    private static final int KEY_X_LOCATION = 3;

    private final Sprite wall;
    private final Sprite breakableWall;
    private final Sprite key;
    private final DoorSprite door;
    private final PlayerSprites player;

    /**
     * Constructor that initialize every entity sprite.
     * @param folder : folder theme
     * @throws IOException : problem during input/output
     */
    public SpritesManager(final String folder) throws IOException {
        final SpriteSheet sheet = new SpriteSheet(folder + "/sprites.png");
        this.breakableWall = new Sprite(sheet, BREAKABLE_WALL_X_LOCATION, Y_LOCATION);
        this.wall = new Sprite(sheet, WALL_X_LOCATION, Y_LOCATION);
        this.key = new Sprite(sheet, KEY_X_LOCATION, Y_LOCATION);
        this.door = new DoorSprite(sheet);
        this.player = new PlayerSprites(sheet);
    }

    /**
     * Wall sprite getter.
     * @return wall sprite
     */
    public Sprite getWallSprite() {
        return this.wall;
    }

    /**
     * Breakable wall sprite getter.
     * @return breakable wall sprite.
     */
    public Sprite getBreakableWallSprite() {
        return this.breakableWall;
    }

    /**
     * Key sprite getter.
     * @return key sprite
     */
    public Sprite getKeySprite() {
        return this.key;
    }

    /**
     * Door sprite getter.
     * @return door sprites
     */
    public DoorSprite getDoorSprite() {
        return this.door;
    }

    /**
     * Player sprite getter.
     * @return any player sprites
     */
    public PlayerSprites getPlayerSprite() {
        return this.player;
    }
}
