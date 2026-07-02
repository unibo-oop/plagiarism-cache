package net.pokemonbt.utility;

/**
 * Interface utilized by the JavaFx controllers to update without
 * reinitialization.
 */
@FunctionalInterface
public interface Refreshable {

    /**
     * Updates the JavaFX controller without reinitialization.
     */
    void refresh();
}
