package ballblast.model.powerups;

import ballblast.model.components.ComponentType;
import ballblast.model.components.ShooterComponent;
import ballblast.model.gameobjects.AbstractGameObject;

/**
 * The class representing the double fire {@link Power}.
 */
public final class DoubleFirePower extends AbstractPower {

    private static final double DOUBLE_SHOT_INTERVAL = 0.055;

    private double prevShotInterval;

    private DoubleFirePower() {
        super(PowerTypes.DOUBLEFIRE);
    }

    @Override
    protected void performPower() {
        this.prevShotInterval = this.findShooter().getShotInterval();
        this.setShotInterval(DOUBLE_SHOT_INTERVAL);
    }

    @Override
    protected void stopPerforming() {
        this.setShotInterval(this.prevShotInterval);
    }

    private void setShotInterval(final double shotInterval) {
        this.findShooter().setShotInterval(shotInterval);
    }

    private ShooterComponent findShooter() {
        return this.getPlayer().getComponents().stream()
                .filter(c -> c.getType() == ComponentType.SHOOTER)
                .map(c -> (ShooterComponent) c)
                .findFirst().get();
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<DoubleFirePower, Builder> {
        @Override
        public final DoubleFirePower build() {
            return this.getGameObject();
        }

        @Override
        protected final DoubleFirePower initGameObject() {
            return new DoubleFirePower();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }

}
