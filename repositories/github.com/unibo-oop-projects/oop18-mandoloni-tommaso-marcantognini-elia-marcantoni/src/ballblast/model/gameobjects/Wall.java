package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents a {@link Wall} object used to create the Level's
 * boundaries.
 */
public final class Wall extends AbstractGameObject {

    /**
     * Class constructor.
     */
    private Wall() {
        super(GameObjectType.WALL);
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
    public static class Builder extends AbstractGameObject.AbstractBuilder<Wall, Builder> {
        @Override
        public final Wall build() {
            return this.getGameObject();
        }

        /**
         * Sets the {@link Wall}'s height.
         * 
         * @param height the height of the {@link Wall}.
         * @return the {@link Builder}.
         */
        public Builder setHeight(final double height) {
            this.getGameObject().setHeight(height);
            return this;
        }

        /**
         * Sets the {@link Wall}'s width.
         * 
         * @param width the width of the {@link Wall}.
         * @return the {@link Builder}.
         */
        public Builder setWidth(final double width) {
            this.getGameObject().setWidth(width);
            return this;
        }

        @Override
        protected final Wall initGameObject() {
            return new Wall();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
