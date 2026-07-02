package todo.model.level.inputs;

import java.util.List;

import todo.vm.Value;

/**
 * This modifier sets the last {@link Value} of the input to zero.
 */
public class EnsureLastZero extends AbstractInputModifier {

    @Override
    protected void modify(final List<Value> input) {
        input.set(input.size() - 1, Value.number(0));
    }
}
