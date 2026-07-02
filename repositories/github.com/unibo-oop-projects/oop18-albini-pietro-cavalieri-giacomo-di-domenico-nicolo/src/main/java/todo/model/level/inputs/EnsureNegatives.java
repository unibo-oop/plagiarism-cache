package todo.model.level.inputs;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import todo.utils.Checks;
import todo.vm.Value;

/**
 * This modifier inserts a specific number of negative {@link Value} in the
 * input. Right now it places them at the beginning, if {@link ShuffleInput} is
 * called after they will be in random positions.
 */
public class EnsureNegatives extends AbstractModifierWithCount implements ModifierWithBound {
    private Optional<Integer> bound;

    @Override
    protected void modify(final List<Value> input) {
        Checks.require(this.bound.isPresent(), IllegalStateException.class);
        input.sort(new EmptyValueComparator());
        for (int i = 0; i < getCount(); i++) {
            input.set(i, Value.number(new Random().nextInt(this.bound.get()) - this.bound.get()));
        }
    }

    @Override
    public void setBound(final int bound) {
        Checks.require(bound < 0, IllegalArgumentException.class);
        this.bound = Optional.of(-bound);
    }

}
