package it.dpg.minigames.jumpgame.model;

import java.util.List;

/**
 * Interface of a Platform container
 * */

public interface PlatformSpawner {

    /**
     * @return a List of platforms
     * @see Platform
     * */
    List<Platform> getPlatforms();

    /**
     * Update the platform generation state and logic (to call at each frame)
     * */
    void updatePlatformsGeneration();
}
