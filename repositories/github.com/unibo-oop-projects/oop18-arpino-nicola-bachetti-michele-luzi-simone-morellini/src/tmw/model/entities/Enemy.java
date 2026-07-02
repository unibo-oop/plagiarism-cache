package tmw.model.entities;

/**
 * This interface represents an enemy in the game.
 *
 */
public interface Enemy extends GameEntity {

    /**
     * This method is used to get the points gained for killing the enemy.
     * 
     * @return the points for killing this enemy
     */
    int getScore();

    /**
     * This method returns the damage that the enemy does when touch the main
     * character.
     * 
     * @return the contact damage of this enemy
     */
    int getContactDamage();
}
