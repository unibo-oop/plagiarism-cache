package game.enemy;

/**
 * Class for the public method of {@link BasicEnemy}, {@link SmartEnemy}, {@link BigEnemy},
 * {@link FastEnemy}, {@link BossEnemy}.
 */
public interface InterfaceEnemy {
    /**
     * Method for create the enemies.
     * @return {@link AbstractEnemy}.
     */
    AbstractEnemy createThisEnemy();
    /**
     * Method for the enemies's random movement.
     * @return {@link DirEnemy}.
     */
    DirEnemy randomMovement();
    /**
     * Method to follow the player.
     */
    void followPlayer();
}
