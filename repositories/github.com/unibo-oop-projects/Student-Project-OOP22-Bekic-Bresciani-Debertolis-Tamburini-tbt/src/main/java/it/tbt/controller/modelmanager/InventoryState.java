package it.tbt.controller.modelmanager;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;

import java.util.List;
import java.util.Map;

/**
 * The {@code InventoryState} interface represents the state of the inventory in the application's controller.
 * It extends the {@link ModelState} interface.
 * <p>
 * This interface provides methods to access information about the party members, inventory phase, inventory items,
 * and perform actions related to the inventory state.
 * </p>
 */
public interface InventoryState extends ModelState {

    /**
     * Returns a list of party members.
     *
     * @return A list of party members.
     */
    List<Ally> getPartyMembers();

    /**
     * Returns the current inventory phase.
     *
     * @return The current inventory phase.
     */
    InventoryPhase getPhase();

    /**
     * Returns the inventory as a map of items and their quantities.
     *
     * @return The inventory as a map of items and their quantities.
     */
    Map<Item, Integer> getInventory();

    /**
     * Moves to the previous element in the inventory.
     */
    void previousElement();

    /**
     * Moves to the next element in the inventory.
     */
    void nextElement();

    /**
     * Performs an action related to the inventory state.
     */
    void performAction();

    /**
     * Moves to the next phase of the inventory.
     */
    void nextPhase();

    /**
     * Returns the size of the party.
     *
     * @return The size of the party.
     */
    int getPartySize();

    /**
     * Moves to the previous phase of the inventory.
     */
    void previousPhase();

    /**
     * Switches to the explore state.
     */
    void switchToExplore();

    /**
     * Returns the index of the currently selected item.
     *
     * @return The index of the currently selected item.
     */
    int getItemSelected();

    /**
     * Returns the index of the item that currently has focus.
     *
     * @return The index of the item that currently has focus.
     */
    int getItemFocus();

    /**
     * Returns the index of the currently selected ally.
     *
     * @return The index of the currently selected ally.
     */
    int getAllySelected();

    /**
     * Returns the index of the ally that currently has focus.
     *
     * @return The index of the ally that currently has focus.
     */
    int getAllyFocused();
}
