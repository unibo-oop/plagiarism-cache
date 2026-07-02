package todo.model.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import todo.vm.instructions.Instruction;

public class ReplaceAllCommand extends AbstractCommand {
    private final List<Instruction> removed;
    private final List<Instruction> toPaste;

    ReplaceAllCommand(final List<Instruction> instructions, final List<Instruction> toPaste) {
        super(instructions);
        this.removed = new ArrayList<>(Objects.requireNonNull(instructions));
        this.toPaste = new ArrayList<>(Objects.requireNonNull(toPaste));
    }

    @Override
    protected void onExecute() {
        this.instructions.clear();
        this.instructions.addAll(this.toPaste);
    }

    @Override
    protected void onUnexecute() {
        this.instructions.clear();
        this.instructions.addAll(this.removed);
    }

}
