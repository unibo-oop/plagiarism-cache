package model;

import javafx.util.Pair;
import model.world_state.World;

public interface Character {
    /**
     *
     * @return location
     *      Location of the character at this moment
     */
    Pair<Double, Double> getLocation();

    /**
     * 
     * @param newPos
     *      New position of the character
     */
    void setLocation(Pair<Double, Double> newPos);

    /**
     * 
     * @return aiming
     *      Returns the aiming state
     */
    boolean isAiming();

    /**
     * 
     * @param aiming
     *      Setter for the aiming state
     */
    void changeAiming(boolean aiming);

    /**
     * 
     * @param letter
     *      Letter pressed by the player
     * 
     * @param world
     *      World where the character spawns in
     */
    void hit(char letter, World world);
}
