package model.bonus;

import model.state.State;

/**
 * 
 * The Class models the concept of a bonus card with a link to a state.
 *
 */
public class StateBonusCardImpl extends BonusCardImpl implements StateBonusCard {

    private static final long serialVersionUID = 275025866324534516L;
    private final State state;
    /**
     * 
     * @param type
     *          type of bonus of this card.
     * 
     * @param state
     *          state associated to this bonus card
     */
    public StateBonusCardImpl(final Bonus type, final State state) {
        super(type);
        this.state = state;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return super.toString() + " State: " + this.state.getName();
    }

}
