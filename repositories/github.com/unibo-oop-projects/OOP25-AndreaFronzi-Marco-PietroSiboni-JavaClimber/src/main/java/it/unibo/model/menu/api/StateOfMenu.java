package it.unibo.model.menu.api;

/**
 * Interface representing a specific state of the menu.
 * Each implementation encapsulates the logic and behavior for a particular menu
 * screen or mode.
 */
public interface StateOfMenu { // NOPMD this interface not is a functional interface because it can have
    // multiple implementations, each representing a different state of the menu.
    /**
     * Executes the logic associated with the current menu state.
     */
    void execute();
}
