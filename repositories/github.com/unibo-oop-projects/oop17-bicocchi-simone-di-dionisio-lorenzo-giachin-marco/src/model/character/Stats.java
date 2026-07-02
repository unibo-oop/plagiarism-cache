package model.character;

import model.AbstractValue;

/**
 * 
 * class for the stats of character.
 *
 */
public class Stats extends AbstractValue {

    /**
     * 
     */
    private static final long serialVersionUID = -283799865278220113L;

    /**
     * 
     * @param newName
     *            is the new name
     * @param newValue
     *            is the new value
     */
    public Stats(final String newName, final double newValue) {
        super(newName, newValue);
    }

    /**
     * 
     * @param newValue
     *            is the value to sub
     * @return true if the character is not dead.
     */
    public boolean modStat(final double newValue) {
        this.setValue(this.getValue() + newValue);
        return this.getValue() > 0;
    }

}
