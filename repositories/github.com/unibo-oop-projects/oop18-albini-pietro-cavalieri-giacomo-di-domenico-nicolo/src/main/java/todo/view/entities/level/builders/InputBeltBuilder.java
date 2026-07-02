package todo.view.entities.level.builders;

import java.util.List;

import todo.view.entities.level.InputBelt;
import todo.view.entities.level.ValueBox;

/**
 * This interface represents a builder to create {@link InputBelt} objects.
 *
 * @param <S> is the self type
 */
public interface InputBeltBuilder<S extends InputBeltBuilder<? extends S>> extends BeltBuilder<S, InputBelt> {
    /**
     * Add the specified initial boxes to the input belt.
     *
     * @param boxes is a comma-separated argument list of the initial
     *            {@link ValueBox}.
     * @return the builder
     */
    S valueBoxes(ValueBox... boxes);

    /**
     * Add the specified initial boxes to the input belt.
     *
     * @param boxes is a list of the initial {@link ValueBox}.
     * @return the builder
     */
    S valueBoxes(List<ValueBox> boxes);
}
