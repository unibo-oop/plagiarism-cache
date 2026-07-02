package todo.view.drawables.level.ui;

import java.util.List;

import todo.vm.instructions.Instruction;

/**
 * This factory is used to produce {@link ProgramInstructionActor} from a
 * specific instance of instruction.
 */
public interface ProgramInstructionActorFactory {
    /**
     * Create a {@link ProgramInstructionActor}, given the instruction instance.
     *
     * @param instruction is the instruction to be matched to the actor
     * @return a {@link ProgramInstructionActor} that represents the instruction
     */
    ProgramInstructionActor fromInstruction(Instruction instruction);

    /**
     * @return a list of used labels
     */
    List<Integer> getUsedLabels();

    /**
     * Refresh the used labels list so that deleted instructions get deleted from
     * the table, and the labels can be reused.
     *
     * @param instructions is the list of current instructions
     */
    void refreshUsedLabels(List<Instruction> instructions);
}
