package todo.model.program;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import todo.utils.UniqueId;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.JumpTargetInstruction;

class RemoveCommand extends AbstractCommand {
    private final Instruction removed;
    private final int index;
    private Optional<Instruction> connectedInstruction;
    private OptionalInt connectedIndex;

    RemoveCommand(final List<Instruction> instructions, final int index) {
        super(instructions);
        this.index = index;
        this.removed = this.instructions.get(index);
        this.connectedInstruction = Optional.empty();
        this.connectedIndex = OptionalInt.empty();
    }

    @Override
    protected void onExecute() {
        this.instructions.remove(this.index);
        setConnectedInstruction();
        if (this.connectedIndex.isPresent()) {
            this.instructions.remove(this.connectedIndex.getAsInt());
        }
    }

    @Override
    protected void onUnexecute() {
        if (this.connectedIndex.isPresent()) {
            this.instructions.add(this.connectedIndex.getAsInt(), this.connectedInstruction.get());
        }
        this.instructions.add(this.index, this.removed);
    }

    private void setConnectedInstruction() {
        if (this.removed instanceof JumpInstruction) {
            this.connectedInstruction = Optional.of(((JumpInstruction) this.removed).getTarget());
            this.connectedIndex = OptionalInt.of(indexOf(this.connectedInstruction.get().getId()));
        } else if (this.removed instanceof JumpTargetInstruction) {
            this.connectedInstruction = Optional.of(getFromId(((JumpTargetInstruction) this.removed).getSourceId()));
            this.connectedIndex = OptionalInt.of(indexOf(this.connectedInstruction.get().getId()));
        }
    }

    private int indexOf(final UniqueId id) {
        return this.instructions.stream()
                                .map(Instruction::getId)
                                .collect(Collectors.toList())
                                .indexOf(id);
    }

    private Instruction getFromId(final UniqueId id) {
        return this.instructions.get(indexOf(id));
    }
}
