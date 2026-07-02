package vg.model.mystery_box.data_round;

import vg.utils.V2D;

/**
 * This class represents the data of the round for mystery box.
 */
public class DataRoundImpl implements DataRound {
    private final V2D position;
    private final boolean blinking;
    public DataRoundImpl(final V2D position, final boolean blinking) {
        this.position = position;
        this.blinking = blinking;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public V2D getPosition() {
        return this.position;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlinking() {
        return this.blinking;
    }
}
