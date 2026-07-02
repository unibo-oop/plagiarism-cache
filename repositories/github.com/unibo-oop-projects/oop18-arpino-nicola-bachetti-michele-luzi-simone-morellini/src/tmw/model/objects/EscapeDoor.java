package tmw.model.objects;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.entities.MilkEntity;

/**
 * This class represents the game door which, on contact with player,allows to
 * go to next room {@link WorldDispenser}. It's an extension of {@link Trigger}.
 * 
 */
public class EscapeDoor extends Trigger {

    private static final double PROPORTION_DOOR = 0.08;

    /**
     * Public constructor for an EscapeDoor.
     * 
     * @param position  {@link P2d} door position
     * @param fieldSize {@link Dimension2D} game resolution
     */
    public EscapeDoor(final P2d position, final Dim2D fieldSize) {
        super(position, new Dim2D(fieldSize.getWidth(), fieldSize.getWidth()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        setDimension(new Dim2D(PROPORTION_DOOR * dimension.getWidth(),
                PROPORTION_DOOR * dimension.getWidth()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersect(final GameObject object) {

        if (object instanceof MilkEntity && super.intersect(object)) {
            if (this.isTriggered()) {
                activateTrigger();
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activateTrigger() {
    }
}

