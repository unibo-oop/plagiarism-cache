package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.api.FlameFactory;
import it.unibo.michelito.util.Position;

/**
 * Factory for creating flames.
 */
public class FlameFactoryImpl implements FlameFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Flame createFlame(final Position position) {
        return new FlameImpl(position);
    }
}
