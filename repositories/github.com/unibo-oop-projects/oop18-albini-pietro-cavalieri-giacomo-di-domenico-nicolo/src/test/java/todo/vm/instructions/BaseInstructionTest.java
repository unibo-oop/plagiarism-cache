package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import todo.vm.Action;
import todo.vm.DummyVmState;
import todo.vm.VmCodeImpl;
import todo.vm.exceptions.VmRuntimeException;
import todo.vm.printer.DummyPrinterState;
import todo.vm.printer.PrintableInstruction;

class BaseInstructionTest {
    protected final List<Action> executedActions;
    protected DummyVmState state;

    protected BaseInstructionTest() {
        this.executedActions = new ArrayList<>();
        this.state = new DummyVmState();
    }

    protected void fallibleExecute(final Instruction... instructions) throws VmRuntimeException {
        final List<MicroInstruction> microcode = new ArrayList<>();
        final VmCodeImpl code = new VmCodeImpl();
        for (final Instruction instr : instructions) {
            instr.buildMicrocode(microcode::add);
            code.addInstruction(instr);
        }
        for (final MicroInstruction micro : microcode) {
            final Action result = micro.execute(this.state, code);
            if (!result.equals(Action.none())) {
                this.executedActions.add(result);
            }
        }
    }

    protected void execute(final Instruction... instructions) {
        try {
            fallibleExecute(instructions);
        } catch (final VmRuntimeException e) {
            fail(e.toString());
        }
    }

    protected void assertPrintable(final String expected, final PrintableInstruction instr) {
        assertEquals(expected, instr.generatePrintableString(new DummyPrinterState()));
    }
}
