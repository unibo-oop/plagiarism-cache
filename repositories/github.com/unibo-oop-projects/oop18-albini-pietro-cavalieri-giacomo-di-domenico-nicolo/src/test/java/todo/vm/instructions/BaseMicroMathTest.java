package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

public abstract class BaseMicroMathTest extends BaseMicroInstructionTest {
    @Test(expected = BrokenCodegenException.class)
    public void testMissingResolvedAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.number(0)));
        this.state.setRegister(Register.MAIN_HAND, Value.number(0));
        fallibleExecute(getInstance());
    }

    @Test(expected = EmptyHandException.class)
    public void testEmptyHand() throws VmRuntimeException {
        fallibleCalc(Value.empty(), Value.number(0), 0);
    }

    @Test(expected = EmptyMemoryAddressException.class)
    public void testEmptyMemoryAddress() throws VmRuntimeException {
        fallibleCalc(Value.number(0), Value.empty(), 0);
    }

    protected void calc(final int number1, final int number2, final int expected) {
        try {
            fallibleCalc(Value.number(number1), Value.number(number2), expected);
        } catch (final VmRuntimeException e) {
            fail("calc failed: " + e.toString());
        }
    }

    protected void fallibleCalc(final Value number1, final Value number2, final int expected)
            throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(number2));
        this.state.setRegister(Register.MAIN_HAND, number1);
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        assertEquals(getAction(0), fallibleExecute(getInstance()));
        assertEquals(Value.number(expected), this.state.getRegister(Register.MAIN_HAND));
        assertEquals(number2, this.state.getMemoryAddress(0));
    }

    protected abstract MicroInstruction getInstance();

    protected abstract Action getAction(int memoryAddress);
}
