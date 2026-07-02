package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api;

/**
 * Interface representing a pair of a generic type.
 * 
 * @param <X> the x object.
 * @param <Y> the y object.
 */
public interface Pair<X, Y> {

    /**
     * Gets the x object.
     * 
     * @return the x object
     */
    X getX();

    /**
     * Gets the y object.
     * 
     * @return the y object
     */
    Y getY();

}
