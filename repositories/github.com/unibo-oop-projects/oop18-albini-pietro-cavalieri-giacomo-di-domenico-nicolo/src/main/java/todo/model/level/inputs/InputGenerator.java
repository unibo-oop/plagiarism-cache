package todo.model.level.inputs;

import java.util.List;

import todo.vm.Value;

/**
 * This functional interface models an input generator. It has different
 * implementations for different input cases.
 */
public interface InputGenerator {
    /**
     * @return a new input as a list of {@link Value}
     */
    List<Value> getNewInput();

    /**
     * Set the minimum {@link Value} to be generated
     *
     * @param min the minimum valid {@link Value}
     * @return this allowing for fluent construction
     */
    InputGenerator setMin(int min);

    /**
     * Set the maximum {@link Value} to be generated
     *
     * @param max the maximum valid {@link Value}
     * @return this allowing for fluent construction
     */
    InputGenerator setMax(int max);

    /**
     * Set the size of the input
     *
     * @param size the size of the input
     * @return this allowing for fluent construction
     */
    InputGenerator setSize(int size);
}
