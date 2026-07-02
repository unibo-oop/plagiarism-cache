package todo.vm.instructions;

import todo.utils.UniqueId;

/**
 * A jump target represents an instruction that acts as the target of another
 * {@link JumpInstruction}.
 */
public interface JumpTargetInstruction extends Instruction {
    /**
     * @return the {@link UniqueId} of the instruction that jumps to this target
     */
    UniqueId getSourceId();
}
