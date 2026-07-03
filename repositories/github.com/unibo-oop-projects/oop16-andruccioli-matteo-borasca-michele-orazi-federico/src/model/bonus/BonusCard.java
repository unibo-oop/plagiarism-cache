package model.bonus;

import java.io.Serializable;

/**
 * 
 * The interface models the concept of a card bonus.
 *
 */
public interface BonusCard extends Serializable {

    /**
     * 
     * @return the type of bonus card to which this one belong.
     * 
     */
    Bonus getBonusType();
}
