package enums;

/**
 * This enumeration identifies a sprite stored on the disk and its path.
 */
public enum Sprite {

    /**
     * The sprite of the player base destroyed.
     */
    BASE_DESTROYED("base_destroyed.png"),
    /**
     * The sprite of the player base to be defended.
     */
    BASE("base.png"),
    /**
     * The sprite of a single Brick Block.
     */
    BLOCK_BRICK("block_brick.png"),
    /**
     * The sprite of a single Ice Block.
     */
    BLOCK_ICE("block_ice.png"),
    /**
     * The sprite of a single Steel Block.
     */
    BLOCK_STEEL("block_steel.png"),
    /**
     * The sprite of a single Tree Block.
     */
    BLOCK_TREE("block_tree.png"),
    /**
     * The sprite of a single Water Block.
     */
    BLOCK_WATER("block_water.png"),
    /**
     * The sprite of the bullet fired by player and enemies.
     */
    BULLET("bullet.png"),
    /**
     * The sprite of the enemy armor tank.
     */
    ENEMY_ARMOR_TANK("enemy_armor_tank.png"),
    /**
     * The sprite of the enemy fast tank.
     */
    ENEMY_FAST_TANK("enemy_fast_tank.png"),
    /**
     * The sprite of the enemy normal tank.
     */
    ENEMY_NORMAL_TANK("enemy_normal_tank.png"),
    /**
     * The sprite of the enemy power tank.
     */
    ENEMY_POWER_TANK("enemy_power_tank.png"),
    /**
     * The sprite of the main player tank. It is green and UP-directed.
     */
    PLAYER_TANK_GREEN("player_tank_green.png"),
    /**
     * The sprite of the main player tank. It is yellow and UP-directed.
     */
    PLAYER_TANK_YELLOW("player_tank_yellow.png");

    // The path of the sprite on disk.
    private String path;
    // The directory which the sprite is stored.
    private static final String DIR = "sprites";

    /*
     * Constructor of the enumeration.
     * 
     * @param spriteName the name which the sprite is stored on the disk with
     *                   extension.
     */
    Sprite(final String spriteName) {
        path = "/" + DIR + "/" + spriteName;
    }

    /**
     * Getter method of the path of the sprite.
     * 
     * @return the path of the sprite on disk.
     */
    public String getPath() {
        return path;
    }

}
