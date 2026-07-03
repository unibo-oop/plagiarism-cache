package game;

/**
 * Every object in the game has its id to be distinguishable from others.
 */
public enum ID {
    /**
     * First player.
     */
    PLAYER,

    /**
     * Second player.
     */
    PLAYER2,

    /**
     * A bullet.
     */
    BULLET,

    /**
     * A basic enemy {@link game.enemy.BasicEnemy}.
     */
    BASIC_ENEMY,

    /**
     * A smart enemy {@link game.enemy.SmartEnemy}.
     */
    SMART_ENEMY,

    /**
     * A big enemy {@link game.enemy.BigEnemy}.
     */
    BIG_ENEMY,

    /**
     * A fast enemy {@link game.enemy.FastEnemy}.
     */
    FAST_ENEMY,

    /**
     * A boss enemy {@link game.enemy.BossEnemy}.
     */
    BOSS_ENEMY,

    /**
     * An enemy's shot {@link game.enemy.Shot}.
     */
    SHOOT,

    /** {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    SMP_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.BouncingObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    BNC_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.EnlargingObstacle} {@link game.obstacles.ObstacleDecorator.BouncingObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    ENL_BNC_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.EnlargingObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    ENL_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.TimeLimitedObstacle} {@link game.obstacles.ObstacleDecorator.EnlargingObstacle} {@link game.obstacles.ObstacleDecorator.BouncingObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    TML_ENL_BNC_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.TimeLimitedObstacle} {@link game.obstacles.ObstacleDecorator.EnlargingObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    TML_ENL_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.TimeLimitedObstacle} {@link game.obstacles.ObstacleDecorator.BouncingObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    TML_BNC_OBSTACLE,

    /**{@link game.obstacles.ObstacleDecorator.TimeLimitedObstacle} {@link game.obstacles.AbstractObstacle.SimpleObstacle}.*/
    TML_OBSTACLE,

    /**
     * An effect, for example an explosion.
     */
    EFFECT,

    /**
     * A powerUp.
     */
    POWER_UP;
}
