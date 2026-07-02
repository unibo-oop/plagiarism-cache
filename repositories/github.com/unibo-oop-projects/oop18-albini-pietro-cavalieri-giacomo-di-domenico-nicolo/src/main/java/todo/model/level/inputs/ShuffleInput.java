package todo.model.level.inputs;

import java.util.Collections;
import java.util.List;

import todo.vm.Value;

/**
 * This modifier shuffles the input.
 */
public class ShuffleInput extends AbstractInputModifier {

    @Override
    protected void modify(final List<Value> input) {
        Collections.shuffle(input);
    }

}
