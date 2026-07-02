package todo.view.entities.level;

import todo.utils.Copyable;
import todo.view.entities.Entity;
import todo.vm.Value;

/**
 * This interface represents a {@link Value} as a box.
 */
public interface ValueBox extends Entity, Copyable<ValueBox> {
    /**
     * @return the value represented by the box
     */
    Value getValue();

    /**
     * Update the content of this box.
     *
     * @param value the new value of this box
     */
    void setValue(Value value);
}
