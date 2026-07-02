package ballblast.model.gameobjects;

import java.util.Optional;
import com.google.common.base.MoreObjects;

/**
 * Implements the GameObject {@link Ball}.
 */
public final class Ball extends AbstractGameObject {
    private BallType ballType;
    private int initialLife;
    private int currentLife;

    /**
     * Class constructor.
     */
    private Ball() {
        super(GameObjectType.BALL);
    }

    /**
     * Sets the current {@link Ball}'s life.
     * 
     * @param life the current {@link Ball}'s life.
     */
    public void setCurrentLife(final int life) {
        this.currentLife = life;
    }

    /**
     * Gets the initial {@link Ball}'s life.
     * 
     * @return the intial {@link Ball}'s life.
     */
    public int getInitialLife() {
        return this.initialLife;
    }

    /**
     * Gets the current {@link Ball}'s life.
     * 
     * @return the current {@link Ball}'s life.
     */
    public int getCurrentLife() {
        return this.currentLife;
    }

    /**
     * Gets the {@link Ball}'s type.
     * 
     * @return the {@link Ball}'s type.
     */
    public BallType getBallType() {
        return this.ballType;
    }

    private void setInitialLife(final int life) {
        this.initialLife = life;
        this.currentLife = life;
    }

    /**
     * Sets the {@link Ball}'s type.
     * 
     * @param ballType the {@link Ball}'s type.
     */
    private void setBallTypes(final BallType ballType) {
        this.ballType = ballType;
        this.setHeight(ballType.getDiameter());
        this.setWidth(ballType.getDiameter());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("GameObjectType", this.getType())
                .add("BallType", this.ballType)
                .add("Diameter", this.ballType.getDiameter())
                .add("InitialLife", this.initialLife)
                .add("Position", this.getPosition().toString())
                .add("IsDestroyed", this.isDestroyed())
                .toString();
    }

    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Ball, Builder> {
        /**
         * Sets the {@link GameObject}.
         * 
         * @param life the {@link Ball} life.
         * @return the {@link Builder}.
         */
        public Builder setLife(final int life) {
            this.getGameObject().setInitialLife(life);
            return this;
        }

        /**
         * Sets the {@link Ball}'s type.
         * 
         * @param ballType the {@link Ball}'s type.
         * @return the {@link Builder}.
         */
        public Builder setBallType(final BallType ballType) {
            this.getGameObject().setBallTypes(ballType);
            return this;
        }

        @Override
        public final Ball build() {
            if (!Optional.ofNullable(getGameObject().getBallType()).isPresent()) {
                throw new IllegalStateException("BallType unset!");
            }
            return this.getGameObject();
        }

        @Override
        protected final Ball initGameObject() {
            return new Ball();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
