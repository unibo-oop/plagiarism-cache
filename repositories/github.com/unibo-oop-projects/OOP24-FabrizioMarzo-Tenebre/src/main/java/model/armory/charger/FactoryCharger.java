package model.armory.charger;

import org.apache.commons.lang3.tuple.Pair;

/**
 * {@inheritDoc}
 */
public class FactoryCharger implements IFactoryCharger {
    /**
     * {@inheritDoc}
     */
    public Charger createDrumCharger(final Pair<Double, Double> pos) {
        final int capacity = 5;
        return new Drum(capacity, pos);
    }
}
