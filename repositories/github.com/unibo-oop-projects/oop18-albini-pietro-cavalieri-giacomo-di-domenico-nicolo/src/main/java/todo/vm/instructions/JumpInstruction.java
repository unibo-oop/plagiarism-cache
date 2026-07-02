package todo.vm.instructions;

import todo.vm.printer.PrintableInstruction;

/**
 * This interface represents a generic Jump instruction, with the extra methods
 * they expose.
 */
public interface JumpInstruction extends Instruction {
    /**
     * Get the target of this jump. The returned target will be the same object even
     * if the method is called multiple times.
     *
     * @return the target of this jump
     */
    PrintableInstruction getTarget();
}
