package model.mod;

import model.obstacle.GameObject;

/**
 * ModObstacle is an extension of Obstacle that allows other elements to get the ModType from the ModEntity.
 *
 */
public interface ModObstacle extends GameObject {

    /**
     * Getter for the ModType.
     * @return modType the ModType.
     */
    ModType getModType();

}
