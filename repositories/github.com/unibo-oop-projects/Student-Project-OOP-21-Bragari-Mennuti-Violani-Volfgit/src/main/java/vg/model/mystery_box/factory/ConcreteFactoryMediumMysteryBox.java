package vg.model.mystery_box.factory;

import vg.model.mystery_box.AbilityInTheBox;
import vg.model.mystery_box.concrete.AbilityFreezeTimeImpl;
import vg.model.mystery_box.concrete.AbilityKillMosquitoesImpl;
import vg.model.mystery_box.concrete.AbilityScoreImpl;
import vg.model.mystery_box.concrete.AbilitySpeedImpl;

/**
 * This is the concrete factory for the medium mystery box.
 */
public class ConcreteFactoryMediumMysteryBox extends AbstractFactoryMysteryBox {
    /**
     * {@inheritDoc}
     */
    @Override
    public AbilityInTheBox createFreezeTime() {
        final int duration = 4000;
        return new AbilityFreezeTimeImpl(duration);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AbilityInTheBox createSpeedUp() {
        final int duration = 4000;
        final int increaseSpeed = 1;
        return new AbilitySpeedImpl(duration, increaseSpeed);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AbilityInTheBox createScore() {
        final int increase = 500;
        return new AbilityScoreImpl(increase);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public AbilityInTheBox createKillMosquitoes() {
        return new AbilityKillMosquitoesImpl();
    }
}
