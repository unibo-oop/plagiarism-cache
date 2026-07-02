package ballblast.model.powerups;

import ballblast.model.gameobjects.GameObject;

/**
 * The interface representing a power.
 */
public interface Power {

    /**
     * Activates the power on a specific player.
     * 
     * @param player The Player who gets the power.
     */
    void activate(GameObject player);

    /**
     * Deactivates the power.
     */
    void deactivate();

    /**
     * Returns the enhancement active state.
     * 
     * @return The enhancement active state.
     */
    boolean isActive();

    /**
     * Returns the {@link PowerTypes} of the power.
     * 
     * @return The {@link PowerTypes} of the power.
     */
    PowerTypes getPowerType();

}
