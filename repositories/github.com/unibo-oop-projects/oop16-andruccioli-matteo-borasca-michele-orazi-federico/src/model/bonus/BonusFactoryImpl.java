package model.bonus;

import model.state.State;

/**
 * 
 * This Class implements a Factory of BonusCards.
 *
 */
public class BonusFactoryImpl implements BonusFactory {

    @Override
    public BonusCard getJolly() {
        return new BonusCardImpl(Bonus.JOLLY);
    }

    @Override
    public StateBonusCard getCavalry(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("state can't be null");
        }
        return new StateBonusCardImpl(Bonus.CAVALRY, state);
    }

    @Override
    public StateBonusCard getInfantry(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("state can't be null");
        }
        return new StateBonusCardImpl(Bonus.INFANTRY, state);
    }

    @Override
    public StateBonusCard getArtillery(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("state can't be null");
        }
        return new StateBonusCardImpl(Bonus.ARTILLERY, state);
    }

}
