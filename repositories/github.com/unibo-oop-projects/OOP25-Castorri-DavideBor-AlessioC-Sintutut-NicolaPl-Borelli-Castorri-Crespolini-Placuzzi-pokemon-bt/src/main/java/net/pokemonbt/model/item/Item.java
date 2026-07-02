package net.pokemonbt.model.item;

/**
 * Base interface for all Items a pokemon can hold.
 */
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface Item {
    /**
     * @return The item's unique ID.
     */
    String getID();
}
