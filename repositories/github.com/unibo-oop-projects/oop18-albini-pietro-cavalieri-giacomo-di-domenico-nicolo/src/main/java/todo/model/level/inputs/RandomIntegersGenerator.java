package todo.model.level.inputs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import todo.utils.Checks;
import todo.vm.Value;

/**
 * This version of the InputGenerator takes a {@link List} of initial
 * {@link Value} and if there are still empty {@link Value} it fills them with
 * random integers.
 */
public class RandomIntegersGenerator implements InputGenerator {
    private final List<Value> values;
    private final List<InputModifier> modifiers;
    private int minValue;
    private int maxValue;
    private int size;

    public RandomIntegersGenerator(final List<InputModifier> modifiers) {
        this.modifiers = Objects.requireNonNull(modifiers);
        this.values = new ArrayList<>();
    }

    @Override
    public List<Value> getNewInput() {
        Checks.require(this.maxValue > this.minValue, IllegalStateException.class);
        Checks.require(this.size > 0, IllegalStateException.class);
        this.values.clear();
        this.values.addAll(Stream.generate(Value::empty).limit(this.size).collect(Collectors.toList()));
        this.modifiers.forEach(mod -> mod.setInitialValues(this.values).update());
        return this.values.stream().map(val -> {
            if (!val.isPresent()) {
                return Value.number(new Random().nextInt(this.maxValue - this.minValue) + this.minValue);
            }
            return val;
        }).collect(Collectors.toList());
    }

    @Override
    public InputGenerator setMin(final int min) {
        this.minValue = min;
        return this;
    }

    @Override
    public InputGenerator setMax(final int max) {
        this.maxValue = max;
        return this;
    }

    @Override
    public InputGenerator setSize(final int size) {
        this.size = size;
        return this;
    }
}
