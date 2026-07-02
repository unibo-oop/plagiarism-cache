package it.tbt.controller.modelmanager;

/**
 * The {@code InventoryPhase} enum represents the different phases of the inventory process.
 * It provides methods to navigate between the phases.
 */
public enum InventoryPhase {

    /**
     * Represents the inventory phase.
     */
    INVENTORY,

    /**
     * Represents the members phase.
     */
    MEMBERS;

    private static final InventoryPhase[] VALUES = values();

    /**
     * Returns the next phase in the sequence.
     *
     * @return The next phase in the sequence.
     */
    public InventoryPhase next() {
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }

    /**
     * Returns the previous phase in the sequence.
     *
     * @return The previous phase in the sequence.
     */
    public InventoryPhase previous() {
        final int previousIndex = Math.floorMod(this.ordinal() - 1, VALUES.length);
        return VALUES[previousIndex];
    }
}
