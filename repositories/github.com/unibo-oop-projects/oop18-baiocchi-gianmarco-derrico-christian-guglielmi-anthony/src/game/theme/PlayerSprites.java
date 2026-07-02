package game.theme;

import java.util.ArrayList;
import java.util.List;

import graphics.Sprite;
import graphics.SpriteSheet;

/**
 * Class that stores any player sprite.
 */
public class PlayerSprites {

    private static final int SPRITES_TO_MOVE = 7;
    private static final int SPRITES_TO_STAY = 2;
    private static final int X_LOCATION_STOP = 2;
    private static final int X_LOCATION_MOVE_LEFT = 3;
    private static final int X_LOCATION_MOVE_RIGHT = 4;
    private static final int X_LOCATION_MOVE_UP = 5;
    private static final int X_LOCATION_MOVE_DOWN = 6;

    private final List<Sprite> stop = new ArrayList<>();
    private final List<Sprite> left = new ArrayList<>();
    private final List<Sprite> right = new ArrayList<>();
    private final List<Sprite> down = new ArrayList<>();
    private final List<Sprite> up = new ArrayList<>();

    /**
     * Constructor for any sprites initialization.
     * @param sheet : png sheet where to take any sprite
     */
    public PlayerSprites(final SpriteSheet sheet) {
        for (int i = 0; i < SPRITES_TO_STAY; i++) {
            this.stop.add(new Sprite(sheet, X_LOCATION_STOP, i + 1));
        }
        for (int i = 0; i < SPRITES_TO_MOVE; i++) {
            this.right.add(new Sprite(sheet, X_LOCATION_MOVE_RIGHT, i + 1));
            this.left.add(new Sprite(sheet, X_LOCATION_MOVE_LEFT, i + 1));
            this.down.add(new Sprite(sheet, X_LOCATION_MOVE_DOWN, i + 1));
            this.up.add(new Sprite(sheet, X_LOCATION_MOVE_UP, i + 1));
        }
    }

    /**
     * @return list of sprites to represent a still entity
     */
    public List<Sprite> getStopSprites() {
        return this.stop;
    }

    /**
     * @return list of sprites to represent an entity that goes left
     */
    public List<Sprite> getLeftSprites() {
        return this.left;
    }

    /**
     * @return list of sprites to represent an entity that goes right
     */
    public List<Sprite> getRightSprites() {
        return this.right;
    }

    /**
     * @return list of sprites to represent an entity that goes down
     */
    public List<Sprite> getDownSprites() {
        return this.down;
    }

    /**
     * @return list of sprites to represent an entity that goes up
     */
    public List<Sprite> getUpSprites() {
        return this.up;
    }
}
