package todo.vm;

import todo.vm.exceptions.VmRuntimeException;

/**
 * This interface contains the methods used by microinstructions to alter the
 * internal state of the VM. Implementations of it are only available to
 * microinstructions, and it's not supposed to be used outside of the VM.
 */
public interface VmState {
    /**
     * Get the value of an internal VM register.
     *
     * @param register the register name
     * @return the value contained in the register
     */
    Value getRegister(Register register);

    /**
     * Set the value of an internal VM register.
     *
     * @param register the register name
     * @param value the new register value
     * @throws NullPointerException if the value is null
     */
    void setRegister(Register register, Value value);

    /**
     * Get the value stored in a memory address.
     *
     * @param address the memory address to fetch
     * @return the value stored in the memory address
     * @throws VmRuntimeException if the memory address does not exist
     */
    Value getMemoryAddress(int address) throws VmRuntimeException;

    /**
     * Set the value of a memory address.
     *
     * @param address the memory address to update
     * @param value the value to store
     * @throws VmRuntimeException if the memory address does not exist
     */
    void setMemoryAddress(int address, Value value) throws VmRuntimeException;

    /**
     * Check if a memory address exists.
     *
     * @param address the memory address to check
     * @return true if the memory address exists
     */
    boolean memoryAddressExists(int address);

    /**
     * Get the next input value.
     *
     * @return a Value from the input
     */
    Value getInput();

    /**
     * Add a value to the output.
     *
     * @param value the new output value
     * @throws VmRuntimeException if the value is empty or null
     */
    void addOutput(Value value) throws VmRuntimeException;

    /**
     * Terminate the execution of the program.
     */
    void endProgram();
}
