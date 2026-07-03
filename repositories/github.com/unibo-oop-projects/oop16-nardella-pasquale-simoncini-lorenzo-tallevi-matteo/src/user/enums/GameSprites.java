package user.enums;

/**
 * This enum contains the name of the various sprites used in the game.
 */
public enum GameSprites {

    /**
     * This fields represents the sprites of the friend ships.
     */
    PLAYER_SHIP("game/ships/player"), ALLY_SHIP("game/ships/ally"),

    /**
     * This fields represents the sprites of the various enemy ships of the
     * game.
     */
    ENEMY_SHIP("game/ships/enemy"), BOSS_SHIP_1("game/ships/boss0"), BOSS_SHIP_2("game/ships/boss1"),

    /**
     * This fields represents the sprites of the bosses' turrets.
     */
    TURRET_SMALL("game/ships/turretSmall"), TURRET_BIG("game/ships/turretBig"),

    /**
     * This fields represents the sprites of the various bullets of the game.
     */
    BULLET_1("game/bullets/bullet0"), BULLET_2("game/bullets/bullet1"), BULLET_3("game/bullets/bullet2"), PLASMA_BULLET("game/bullets/plasmaBullet"), TURRET_BULLET("game/bullets/turretBullet"),

    /**
     * This fields represents the sprites of the various explosions.
     */
    SMALL_EXPLOSION("game/effects/exploTiny"), MEDIUM_EXPLOSION_0("game/effects/exploSmall0"), MEDIUM_EXPLOSION_1("game/effects/exploSmall1"), BIG_EXPLOSION("game/effects/exploBig"),

    /**
     * This fields represents the sprites of the other effects.
     */
    PIXEL_LIGHT("game/effects/pixelLight"), LIGHT("game/effects/light"), PARTICLE("game/bullets/cannonBullet"), SHOCKWAVE("game/effects/shockwave"), TRAIL("game/effects/trail"),

    /**
     * This field represent the target indicator of the player ship.
     */
    TARGET_POINTER("game/effects/indicator");

    private String value;

    GameSprites(final String value) {
        this.value = value;
    }

    /**
     * This method returns the class name of the desired object.
     * 
     * @return the class name of the requested object
     */
    public String getValue() {
        return this.value;
    }

}
