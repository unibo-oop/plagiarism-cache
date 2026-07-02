package todo.vm;

import java.util.List;
import java.util.NoSuchElementException;

import todo.utils.UniqueId;
import todo.vm.exceptions.VmRuntimeException;

public interface Vm {
    /**
     * Execute the program until it finishes. If the execution has already started
     * this method will *not* restart the execution from scratch, but it will resume
     * it from where it stopped.
     */
    void execute() throws VmRuntimeException;

    /**
     * Execute the program until an Action is done, then return that action.
     * Multiple instructions can be executed before an Action is done, but an
     * instruction can also yield more than one Action.
     *
     * @return Action the executed action
     */
    Action step() throws VmRuntimeException;

    /**
     * Get the list of values stored by the program on the output. The method can be
     * called at any time during the execution, but if it's called before the end of
     * the program it will not return the final results.
     *
     * @return the list of values stored on the output
     */
    List<Value> getOutput();

    /**
     * @return true if the execution of the program finished
     */
    boolean hasFinished();

    /**
     * @return the {@link UniqueId} of the next instruction
     */
    UniqueId getNextInstructionId();

    /**
     * @return the number of executed instructions
     */
    int getExecutedInstructionsCount();

    /**
     * @return the content of the provided memory address
     * @param memoryAddress the index of the memory address
     * @throws NoSuchElementException if the memory address does not exist
     */
    Value getMemoryAddressContent(int memoryAddress);

    /**
     * @return the content of the main hand
     */
    Value getMainHandContent();

    /**
     * This method returns false if the next operation the VM will execute is part
     * of the instruction executed during the last step. Sometimes the VM will split
     * an instruction into multiple internal steps, and this method allows the VM
     * consumer to better execute animations.
     *
     * @return true if the VM is placed at an instruction boundary
     */
    boolean isAtInstructionBoundary();
}
