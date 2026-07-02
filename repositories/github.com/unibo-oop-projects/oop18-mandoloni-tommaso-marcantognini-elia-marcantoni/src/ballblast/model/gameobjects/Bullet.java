package ballblast.model.gameobjects;

import com.google.common.base.MoreObjects;

/**
 * Represents a {@link Bullet} that can be shot by the {@link Player} to hit the
 * balls. It can collide only with {@link Wall}s and {@link Ball}s.
 *
 */
public final class Bullet extends AbstractGameObject {
    private static final double DEFAULT_WIDTH = 1.4;
    private static final double DEFAUTL_HEIGHT = 2;

    /**
     * Class constructor.
     */
    private Bullet() {
        super(GameObjectType.BULLET);
        this.setHeight(DEFAUTL_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
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
    public static class Builder extends AbstractGameObject.AbstractBuilder<Bullet, Builder> {
        @Override
        public final Bullet build() {
            return this.getGameObject();
        }

        @Override
        protected final Bullet initGameObject() {
            return new Bullet();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
