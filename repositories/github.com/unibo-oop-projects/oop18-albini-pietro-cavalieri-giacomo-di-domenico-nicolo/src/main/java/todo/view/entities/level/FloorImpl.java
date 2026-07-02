package todo.view.entities.level;

import todo.utils.Checks;
import todo.view.entities.BaseEntity;
import todo.view.entities.EntityVisitor;
import todo.view.entities.level.builders.FloorBuilder;

public final class FloorImpl extends BaseEntity implements Floor {
    private final float width;
    private final float height;

    private FloorImpl(final float width, final float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public <T> T visit(final EntityVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    public static final class Builder extends BaseEntity.Builder<Builder, Floor> implements FloorBuilder<Builder> {
        private float width;
        private float height;

        @Override
        public Builder size(final float width, final float height) {
            this.width = width;
            this.height = height;
            return this;
        }

        @Override
        protected Floor callConstructor() {
            Checks.require(this.width > 0, IllegalArgumentException.class, "Width can't be negative");
            Checks.require(this.height > 0, IllegalArgumentException.class, "Height can't be negative");
            return new FloorImpl(this.width, this.height);
        }
    }
}
