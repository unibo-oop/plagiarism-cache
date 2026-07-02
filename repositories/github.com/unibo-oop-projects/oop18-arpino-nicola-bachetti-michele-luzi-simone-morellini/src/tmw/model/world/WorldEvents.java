package tmw.model.world;

/**
 * Events that world can notify. Typically use is for notify listeners of
 * gameWorld.
 *
 */
public enum WorldEvents {

    /**
     * Item-pick event.
     */
    ITEMPICK,

    /**
     * Enemy-killed event.
     */
    ENEMY_KILLED,

    /**
     * Bullet-hit event.
     */
    BULLET_HIT,

    /**
     * Shoot event.
     */
    SHOOT,

    /**
     * Obstacle destroy event.
     */
    OBSTACLE_DESTROYED,

    /**
     * Player death event.
     */
    PLAYER_DEATH;
}

