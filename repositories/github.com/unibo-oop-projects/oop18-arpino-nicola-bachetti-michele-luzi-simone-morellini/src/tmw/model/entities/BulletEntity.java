package tmw.model.entities;

/**
 * This interface represents a bullet in the game.
 */
public interface BulletEntity extends GameEntity {

    /**
     * Returns the damage that the bullet does when hit an entities.
     * 
     * @return the damage of the bullet
     */
    int getDamage();
}
