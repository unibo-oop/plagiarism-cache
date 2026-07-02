package todo.view.entities.level.builders;

import todo.view.entities.EntityBuilder;
import todo.view.entities.level.ValueBox;
import todo.vm.Value;

/**
 * This interface represents a builder that creates a {@link ValueBox}.
 *
 * @param <S> is the self type
 */
public interface ValueBoxBuilder<S extends ValueBoxBuilder<? extends S>> extends EntityBuilder<S, ValueBox> {
    /**
     * Set the initial value of the box.
     * 
     * @param value is the value of the box
     * @return the builder
     */
    S value(Value value);
}
