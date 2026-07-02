package net.pokemonbt.utility;

/**
 * An interface representing all classes that can be copied.
 *
 * @param <T> The type of the class to return.
 */
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface Clone<T> {
    /**
     * @return A new instance with the same values.
     */
    T copyOf();
}
