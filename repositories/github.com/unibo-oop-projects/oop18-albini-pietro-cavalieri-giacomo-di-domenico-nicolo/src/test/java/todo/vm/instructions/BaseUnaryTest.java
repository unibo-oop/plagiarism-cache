package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.UnaryOpOnCharException;
import todo.vm.exceptions.VmRuntimeException;

public abstract class BaseUnaryTest extends BaseInstructionTest {
    @Test(expected = EmptyMemoryAddressException.class)
    public void testEmptyMemoryAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        fallibleExecute(getInstance(0));
    }

    @Test(expected = UnaryOpOnCharException.class)
    public void testAsciiDisallowed() throws VmRuntimeException {
        unary(Value.ascii('a'), Value.empty());
    }

    protected void unary(final Value from, final Value to) throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(from));
        fallibleExecute(getInstance(0));
        assertEquals(Arrays.asList(Action.locateMemoryAddress(0), getAction(0), Action.copyFrom(0)),
                this.executedActions);
        assertEquals(to, this.state.getRegister(Register.MAIN_HAND));
        assertEquals(to, this.state.getMemoryAddress(0));
    }

    protected abstract Instruction getInstance(int memoryAddress);

    protected abstract Action getAction(int memoryAddress);
}
