package thatlevelagain.character.enemies;

import java.awt.Rectangle;
import thatlevelagain.character.CharacterInterface;
import thatlevelagain.character.enemies.collision.CollisionInterface;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * 
 *
 */
public interface EnemyInterface extends CharacterInterface {

    /**
     * State in which enemy is not jumping.
     */
    int STOP = 0;

    /**
     * State in which enemy started to jump.
     */
    int STARTED = 1;

    /**
     * Delta y of the enemy to jump in vertical direction.
     */
    int DELTA_SALTO = 1;

    /**
     * Duration of jump in cicle of game loop.
     */
    int N = GamePanel.BLOCK_HEIGHT * 3;

    /**
     * Number that represent left direction of the enemy.
     */
    int LEFT = -1;

    /**
     * Number that represent right direction of the enemy.
     */
    int RIGHT = 1;

    /**
     * Represent none direction and the number zero.
     */
    int NONE = 0;

    /**
     * Delta x of the enemy to represent the movement.
     */
    int DELTA_MOVEMENT = 2;

    /**
     * Side of the Rectangle.
     */
    int RECT_SIDE = 5;

    /**
     * Free space between enemy and player in which direction is NONE.
     */
    int FREE_SPACE = 10;

    /**
     * @param flag 
     *          set value of available movement
     */
    void setAvailableMovement(boolean flag);

    /**
     * @param flag 
     *          set value of goDown field
     */
    void setGoDown(boolean flag);

    /**
     * @param flag 
     *          set value of jumping field
     */
    void setJumping(boolean flag);

    /**
     * 
     * @return 
     *          Object of class MapImpl
     */
    MapImpl getMap();

    /**
     * 
     * @return 
     *          Object of class Rectangle
     */
    Rectangle getRect();

    /**
     * 
     * @return 
     *          value of direction. -1 if the direction is LEFT, 1 if it is RIGHT, 0 if 
     *          there is no movement
     */
    int getDirection();

    /**
     * @return .
     *          Object of class that implements CollisionInterfacess
     */
    CollisionInterface getCollisionManager();

    /**
     * 
     * @return 
     *          true if enemy can move
     */
    boolean isAvailableMovement();

    /**
     * @return 
     *          true if the enemy is going down
     */
    boolean isGoDown();

    /**
     * @return 
     *          true if the enemy is jumping
     */
    boolean isJumping();

    /**
     * @param delta
     *          Delta of horizontal movement
     */
    void incrementX(int delta);

    /**
     * @param delta 
     *          Delta of vertical movement
     */
    void incrementY(int delta);

    /**
     * Simulation of Earth gravity effect. Increment Y every loop game
     */
    void gravityEffect();

    /**
     * Set the direction of the object according to Player coordination.
     */
    void updateMyDir();

    /**
     * Move in a direction by a specified value. 
     */
    void move();

}
