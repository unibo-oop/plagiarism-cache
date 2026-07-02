package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

public class MicroCopyFromTest extends BaseMicroInstructionTest {
    @Test
    public void testWithFullMemoryAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.number(42)));
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        assertEquals(Action.copyFrom(0), execute(new MicroCopyFrom()));
        assertEquals(Value.number(42), this.state.getRegister(Register.MAIN_HAND));
        assertEquals(Value.number(42), this.state.getMemoryAddress(0));
    }

    @Test(expected = EmptyMemoryAddressException.class)
    public void testWithEmptyMemoryAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        fallibleExecute(new MicroCopyFrom());
    }

    @Test(expected = BrokenCodegenException.class)
    public void testWithoutResolvedAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        fallibleExecute(new MicroCopyFrom());
    }
}
