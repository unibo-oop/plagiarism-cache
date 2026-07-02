package todo.view.drawables.level.ui;

import java.util.List;

import todo.vm.instructions.Instruction;

/**
 * This interface represents a factory that creates {@link InstructionActor}
 * given its corresponding instruction class.
 */
public interface InstructionActorFactory {
    /**
     * Get an instruction actor that represents an instruction given its class.
     *
     * @param instruction is the instruction class associated to the desired button
     * @return the created instruction actor
     */
    InstructionActor fromInstructionClass(Class<? extends Instruction> instruction);

    /**
     * @return an instruction actor for each supported instruction class
     */
    List<InstructionActor> getAll();
}
