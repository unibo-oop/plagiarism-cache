package entity;

import java.util.Optional;

import virtualworld.Movment;
import virtualworld.VirtualMap;

/**
 * Represent a Virtual VirtualBody.
 */
public interface VirtualBody {

    /**
     * Move the virtualBody if possible and if the movementcontroll is present.
     * @param xvalue Is the X coordinate
     * @param yvalue Is the Y coordinate
     * @return return a boolean representing the action appening
     */
    boolean move(int xvalue, int yvalue);

    /**
     * @return The collisionBox
     */
    CollisionBox<Integer> getCollisionBox();

    /**
     * Check the collision between two virtualBody.
     * @param box
     * @return A boolean representing the state
     */
    boolean checkCollision(CollisionBox<Integer> box);

    /**
     * Set the actual movement in reference to a map.
     * @param movementcontrol
     */
    void setMotion(Movment movementcontrol);

    /** 
     * @return an optional VirtualMap
     */
    Optional<VirtualMap> getMap();
}
