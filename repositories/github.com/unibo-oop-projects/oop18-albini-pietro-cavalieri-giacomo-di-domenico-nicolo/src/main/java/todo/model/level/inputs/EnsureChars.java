package todo.model.level.inputs;

import java.util.List;
import java.util.Random;

import todo.vm.Value;

/**
 * This modifier inserts a specific number of char {@link Value} in the input.
 * Right now it places them at the beginning, if {@link ShuffleInput} is called
 * after they will be in random positions.
 */
public class EnsureChars extends AbstractModifierWithCount {
    private static final int A = 65;
    private static final int Z = 90;

    @Override
    protected void modify(final List<Value> input) {
        input.sort(new EmptyValueComparator());
        for (int i = 0; i < getCount(); i++) {
            input.set(i, Value.ascii((char) (new Random().nextInt(Z - A) + A)));
        }
    }
}
