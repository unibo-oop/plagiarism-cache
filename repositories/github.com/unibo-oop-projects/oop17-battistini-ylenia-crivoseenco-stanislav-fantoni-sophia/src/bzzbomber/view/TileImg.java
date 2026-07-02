package bzzbomber.view;

import bzzbomber.model.utilities.Pair;

/**
 * Enumeration of Tile for @GameFieldPanel .
 */
public enum TileImg {
    /**
     * Tile of Wall.
     */
    WALL(new Pair<>(0, "Tiles/Blocks/SolidBlock.png")),
    /**
     * Tile of Box.
     */
    BOX(new Pair<>(1, "Tiles/Blocks/box.png")),
    /**
     * Tile of Ground.
     */
    GROUND(new Pair<>(2, "Tiles/Blocks/BackgroundTile.png")),

    /**
     * Tile of Door.
     */
    DOOR(new Pair<>(3, "Tiles/Blocks/door.png")),
    /**
     * Tile of Bomb.
     */
    BOMB(new Pair<>(4, "Tiles/Bomb/Bomb_f01.png")),
    /**
     * Tile of Health.
     */
    HEALTH(new Pair<>(4, "Tiles/Powerups/HeartPowerup.png")),

    /**
     * Tile of Explosion.
     */
    EXPLOSION(new Pair<>(4, "Tiles/explosion/explosion.png")),

    /**
     * Tile of Bomber.
     */
    BOMBER(new Pair<>(5, "Tiles/Bomberman/Front/BmanFront.png")),

    /**
     * Tile of Insect Back.
     */
    INSECT_UP(new Pair<>(5, "Tiles/Creep/Back/Insect.png")),
    /**
     * Tile of Insect Front.
     */
    INSECT_DOWN(new Pair<>(5, "Tiles/Creep/Front/Insect.png")),
    /**
     * Tile of Insect turn to left.
     */
    INSECT_LEFT(new Pair<>(5, "Tiles/Creep/Left/Insect.png")),
    /**
     * Tile of Insect turn to right.
     */
    INSECT_RIGHT(new Pair<>(5, "Tiles/Creep/Right/Insect.png")),
    /**
     * Tile for Hero Immunity.
     */
    IMMUNITY(new Pair<>(5, "Tiles/Bomberman/HeroImmunity.png"));

    private Pair<Integer, String> val;

    TileImg(final Pair<Integer, String> val) {
        this.val = val;
    }

    /**
     * @return value of a tile type
     */
    public Pair<Integer, String> getVal() {
        return val;
    }
}
