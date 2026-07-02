package todo.view.drawables.level.ui;

import todo.utils.Copyable;
import todo.utils.Disposable;
import todo.vm.instructions.Instruction;

/**
 * This interface represents an instruction actor, which encapsulates a GDX
 * button.
 */
public interface InstructionActor extends Disposable, ActorHolder, Copyable<InstructionActor> {
    /**
     * @return the class of the instruction associated to this button
     */
    Class<? extends Instruction> getInstructionClass();
}
