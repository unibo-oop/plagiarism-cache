package model.bonus;

import model.state.State;

/**
 * 
 * The interface models the concept of a card bonus with a link to a state.
 *
 */
public interface StateBonusCard extends BonusCard {
    /**
     * 
     * @return the state assigned to the BonusCard.
     * 
     */
    State getState();
}
