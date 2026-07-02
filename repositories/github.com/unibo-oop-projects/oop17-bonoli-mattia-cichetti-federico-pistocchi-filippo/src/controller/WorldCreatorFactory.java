package controller;

import model.world.World;

/**
 * factory responsible for world building.
 */
public interface WorldCreatorFactory {
 
    /**
     * @return world for easy game mode
     */
    World createEasyWorld();

    /**
     * @return world for medium game mode
     */
    World createNormalWorld();

    /**
     * @return world for hard game mode
     */
    World createHardWorld();
}
