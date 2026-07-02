package zombieversity.model.entities;

/**
 * 
 * Enum that contains type and URL of the entity.
 *
 */
public enum EntityType {

    /**
     * Player.
     */
    PLAYER("/player.png"),
    /**
     * Zombie.
     */
    ZOMBIE("/zombie.png"),
    /**
     * Weapon.
     */
    WEAPON(""),
    /**
     * Bullet.
     */
    BULLET("/bullet.png"),
    /**
     * 
     */
    MELEE_ATTACK("/knifeAttack.png"),
    /**
     * Obstacle.
     */
    OBSTACLE("");

    private final String url;

    EntityType(final String url) {
        this.url = url;
    }

    public final String getURL() {
        return this.url;
    }
}
