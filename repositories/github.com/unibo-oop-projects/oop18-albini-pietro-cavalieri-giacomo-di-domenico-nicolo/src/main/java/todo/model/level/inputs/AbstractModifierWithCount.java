package todo.model.level.inputs;

import java.util.List;
import java.util.Optional;

import todo.utils.Checks;
import todo.vm.Value;

public abstract class AbstractModifierWithCount extends AbstractInputModifier implements ModifierWithCount {
    private Optional<Integer> minCount;

    @Override
    public void setCount(final int minCount) {
        Checks.require(minCount > 0, IllegalArgumentException.class);
        this.minCount = Optional.of(minCount);
    }

    @Override
    protected abstract void modify(List<Value> input);

    protected int getCount() {
        return this.minCount.orElseThrow(IllegalStateException::new);
    }
}
