package todo.vm.instructions;

import todo.utils.Identifiable;
import todo.vm.MicrocodeBuilder;

/**
 * This interface represents an instruction, which is part of the program
 * submitted by the VM consumer to the VM itself.
 */
public interface Instruction extends Identifiable {
    /**
     * Add the microinstructions needed to execute this instruction to the provided
     * builder. This method is supposed to be called only by the VM.
     *
     * @param builder an object that allows adding microinstructions
     */
    void buildMicrocode(MicrocodeBuilder builder);

    /**
     * A dummy instruction is an instruction that the user didn't add explicitly,
     * for example the jump targets. Dummy instructions don't count torward the
     * instruction limit, and are always allowed even if not mentioned in the
     * level's allowed instructions list.
     *
     * @return true if the instruction is dummy
     */
    default boolean isDummy() {
        // This method has a default implementation inside the interface so it's
        // still possible to use Instruction as a functional interface.
        return false;
    }
}
