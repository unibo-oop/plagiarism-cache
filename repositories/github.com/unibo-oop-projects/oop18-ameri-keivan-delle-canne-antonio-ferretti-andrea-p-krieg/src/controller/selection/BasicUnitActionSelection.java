package controller.selection;

/**
 *selection for unit's basic actions. 
 */
public enum BasicUnitActionSelection implements Selection {
    /** if the case is selected the actually selected unit will move on it. */
    MOVEMENT,
    /**
     * if the case is selected the actually selected unit will attack the unit on
     * it.
     */
    ATTACK;

    /**{@inheritDoc}**/@Override
    public String getId() {
        return this.toString().toLowerCase() + "_selection";
    }
}
