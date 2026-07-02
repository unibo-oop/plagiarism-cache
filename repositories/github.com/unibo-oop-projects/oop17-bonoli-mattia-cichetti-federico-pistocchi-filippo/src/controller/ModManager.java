package controller;

import model.mod.ModObstacle;
import model.world.World;

/**
 * A ModManager is used to keep Mods in execution for long periods of time.
 */
public interface ModManager {

    /**
     * This method adds a Mod to the current Set of active Mods.
     * @param m The mod to be activated.
     */
    void activateMod(ModObstacle m);

    /**
     * Tick is called once per GameLoop cycle and decreases the time-to-live of each active Mod.
     */
    void tick();

    /**
     * Setter for the world.
     * @param w The world to set on this ModManager
     */
    void setWorld(World w);

    /**
     * Sets a new mod creation delay time.
     * @param newModDelay The new delay time for spawning mods.
     */
    void setModDelay(int newModDelay);
}
