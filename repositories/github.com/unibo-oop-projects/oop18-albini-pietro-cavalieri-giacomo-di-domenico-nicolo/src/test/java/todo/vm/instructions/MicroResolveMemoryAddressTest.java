package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.InvalidMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

public class MicroResolveMemoryAddressTest extends BaseMicroInstructionTest {
    @Test
    public void testWithValidAddress() {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty(), Value.empty()));
        assertEquals(Action.locateMemoryAddress(1), execute(new MicroResolveMemoryAddress(1)));
        assertEquals(Value.number(1), this.state.getRegister(Register.RESOLVED_ADDRESS));
    }

    @Test(expected = InvalidMemoryAddressException.class)
    public void testWithInvalidMemoryAddress() throws VmRuntimeException {
        fallibleExecute(new MicroResolveMemoryAddress(1));
    }
}
