package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents the entity used by the user, who is able to move and shoot. When
 * hit by a ball it dies.
 */
public final class Player extends AbstractGameObject {
    private static final double DEFAULT_HEIGHT = 12;
    private static final double DEFAULT_WIDTH = 6;
    private static final double DEFAULT_SPEED = 50;
    private double currentSpeed;
    private boolean immune;

    /**
     * Class constructor.
     */
    private Player() {
        super(GameObjectType.PLAYER);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
        this.currentSpeed = DEFAULT_SPEED;
    }

    /**
     * Sets {@link Player}'s speed.
     * 
     * @param speed the current {@link Player}'s speed.
     */
    public void setSpeed(final double speed) {
        this.currentSpeed = speed;
    }

    /**
     * Gets {@link Player}'s speed.
     * 
     * @return the current {@link Player}'s speed.
     */
    public double getSpeed() {
        return this.currentSpeed;
    }

    /**
     * Check if the {@link Player} is immune.
     * 
     * @return true if the {@link Player} is immune, false otherwise.
     */
    public boolean isImmune() {
        return this.immune;
    }

    /**
     * Sets the {@link Player}'s immunity.
     * 
     * @param immune true if the {@link Player} is immune, false otherwise.
     */
    public void setImmunity(final boolean immune) {
        this.immune = immune;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("GameObjectType", this.getType())
                .add("Position", this.getPosition().toString())
                .add("IsDestroyed", this.isDestroyed())
                .toString();
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Player, Builder> {
        /**
         * Sets the {@link Player}'s speed.
         * 
         * @param speed the {@link Player}'s speed to be set.
         * @return the concrete {@link Builder}.
         */
        public Builder setSpeed(final double speed) {
            this.getGameObject().setSpeed(speed);
            return this;
        }

        /**
         * Sets the {@link Player}'s immunity.
         * 
         * @param immune true if the {@link Player} is immune, false otherwise.
         * @return the concrete {@link Builder}.
         */
        public Builder setImmunity(final boolean immune) {
            this.getGameObject().setImmunity(immune);
            return this;
        }

        @Override
        public final Player build() {
            return this.getGameObject();
        }

        @Override
        protected final Player initGameObject() {
            return new Player();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
