package unibo.exiled.model.map;

import unibo.exiled.utilities.ElementalType;

/**
 * Enum representing different types of cells on the game map.
 */
public enum CellType {
    /**
     * Represents a plains cell on the game map.
     */
    PLAINS(0, ElementalType.NORMAL),

    /**
     * Represents a swamp cell on the game map.
     */
    SWAMP(1, ElementalType.WATER),

    /**
     * Represents a volcano cell on the game map.
     */
    VOLCANO(2, ElementalType.FIRE),

    /**
     * Represents a storm cell on the game map.
     */
    STORM(3, ElementalType.BOLT),

    /**
     * Represents a forest cell on the game map.
     */
    FOREST(4, ElementalType.GRASS);

    private final int index;
    private final ElementalType associatedType;

    /**
     * Constructs a CellType with the specified index.
     *
     * @param index          The index associated with the cell type.
     * @param associatedType The type corresponding to the environmental type.
     */
    CellType(final int index, final ElementalType associatedType) {
        this.index = index;
        this.associatedType = associatedType;
    }

    /**
     * Gets the index value associated with the cell type.
     *
     * @return The index value of the cell type.
     */
    public int getValue() {
        return this.index;
    }

    /**
     * Gets the associated ElementalType of the environmental cell.
     *
     * @return An ElementalType, the one of the CellType.
     */
    public ElementalType getAssociatedType() {
        return this.associatedType;
    }
}
