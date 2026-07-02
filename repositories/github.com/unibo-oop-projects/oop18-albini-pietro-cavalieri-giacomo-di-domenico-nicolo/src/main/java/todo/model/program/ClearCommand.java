package todo.model.program;

import java.util.ArrayList;
import java.util.List;

import todo.vm.instructions.Instruction;

class ClearCommand extends AbstractCommand {
    private final List<Instruction> removedInstructions;

    ClearCommand(final List<Instruction> instructions) {
        super(instructions);
        this.removedInstructions = new ArrayList<>(instructions);
    }

    @Override
    protected void onExecute() {
        this.instructions.clear();
    }

    @Override
    protected void onUnexecute() {
        this.instructions.addAll(this.removedInstructions);
    }

}
