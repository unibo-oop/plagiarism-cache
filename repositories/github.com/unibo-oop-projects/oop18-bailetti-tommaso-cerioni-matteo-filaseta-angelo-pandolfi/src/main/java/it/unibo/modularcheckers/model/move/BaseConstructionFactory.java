package it.unibo.modularcheckers.model.move;

/**
 * Interface containing basic methods of evevry factory. Every new Factory must
 * implements this interface.
 * 
 * @param <X> the generic value of the factory.
 */
public interface BaseConstructionFactory<X> {

    /**
     * Add a value to the factory.
     * 
     * @param value the value to add.
     */
    void addValue(X value);

    /**
     * Removes the last value inserted.
     * 
     * @return the value removed.
     */
    X removeValue();

}
