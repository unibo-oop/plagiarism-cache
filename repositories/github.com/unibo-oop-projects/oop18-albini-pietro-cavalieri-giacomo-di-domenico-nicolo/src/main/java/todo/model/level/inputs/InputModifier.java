package todo.model.level.inputs;

import java.util.List;

import todo.vm.Value;

/**
 * This interface models a modifier for an input. It takes a {@link List} of
 * initial values to work with and updates them to the specific request.
 */
public interface InputModifier {
    /**
     * Modifies the wrapped input
     *
     * @return the modified input
     */
    void update();

    /**
     * @param values the values to be modified by this modifier
     * @return this for fluent construction
     */
    InputModifier setInitialValues(List<Value> values);
}
