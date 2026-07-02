package it.unibo.the100dayswar.model.cell.api;

import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;

/**
 * Interface that represents a cell that can give a bonus to a player.
 */
public interface BonusCell extends Cell, ResourceGenerator {
    /**
     * 
     * @return true if the bonus can be given, false otherwise.
     */
    boolean isBonusActive();
    /**
     * 
     * @param bonusActive the new value of the bonus.
     */
    void setBonusActive(boolean bonusActive);
}
