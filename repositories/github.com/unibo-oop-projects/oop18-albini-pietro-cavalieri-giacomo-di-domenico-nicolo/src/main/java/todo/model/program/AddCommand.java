package todo.model.program;

import java.util.List;
import java.util.Objects;

import todo.utils.Checks;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.JumpTargetInstruction;

class AddCommand extends AbstractCommand {
    private final Instruction toAdd;
    private final int index;

    AddCommand(final List<Instruction> instructions, final int index, final Instruction toAdd) {
        super(instructions);
        Checks.require(!(toAdd instanceof JumpTargetInstruction), IllegalArgumentException.class,
                "A target can not directly be added to a Program!");
        this.toAdd = Objects.requireNonNull(toAdd);
        this.index = index;
    }

    @Override
    protected void onExecute() {
        addToList(this.toAdd, this.index);
        if (this.toAdd instanceof JumpInstruction) {
            addToList(((JumpInstruction) this.toAdd).getTarget(), this.index + 1);
        }
    }

    @Override
    protected void onUnexecute() {
        if (this.toAdd instanceof JumpInstruction) {
            this.instructions.remove(this.index + 1);
        }
        this.instructions.remove(this.index);
    }

    private void addToList(final Instruction instruction, final int index) {
        if (index >= this.instructions.size()) {
            this.instructions.add(instruction);
        } else {
            this.instructions.add(index, instruction);
        }
    }
}
