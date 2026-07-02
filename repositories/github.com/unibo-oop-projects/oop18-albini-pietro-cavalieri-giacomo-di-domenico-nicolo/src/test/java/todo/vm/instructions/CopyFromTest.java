package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

public class CopyFromTest extends BaseInstructionTest {
    @Test
    public void testWithFullMemoryAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.number(42)));
        execute(new CopyFrom(0));
        assertEquals(Arrays.asList(Action.locateMemoryAddress(0), Action.copyFrom(0)), this.executedActions);
        assertEquals(Value.number(42), this.state.getRegister(Register.MAIN_HAND));
        assertEquals(Value.number(42), this.state.getMemoryAddress(0));
    }

    @Test
    public void testPrintable() {
        assertPrintable("copyfrom 5", new CopyFrom(5));
    }

    @Test(expected = EmptyMemoryAddressException.class)
    public void testWithEmptyMemoryAddress() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        fallibleExecute(new CopyFrom(0));
    }
}
