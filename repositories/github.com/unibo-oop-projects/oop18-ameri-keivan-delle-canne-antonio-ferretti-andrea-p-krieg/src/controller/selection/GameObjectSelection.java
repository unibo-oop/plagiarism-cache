package controller.selection;

/**
 * selection for basic game objects.
 */
public enum GameObjectSelection implements Selection {
    /** the unit on this case is selected. */
    UNIT,
    /** the terrain on this case is selected. */
    TERRAIN,
    /** the structure on this case is selected. */
    STRUCTURE;

    /**{@inheritDoc}**/@Override
    public String getId() {
        return this.toString().toLowerCase() + "_selection";
    }
}
