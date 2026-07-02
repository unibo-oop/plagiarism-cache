package thatlevelagain.character.enemies.collision;

import java.util.List;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Class that manage the behavior of an object that implements EnemyInterface, in this case Gomitolo.
 * 
 * @author 
 *
 */
public class CollisionGomitolo extends Collision {

    /**
     * 
     * Method that change value of the Enemy to react with the environment.
     * 
     * @param gomitolo
     *          Object of class that implements EnemyInterface
     * @param mattoni 
     *          List of Object Mattoni
     */
    public void intersection(final EnemyInterface gomitolo, final List<Mattoni> mattoni) {
            //call the method of the class Abstract to refresh the collisions
        checkColliding(gomitolo, mattoni);
            //according to the collision set the field of the car
        if (isCollisionBottom()) {
            setFalling(false);
        } else if (!isCollisionSide()) {
            setFalling(true);
        }
        if (isCollisionSide() && !isFalling()) {
            gomitolo.setAvailableMovement(false);
            gomitolo.setGoDown(false);
            gomitolo.incrementY(-DELTA_Y);
        } else if (isCollisionSide() && isFalling()) {
            gomitolo.setAvailableMovement(false);
        }
        if (isCollisionTop()) {
            gomitolo.setJumping(false);
        }
    }
}
