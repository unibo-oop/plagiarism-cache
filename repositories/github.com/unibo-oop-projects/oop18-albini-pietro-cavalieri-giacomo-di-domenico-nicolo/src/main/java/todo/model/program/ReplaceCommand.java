package todo.model.program;

import java.util.List;
import java.util.Objects;

import todo.vm.instructions.Instruction;

class ReplaceCommand extends AbstractCommand {
    private final Instruction instruction;
    private final Instruction removed;
    private final int index;

    ReplaceCommand(final List<Instruction> instructions, final int index, final Instruction instruction) {
        super(instructions);
        this.instruction = Objects.requireNonNull(instruction);
        this.removed = instructions.get(index);
        this.index = index;
    }

    @Override
    protected void onExecute() {
        this.instructions.set(this.index, this.instruction);
    }

    @Override
    protected void onUnexecute() {
        this.instructions.set(this.index, this.removed);
    }

}
