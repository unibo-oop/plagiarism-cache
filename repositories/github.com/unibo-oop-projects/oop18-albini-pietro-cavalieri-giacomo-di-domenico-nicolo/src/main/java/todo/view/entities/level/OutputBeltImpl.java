package todo.view.entities.level;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.view.entities.EntityVisitor;
import todo.view.entities.level.builders.BaseBeltBuilder;
import todo.view.entities.tasks.common.ListDeltaVector2InterpolateTask;
import todo.view.rooms.AnimationsSpeed;

/**
 * This class represents an implementation of the {@link OutputBelt} interface.
 */
public final class OutputBeltImpl extends BaseBelt implements OutputBelt {
    private final AnimationsSpeed animationsSpeed;

    private OutputBeltImpl(final Vector2 endPosition, final float horizontalBoxMargin, final float verticalBoxMargin,
            final AnimationsSpeed animationsSpeed) {
        super(endPosition, horizontalBoxMargin, verticalBoxMargin);
        this.animationsSpeed = Objects.requireNonNull(animationsSpeed);
    }

    @Override
    public <T> T visit(final EntityVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void enqueue(final ValueBox valueBox) {
        super.getModifiableCollection().add(valueBox);
        valueBox.setParent(this);
        valueBox.setPosition(getEndPosition().cpy().add(getHorizontalBoxMargin(), 0f));
        // Move every value box forward
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
        getLoopableTaskManager().add(new ListDeltaVector2InterpolateTask(from, new Vector2(0f, -getVerticalBoxMargin()),
                setter, this.animationsSpeed.baseSpeed(), Interpolation.linear));
    }

    /**
     * This class is responsible for building the output belt.
     */
    public static final class Builder extends BaseBeltBuilder<Builder, OutputBelt> {
        @Override
        protected OutputBelt callConstructor() {
            return new OutputBeltImpl(super.getEndPosition(), super.getHorizontalMargin(), super.getVerticalMargin(),
                    super.getAnimationsSpeed());
        }
    }
}
