package todo.view.entities.level;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.utils.Checks;
import todo.view.entities.EntityVisitor;
import todo.view.entities.level.builders.BaseBeltBuilder;
import todo.view.entities.level.builders.InputBeltBuilder;
import todo.view.entities.tasks.common.ListDeltaVector2InterpolateTask;
import todo.view.rooms.AnimationsSpeed;

/**
 * This class represents an implementation of the {@link InputBelt} interface.
 */
public final class InputBeltImpl extends BaseBelt implements InputBelt {
    private final AnimationsSpeed animationsSpeed;

    private InputBeltImpl(final Vector2 endPosition, final float horizontalBoxMargin, final float verticalBoxMargin,
            final AnimationsSpeed animationsSpeed, final List<ValueBox> initialBoxes) {
        super(endPosition, horizontalBoxMargin, verticalBoxMargin);
        super.getModifiableCollection().addAll(Objects.requireNonNull(initialBoxes));
        this.animationsSpeed = Objects.requireNonNull(animationsSpeed);
    }

    @Override
    public <T> T visit(final EntityVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Optional<ValueBox> poll() {
        final Optional<ValueBox> polled = Optional.ofNullable(super.getModifiableCollection().poll());
        if (polled.isPresent()) {
            // Unparent the value box
            polled.get().removeParent();
            // Move the other ones
            final List<Vector2> from = super.getModifiableCollection().stream()
                                                                      .map(vb -> vb.getRelativePosition())
                                                                      .collect(Collectors.toList());
            final Consumer<List<Vector2>> setter = list -> {
                final Iterator<Vector2> interpolated = list.iterator();
                final Iterator<ValueBox> toApply = super.getModifiableCollection().iterator();
                while (toApply.hasNext() && interpolated.hasNext()) {
                    toApply.next().setRelativePosition(interpolated.next());
                }
            };
            getLoopableTaskManager().add(
                    new ListDeltaVector2InterpolateTask(from, new Vector2(0f, getVerticalBoxMargin()), setter,
                            this.animationsSpeed.baseSpeed(), Interpolation.linear));
            return polled;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ValueBox> peek() {
        return Optional.ofNullable(super.getModifiableCollection().peek());
    }

    /**
     * This class is responsible for building the input belt.
     */
    public static final class Builder extends BaseBeltBuilder<Builder, InputBelt> implements InputBeltBuilder<Builder> {
        private final List<ValueBox> initialBoxes = new LinkedList<>();

        @Override
        public Builder valueBoxes(final ValueBox... boxes) {
            final List<ValueBox> toAdd = Arrays.asList(boxes);
            Checks.requireOnIterable(toAdd, b -> b != null, IllegalArgumentException.class,
                    "One or more boxes are null");
            this.initialBoxes.addAll(toAdd);
            return this;
        }

        @Override
        public Builder valueBoxes(final List<ValueBox> boxes) {
            Checks.require(Objects.requireNonNull(boxes).size() > 0, IllegalArgumentException.class,
                    "The input list must be non-empty");
            this.initialBoxes.addAll(boxes);
            return this;
        }

        @Override
        protected InputBelt callConstructor() {
            return new InputBeltImpl(super.getEndPosition(), super.getHorizontalMargin(), super.getVerticalMargin(),
                    super.getAnimationsSpeed(), new LinkedList<>(this.initialBoxes));
        }

        @Override
        protected InputBelt additionalBuild(final InputBelt belt) {
            for (int i = 0; i < this.initialBoxes.size(); i++) {
                final ValueBox box = this.initialBoxes.get(i);
                box.setParent(belt);
                box.setRelativePosition(new Vector2(belt.getHorizontalBoxMargin(),
                        belt.getEndPosition().y - belt.getPosition().y - belt.getVerticalBoxMargin() * i));
            }
            return belt;
        }
    }
}
