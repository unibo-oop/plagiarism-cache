package ballblast.model.powerups;

import ballblast.model.gameobjects.AbstractGameObject;

/**
 * The class representing the speed Link Power.
 */
public final class SpeedPower extends AbstractPower {

    private static final double ENHANCED_SPEED = 90;
    private double prevSpeed;

    private SpeedPower() {
        super(PowerTypes.SPEED);
    }

    @Override
    protected void performPower() {
        this.prevSpeed = this.getPlayer().getSpeed();
        this.setSpeed(ENHANCED_SPEED);
    }

    @Override
    protected void stopPerforming() {
        this.setSpeed(this.prevSpeed);
    }

    private void setSpeed(final double speed) {
        this.getPlayer().setSpeed(speed);
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<SpeedPower, Builder> {
        @Override
        public final SpeedPower build() {
            return this.getGameObject();
        }

        @Override
        protected final SpeedPower initGameObject() {
            return new SpeedPower();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }

}
