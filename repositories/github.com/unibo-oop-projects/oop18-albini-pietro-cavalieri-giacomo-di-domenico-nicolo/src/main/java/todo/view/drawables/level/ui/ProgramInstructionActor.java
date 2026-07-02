package todo.view.drawables.level.ui;

import todo.vm.instructions.Instruction;

/**
 * This interface represents an {@link InstructionActor} that holds an instance
 * of a VM instruction.
 */
public interface ProgramInstructionActor extends InstructionActor {
    /**
     * @return the instruction associated with the button
     */
    Instruction getInstruction();
}
