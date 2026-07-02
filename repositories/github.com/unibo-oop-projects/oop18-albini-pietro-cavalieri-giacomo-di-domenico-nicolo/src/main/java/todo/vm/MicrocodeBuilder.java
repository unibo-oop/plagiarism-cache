package todo.vm;

import todo.vm.instructions.MicroInstruction;

/**
 * This interface allows instructions to add microinstructions to the program
 * executed by the VM. Implementations of it are only available to instructions,
 * and it's not supposed to be used outside of the VM.
 */
public interface MicrocodeBuilder {
    /**
     * Add the provided microinstruction to the executable program.
     *
     * @param micro the microinstruction to add
     */
    void add(MicroInstruction micro);
}
