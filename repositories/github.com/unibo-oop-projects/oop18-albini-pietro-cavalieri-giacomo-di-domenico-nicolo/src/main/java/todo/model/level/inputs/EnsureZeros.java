package todo.model.level.inputs;

import java.util.List;

import todo.vm.Value;

/**
 * This modifier inserts a specific number of zero {@link Value} in the input.
 * Right now it places them at the beginning, if {@link ShuffleInput} is called
 * after they will be in random positions.
 */
public class EnsureZeros extends AbstractModifierWithCount {

    @Override
    protected void modify(final List<Value> input) {
        input.sort(new EmptyValueComparator());
        for (int i = 0; i < getCount(); i++) {
            input.set(i, Value.number(0));
        }
    }
}
