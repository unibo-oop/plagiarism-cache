package model.entities;

import java.awt.Dimension;

public class BarrelTriggerImpl extends DynamicEntityImpl implements BarrelTrigger {

    public BarrelTriggerImpl(final Double x, final Double y, final Dimension dim) {
        super(x, y, dim);
    }

    @Override
    protected void tryToMove(final Movement dir) {
        this.addMovement(dir);
    }

    
}
