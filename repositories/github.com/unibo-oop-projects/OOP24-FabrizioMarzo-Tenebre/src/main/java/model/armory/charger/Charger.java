package model.armory.charger;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;

/**
 * Interface representing a charger component responsible for managing
 * ammunition.
 * <p>
 * Provides methods to extract ammunition, update charger position,
 * and retrieve the current ammunition load.
 */
public interface Charger {

    /**
     * Extracts a single unit of ammunition from the charger.
     * 
     * @return the extracted {@link Munition}, or {@code null} if no ammunition is
     *         available
     */
    Munition extractAmmunition();

    /**
     * Sets the position of the charger in the model space.
     * 
     * @param pos a pair (x, y) representing the new position
     */
    void setPos(final Pair<Double, Double> pos);

    /**
     * Returns the current amount of ammunition loaded in the charger.
     * 
     * @return the number of ammunition units currently loaded
     */
    int getCurrentLoad();

}
