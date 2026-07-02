package models;

/**
 * The Enemy interface is an extension of Character.
 * It establishes contracts for a specific type of entity called enemy.
 * Their main focus is to kill the player.
 */
public interface Enemy extends Character{

    /**
     * Get the Ai of instance of enemy.
     * 
     * @return Ai of instace of enemy
     */
    AiEnemy getAi();

}