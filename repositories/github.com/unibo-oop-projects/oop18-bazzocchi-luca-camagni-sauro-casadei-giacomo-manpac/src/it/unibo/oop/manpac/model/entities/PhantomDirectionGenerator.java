package it.unibo.oop.manpac.model.entities;
import it.unibo.oop.manpac.model.Directions;

/**
 * Functional interface for the directions generation strategy.
 */
@FunctionalInterface
public interface PhantomDirectionGenerator {

    /**
     * Generate a new direction.
     * 
     * @return The new direction
     */
    Directions generateDirection();
}
