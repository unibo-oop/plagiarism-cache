package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

public class MicroCopyToTest extends BaseMicroInstructionTest {
    @Test
    public void testWithFullHand() throws VmRuntimeException {
        this.state.setRegister(Register.MAIN_HAND, Value.number(10));
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        assertEquals(Action.copyTo(0), execute(new MicroCopyTo()));
        assertEquals(Value.number(10), this.state.getMemoryAddress(0));
        assertEquals(Value.number(10), this.state.getRegister(Register.MAIN_HAND));
    }

    @Test(expected = EmptyHandException.class)
    public void testWithEmptyHand() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        this.state.setRegister(Register.RESOLVED_ADDRESS, Value.number(0));
        fallibleExecute(new MicroCopyTo());
    }

    @Test(expected = BrokenCodegenException.class)
    public void testWithoutResolvedAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        fallibleExecute(new MicroCopyTo());
    }
}
