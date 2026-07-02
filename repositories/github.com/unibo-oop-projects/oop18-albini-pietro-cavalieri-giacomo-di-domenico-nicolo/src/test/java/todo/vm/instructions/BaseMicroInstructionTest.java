package todo.vm.instructions;

import static org.junit.Assert.fail;

import todo.vm.Action;
import todo.vm.DummyVmState;
import todo.vm.VmCodeImpl;
import todo.vm.exceptions.VmRuntimeException;

class BaseMicroInstructionTest {
    protected DummyVmState state;

    public BaseMicroInstructionTest() {
        this.state = new DummyVmState();
    }

    protected Action fallibleExecute(final MicroInstruction micro) throws VmRuntimeException {
        final VmCodeImpl code = new VmCodeImpl();
        code.addInstruction(new DummyInstruction(micro));
        return micro.execute(this.state, code);
    }

    protected Action execute(final MicroInstruction micro) {
        try {
            return fallibleExecute(micro);
        } catch (final VmRuntimeException e) {
            fail(e.toString());
            return Action.none();
        }
    }
}
