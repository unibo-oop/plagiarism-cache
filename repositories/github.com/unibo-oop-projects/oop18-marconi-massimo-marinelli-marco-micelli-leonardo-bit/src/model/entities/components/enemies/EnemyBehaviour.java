package model.entities.components.enemies;

/**
 * Lists all the possible behaviours an enemy can have.
 */
public enum EnemyBehaviour {

    /**
     * The enemy is looking around.
     */
    LOOKING,

    /**
     * The enemy detected the player and is attacking him.
     */
    AGGRESSIVE,

    /**
     * The enemy is dying.
     */
    DYING
}
