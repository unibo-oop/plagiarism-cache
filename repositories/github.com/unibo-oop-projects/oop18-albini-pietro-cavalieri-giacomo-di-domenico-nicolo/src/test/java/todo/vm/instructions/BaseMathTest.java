package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.function.Function;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.VmRuntimeException;

abstract class BaseMathTest extends BaseInstructionTest {
    protected void execMath(final Value number1, final Value number2, final int expected,
            final Function<Integer, Action> actionBuilder) {
        try {
            fallibleExecMath(number1, number2, expected, actionBuilder);
        } catch (final VmRuntimeException e) {
            fail("calc failed: " + e.toString());
        }
    }

    protected void fallibleExecMath(final Value value1, final Value value2, final int expected,
            final Function<Integer, Action> actionBuilder) throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(value2));
        this.state.setRegister(Register.MAIN_HAND, value1);
        execute(getInstruction(0));
        assertEquals(Arrays.asList(Action.locateMemoryAddress(0), actionBuilder.apply(0)), this.executedActions);
        assertEquals(Value.number(expected), this.state.getRegister(Register.MAIN_HAND));
        assertEquals(value2, this.state.getMemoryAddress(0));
    }

    protected abstract Instruction getInstruction(int memoryAddress);
}
