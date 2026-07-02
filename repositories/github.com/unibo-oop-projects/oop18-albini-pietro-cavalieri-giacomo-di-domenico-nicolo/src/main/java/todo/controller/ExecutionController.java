package todo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import todo.vm.Action;
import todo.vm.Value;
import todo.vm.Vm;

/**
 * An ExecutionController is an entity used to control the execution of a vm
 * providing methods like {@link #step()} which prompts the execution of the
 * next instruction or {@link #stop()} which stops the execution of the vm.
 */
public interface ExecutionController {
    /**
     * Performes the next action the {@link Vm} has to execute.
     *
     * @return the {@link Action} performed by the {@link Vm}
     * @throws IllegalStateException if called after {@link #stop()}
     */
    Action step();

    /**
     * Stops the execution of the virtual machine, making the user no longer able to
     * use the {@link #step()} method.
     */
    void stop();

    /**
     * Get the list of values stored by the {@link Vm} on the output. The method can
     * be called at any time during the execution, but if it's called before the end
     * of the program it will return partial results.
     *
     * @return the list of values stored on the output
     */
    List<Value> getOutput();

    /**
     * @return the content of the provided memory address
     * @param memoryAddress the memory address whose content has to be retrieved
     * @throws NoSuchElementException if the memory address does not exist
     */
    Value getMemoryAddressContent(int memoryAddress);

    /**
     * @return the content of the main hand
     */
    Value getMainHandContent();

    /**
     * @return true if the execution of the program has finished
     */
    boolean hasFinished();

    /**
     * See the documentation of {@link Vm#isAtInstructionBoundary} for more
     * information.
     *
     * @return true if the VM is at an instruction boundary
     */
    boolean isVmAtInstructionBoundary();
}
