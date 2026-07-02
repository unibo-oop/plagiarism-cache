package spacesurvival.model.gameobject.factories;

import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.takeable.TakeableGameObject;

/**
 * Contract for the factory which will create all objects for the game.
 */
public abstract class AbstractFactoryGameObject {

    /**
     * @return a new Asteroid
     */
    public abstract MainObject createAsteroid();

    /**
     * @return a new ChaseEnemy
     */
    public abstract MainObject createChaseEnemy();

    /**
     * @return a new FireEnemy
     */
    public abstract FireableObject createFireEnemy();

    /**
     * @return a new Boss
     */
    public abstract FireableObject createBoss();

    /**
     * @return a new Ammo
     */
    public abstract TakeableGameObject createAmmo();

    /**
     * @return a new Heart
     */
    public abstract TakeableGameObject createHeart();

}
