package it.unibo.oop.supermario.character;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import it.unibo.oop.supermario.enemies.Enemy;
import it.unibo.oop.supermario.utils.State;

/**
 * This class models Mario and manages its states and movements.
 */
public interface Mario {
    /**
     * The body of Mario which contains : position, velocity, fixture ecc.
     * 
     * @return Mario's body
     */
    Body getBody();

    /**
     * Get Mario state like running, jumping, shooting ecc.
     * 
     * @return Mario's State
     */
    State getState();

    /**
     * Check if Mario is Big or not.
     * 
     * @return Big Mario state
     */
    boolean isGrownUp();
    /**
     * Check if Mario is Fire Mario or not.
     * 
     * @return Fire Mario state
     */
    boolean isFireMario();

    /**
     * Check if Mario is dead or not.
     * 
     * @return Dead state
     */
    boolean isDead();

    /**
     * Set the state of Big Mario when he is finished to transform.
     * 
     * @param value if he has finished to transforms animation
     */
    void setGrowUp(boolean value);
    /**
     * Mario becomes Big Mario.
     */
    void grow();

    /**
     * Mario becomes Fire Mario.
     */
    void onFire();

    /**
     * Apply impulse on the y axis.
     */
    void jump();

    /**
     * Apply velocity to player.
     * 
     * @param direction  the direction to be applied
     */
    void move(float direction);

    /**
     * Mario can crouch.
     */
    void getCrouch();

    /**
     * Mario can shoot.
     */
    void shoot();

    /**
     * If Mario isn't crouch anymore , than he stand up.
     */
    void standUp();

    /**
     * Check if Mario is running to the right.
     * 
     * @return Running side state
     */
    boolean isRunningRight();

    /**
     * Update Mario's model every frame.
     *
     * @param dt delta time of the frame
     */
    void update(float dt);

    /**
     * Handle the collision with Enemy. 
     * 
     * @param enemy the enemy with which it is collided 
     */
    void hit(Enemy enemy);

    /**
     * Check if Mario shoots fireballs.
     * 
     * @return Mario firing state
     */
    boolean isFiring();

    /**
     * Give the position of Mario in world.
     * 
     * @return Vector2 : x, y coordinate of Mario
     */
    Vector2 getPosition();

    /**
     * Mario was hit.
     */
    void shrinking();
}
