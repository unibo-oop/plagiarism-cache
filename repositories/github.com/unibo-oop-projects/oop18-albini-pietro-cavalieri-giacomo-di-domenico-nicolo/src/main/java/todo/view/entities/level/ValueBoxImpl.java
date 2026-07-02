package todo.view.entities.level;

import java.util.Objects;

import todo.utils.Checks;
import todo.view.entities.BaseEntity;
import todo.view.entities.EntityVisitor;
import todo.view.entities.level.builders.ValueBoxBuilder;
import todo.vm.Value;

/**
 * This class is an implementation of the {@link ValueBox} interface.
 */
public final class ValueBoxImpl extends BaseEntity implements ValueBox {
    private Value value;

    private ValueBoxImpl(final Value value) {
        this.value = value;
    }

    @Override
    public <T> T visit(final EntityVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value getValue() {
        return this.value;
    }

    @Override
    public void setValue(final Value value) {
        this.value = value;
    }

    @Override
    public ValueBox copy() {
        return new Builder().position(getPosition())
                            .rotation(getRotation())
                            .scale(getScale())
                            .value(this.value)
                            .build();
    }

    /**
     * This class is responsible for building the value box.
     */
    public static final class Builder extends BaseEntity.Builder<Builder, ValueBox>
            implements ValueBoxBuilder<Builder> {
        private Value currentValue;

        @Override
        public Builder value(final Value value) {
            this.currentValue = Objects.requireNonNull(value);
            return this;
        }

        @Override
        protected ValueBox callConstructor() {
            Checks.require(this.currentValue != null, IllegalArgumentException.class, "A value must be set");
            Checks.require(this.currentValue.isPresent(), IllegalArgumentException.class,
                    "The value must not be empty");
            return new ValueBoxImpl(this.currentValue);
        }
    }
}
