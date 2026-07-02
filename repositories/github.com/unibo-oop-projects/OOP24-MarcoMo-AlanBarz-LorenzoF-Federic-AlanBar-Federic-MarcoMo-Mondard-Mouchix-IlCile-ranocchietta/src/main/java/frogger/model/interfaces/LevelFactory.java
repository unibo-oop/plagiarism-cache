package frogger.model.interfaces;

/**
 * Factory interface for creating {@link Level} instances.
 * <p>
 * This interface allows different implementations
 * provide various strategies for level creation (e.g., random, custom).
 * By using this abstraction, new types of levels can be introduced by simply
 * providing new implementations of this interface, without modifying client code.
 * </p>
 */
public interface LevelFactory {

    /**
     * Creates a new {@link Level} instance.
     * The specific type and configuration of the level are determined by the concrete implementation.
     *
     * @return a new Level instance
     */
    Level createLevel();
}
