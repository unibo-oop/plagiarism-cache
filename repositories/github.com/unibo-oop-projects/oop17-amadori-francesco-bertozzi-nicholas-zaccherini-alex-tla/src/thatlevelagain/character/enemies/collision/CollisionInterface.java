package thatlevelagain.character.enemies.collision;

import java.util.List;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Interface of the class Collision that manage the collision of Enemies.
 * 
 * @author 
 *
 */
public interface CollisionInterface {

    /**
     * 
     * @param enemy 
     *          Object of class that implements EnemyInterface
     * @param mattoni 
     *          List of Object Mattoni
     */
    void checkColliding(EnemyInterface enemy, List<Mattoni> mattoni);

    /**
     * 
     * @param enemy 
     *          Object of class that implements EnemyInterface
     * @param mattoni 
     *          List of Object Mattoni
     */
    void intersection(EnemyInterface enemy, List<Mattoni> mattoni);

    /**
     * 
     * @return 
     *          true if is colliding top
     */
    boolean isCollisionTop();

    /**
     * 
     * @return 
     *          true if is colliding bottom
     */
    boolean isCollisionBottom();

    /**
     * 
     * @return 
     *          true if is colliding side
     */
    boolean isCollisionSide();

    /**
     * 
     * @return 
     *          the value of falling field
     */
    boolean isFalling();

    /**
     * @param flag 
     *          set collisionTop value to flag value
     */
    void setCollisionTop(boolean flag);

    /**
     * @param flag
     *          set collisionBottom value to flag value 
     */
    void setCollisionBottom(boolean flag);

    /**
     * @param flag 
     *          set collisionSide value to flag value
     */
    void setCollisionSide(boolean flag);

    /**
     * 
     * @param flag 
     *          set falling value to flag value
     */
    void setFalling(boolean flag);

    /**
     * Reset the value of the field of the collision.
     */
    void resetColliding();

}
