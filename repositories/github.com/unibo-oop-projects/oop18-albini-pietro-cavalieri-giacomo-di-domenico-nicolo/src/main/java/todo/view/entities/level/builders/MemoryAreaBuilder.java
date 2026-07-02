package todo.view.entities.level.builders;

import java.util.List;
import java.util.Optional;

import todo.view.entities.EntityBuilder;
import todo.view.entities.level.MemoryArea;
import todo.view.entities.level.ValueBox;

/**
 * This interface represents a builder to create {@link MemoryArea} objects.
 *
 * @param <S> is the self type
 */
public interface MemoryAreaBuilder<S extends MemoryAreaBuilder<? extends S>> extends EntityBuilder<S, MemoryArea> {
    /**
     * Set the size of the memory area.
     *
     * @param rows is the number of rows
     * @param columns is the number of columns
     * @return the builder
     */
    S size(int rows, int columns);

    /**
     * Set the initial values of the memory area.
     *
     * @param values is a list of value boxes; only the first rows * columns
     *            elements will be taken
     * @return the builder
     */
    S initialValues(List<Optional<ValueBox>> values);
}
