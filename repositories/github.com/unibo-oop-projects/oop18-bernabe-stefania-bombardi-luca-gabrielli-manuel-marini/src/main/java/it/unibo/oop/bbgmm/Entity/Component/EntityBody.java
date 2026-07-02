package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;


public interface EntityBody extends EntityComponent{

    /**
     * @return The position
     */
    Pair<Integer, Integer> getPosition();

    /**
     *
     * @param velocity
     *          The speed of the entity
     */
    void setVelocity(double velocity);

    /**
     *
     * @return Current speed
     */
    double getVelocity();

    /**
     *
     * @return Body dimension
     */
    Pair<Double, Double> getDimension();

}
