package todo.model.level.inputs;

import java.util.List;
import java.util.Objects;

import todo.utils.Checks;
import todo.vm.Value;

/**
 * This abstract class provides a basic implementation of an
 * {@link InputModifier}. It uses a template method to specify how to update the
 * input.
 */
public abstract class AbstractInputModifier implements InputModifier {
    private List<Value> input;

    @Override
    public final InputModifier setInitialValues(final List<Value> values) {
        this.input = Objects.requireNonNull(values);
        Checks.require(!values.isEmpty(), IllegalStateException.class);
        return this;
    }

    @Override
    public final void update() {
        modify(this.input);
    }

    protected abstract void modify(final List<Value> input);
}
