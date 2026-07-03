package model.bonus;

import model.state.State;

/**
 * 
 * Interface for a Factory of BonusCards.
 *
 */
public interface BonusFactory {

    /**
     * 
     * @return BonusCard of type Jolly;
     *
     */
    BonusCard getJolly();

    /**
     * @param state
     *            state associated to the new StateBonusCard;
     * 
     * @return StateBonusCard of type Cavalry containing the state "state";
     *
     */
    StateBonusCard getCavalry(State state);

    /**
     * @param state
     *            state associated to the new StateBonusCard;
     * 
     * @return StateBonusCard of type Infantry containing the state "state";
     *
     */
    StateBonusCard getInfantry(State state);

    /**
     * @param state
     *            state associated to the new StateBonusCard;
     * 
     * @return StateBonusCard of type Infantry containing the state "state";
     *
     */
    StateBonusCard getArtillery(State state);

}
