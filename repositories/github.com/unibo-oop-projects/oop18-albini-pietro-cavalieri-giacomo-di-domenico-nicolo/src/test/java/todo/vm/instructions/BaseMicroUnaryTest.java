package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.UnaryOpOnCharException;
import todo.vm.exceptions.VmRuntimeException;

public abstract class BaseMicroUnaryTest extends BaseMicroInstructionTest {
    @Test(expected = BrokenCodegenException.class)
    public void testMissingResolvedAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.number(0)));
        fallibleExecute(getInstance());
    }

    @Test(expected = EmptyMemoryAddressException.class)
    public void testEmptyMemoryAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        fallibleExecute(getInstance());
    }

    @Test(expected = UnaryOpOnCharException.class)
    public void testAsciiDisallowed() throws VmRuntimeException {
        unary(Value.ascii('a'), Value.empty());
    }

    protected void unary(final Value from, final Value to) throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(from));
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        assertEquals(getAction(0), fallibleExecute(getInstance()));
        assertEquals(to, this.state.getMemoryAddress(0));
    }

    protected abstract MicroInstruction getInstance();

    protected abstract Action getAction(int memoryAddress);
}
