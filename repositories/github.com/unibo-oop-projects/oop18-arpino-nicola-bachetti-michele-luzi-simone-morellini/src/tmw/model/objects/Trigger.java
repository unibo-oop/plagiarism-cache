package tmw.model.objects;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.entities.MilkEntity;

/**
 * Class to represent an invisible element which has an height equal to the
 * height of the entire map.
 * <p>
 * Extension of {@link BaseGameObject}
 */
public abstract class Trigger extends BaseGameObject implements TriggerInterface {

    private static final double DIMENSION_PROPORTION_TRIGGER = 0.08;
    private boolean triggered;

    /**
     * Public constructor.
     * 
     * @param position  trigger position
     * @param fieldSize The dimension of the field
     */
    public Trigger(final P2d position, final Dim2D fieldSize) {
        super(position, new Dim2D(fieldSize.getWidth() * DIMENSION_PROPORTION_TRIGGER,
                fieldSize.getHeight() * DIMENSION_PROPORTION_TRIGGER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        setDimension(new Dim2D(DIMENSION_PROPORTION_TRIGGER * dimension.getWidth(),
                dimension.getWidth() * DIMENSION_PROPORTION_TRIGGER));
    }

    /**
     * {@inheritDoc}
     */
    public abstract void activateTrigger();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersect(final GameObject object) {

        if (object instanceof MilkEntity && super.intersect(object)) {
            if (!this.isTriggered()) {
                this.activateTrigger();
                this.setTriggered(true);
            }

            return true;
        }
        return false;
    }

    /**
     * Check trigger status.
     * 
     * @return true if triggered
     */
    public boolean isTriggered() {
        return this.triggered;
    }

    /**
     * Sets trigger status.
     * 
     * @param triggered boolean value
     */
    public void setTriggered(final boolean triggered) {
        this.triggered = triggered;
    }
}
