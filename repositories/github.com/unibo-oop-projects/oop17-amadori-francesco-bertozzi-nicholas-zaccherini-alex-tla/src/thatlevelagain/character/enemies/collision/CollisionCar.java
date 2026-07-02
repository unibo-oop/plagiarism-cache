package thatlevelagain.character.enemies.collision;

import java.util.List;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Class that manage the behavior of an object that implements EnemyInterface, in this case a Car.
 * 
 * @author
 *
 */
public class CollisionCar extends Collision {

    /**
     * Method that change value of the Enemy to react with the environment.
     * 
     * @param car 
     *          Object of class that implements EnemyInterface
     * @param mattoni 
     *          List of Object Mattoni
     */
    public void intersection(final EnemyInterface car, final List<Mattoni> mattoni) {
            //call the method of the class Abstract to refresh the collisions
        checkColliding(car, mattoni);
            //according to the collision set the field of the car
        if (isCollisionBottom()) {
            setFalling(false);
        } else if (!isCollisionSide()) {
            setFalling(true);
            car.setAvailableMovement(true);
        }
        if (isCollisionSide()) {
            car.setAvailableMovement(false);
        }
        if (isCollisionSide() && !isFalling()) {
            car.setAvailableMovement(false);
            car.setGoDown(false);
            car.incrementY(-DELTA_Y);
        } else if (isCollisionSide() && isFalling()) {
            car.setAvailableMovement(false);
        }
        if (isCollisionTop()) {
            car.setJumping(false);
        }
        if (car.isJumping() && isCollisionSide()) {
            car.setAvailableMovement(false);
        }
    }
}
