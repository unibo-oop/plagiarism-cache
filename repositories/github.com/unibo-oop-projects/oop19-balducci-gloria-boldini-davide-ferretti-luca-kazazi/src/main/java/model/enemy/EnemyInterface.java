package model.enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import controllers.movement.Movement;
import controllers.movement.animation.Animation;
import model.Direction;

public interface EnemyInterface {

    /**
     * @return union rectangle
     * 
     *         method to get enemy's rectangle union ray rectangle
     * 
     */
    Rectangle getRectangle();

    /**
     * @return enemy's rectangle
     * 
     *         method to get only enemy rectangle
     * 
     */
    Rectangle getEnemyRectangle();

    /**
     * @return alive
     * 
     *         method to check if enemy's alive
     * 
     */
    boolean isAlive();

    /**
     * @return true if is possible to kill this enemy, false otherwise
     * 
     *         method to remove enemy's life, if's possible
     * 
     */
    boolean killLife();

    /**
     * setter alive.
     * 
     * @param alive
     */
    void setAlive(boolean alive);

    /**
     * @return ray
     * 
     *         get enemy action ray
     * 
     */
    Enemy.Ray getRay();

    /**
     * @return listUp
     * 
     *         get textures up movement
     * 
     */
    List<BufferedImage> getListUp();

    /**
     * @return listUp
     * 
     *         get textures down movement
     * 
     */
    List<BufferedImage> getListDown();

    /**
     * @return listUp
     * 
     *         get textures left movement
     * 
     */
    List<BufferedImage> getListLeft();

    /**
     * 
     * @return listUp
     * 
     *         get textures right movement
     * 
     */
    List<BufferedImage> getListRight();

    /**
     * @return animation
     * 
     *         get enemy's animation
     * 
     */
    Animation getAnimation();

    /**
     * method to set position after a collision.
     * 
     * @param direction
     */
    void setPositionAfterCollision(Direction direction);

    /**
     * 
     * method to check realtime collisions.
     */
    void tick();

    /**
     * 
     * @return speed
     * 
     *         getter speed value
     * 
     */
    Movement getMovement();

    /**
     * @return movement
     * 
     *         getter movement object
     */
    int getSpeed();

}
