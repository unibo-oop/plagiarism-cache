package todo.model.program;

import java.util.List;

import todo.vm.instructions.Instruction;

class MoveCommand extends AbstractCommand {
    private final int from;
    private final int to;

    MoveCommand(final List<Instruction> instructions, final int from, final int to) {
        super(instructions);
        this.from = from;
        this.to = to;
    }

    @Override
    protected void onExecute() {
        move(this.from, this.to);
    }

    @Override
    protected void onUnexecute() {
        move(this.to, this.from);
    }

    private void move(final int from, final int to) {
        if (from != to) {
            this.instructions.add(to, this.instructions.remove(from));
        }
    }

}
