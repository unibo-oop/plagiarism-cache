package thatlevelagain.character.enemies.collision;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Abstract Class of general strategy to check if there is a collision between an Enemy and 
 * an object of the Class Mattoni. Behavior chenge according to the side of the collision
 * 
 * @author 
 *
 */
public abstract class Strategy {

    /**
     * Object of a class that implements EnemyInterface. The Enemy which has to check collision
     */
    private final EnemyInterface enemy;

    /**
     * Strategy constructor.
     * 
     * @param enemy
     *          Object of class that implements Enemy Interface
     */
    public Strategy(final EnemyInterface enemy) {
        this.enemy = enemy;
    }

    /**
     * 
     * @return 
     *          the enemy that is checking if it is colliding with mattone
     */
    public EnemyInterface getEnemy() {
        return enemy;
    }

    /**
     * Return true if there is an intersection between enemy and mattone passed in input.
     * In that case there is a collision between two objects.
     * 
     * @param mattone 
             Object of class Mattoni
     * @return true if collide
     */
    public abstract boolean collision(Mattoni mattone);

}
