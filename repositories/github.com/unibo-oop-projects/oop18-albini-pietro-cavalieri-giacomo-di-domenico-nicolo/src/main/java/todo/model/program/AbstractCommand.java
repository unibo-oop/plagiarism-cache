package todo.model.program;

import java.util.List;
import java.util.Objects;

import todo.utils.Checks;
import todo.vm.instructions.Instruction;

abstract class AbstractCommand implements ProgramCommand {
    protected final List<Instruction> instructions;
    private boolean executed;

    /**
     * @param instructions the {@link List} of {@link Instruction}s upon which the
     *            {@link ProgramCommand} will be executed changing it
     */
    AbstractCommand(final List<Instruction> instructions) {
        this.instructions = Objects.requireNonNull(instructions);
        this.executed = false;
    }

    @Override
    public final void execute() {
        Checks.require(!this.executed, IllegalStateException.class, "Can not execute the same Command twice");
        this.executed = true;
        onExecute();
    }

    protected abstract void onExecute();

    @Override
    public final void unexecute() {
        Checks.require(this.executed, IllegalStateException.class, "Can not unexecute a Command that wasn't executed");
        this.executed = false;
        onUnexecute();
    }

    protected abstract void onUnexecute();
}
