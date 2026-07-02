package todo.model.level.inputs;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import todo.utils.Checks;
import todo.vm.Value;

public class EnsurePositives extends AbstractModifierWithCount implements ModifierWithBound {
    private Optional<Integer> bound;

    @Override
    protected void modify(final List<Value> input) {
        Checks.require(this.bound.isPresent(), IllegalStateException.class);
        input.sort(new EmptyValueComparator());
        for (int i = 0; i < getCount(); i++) {
            input.set(i, Value.number(new Random().nextInt(this.bound.get()) + 1));
        }
    }

    @Override
    public void setBound(final int bound) {
        Checks.require(bound > 0, IllegalArgumentException.class);
        this.bound = Optional.of(bound);
    }
}
