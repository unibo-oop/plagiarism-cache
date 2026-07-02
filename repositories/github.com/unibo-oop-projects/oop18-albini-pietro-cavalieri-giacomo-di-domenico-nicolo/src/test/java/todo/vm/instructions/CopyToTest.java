package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

public class CopyToTest extends BaseInstructionTest {
    @Test
    public void testWithHandContent() throws VmRuntimeException {
        this.state.setRegister(Register.MAIN_HAND, Value.number(10));
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        execute(new CopyTo(0));
        assertEquals(Arrays.asList(Action.locateMemoryAddress(0), Action.copyTo(0)), this.executedActions);
        assertEquals(Value.number(10), this.state.getMemoryAddress(0));
        assertEquals(Value.number(10), this.state.getRegister(Register.MAIN_HAND));
    }

    @Test
    public void testPrintable() {
        assertPrintable("copyto 5", new CopyTo(5));
    }

    @Test(expected = EmptyHandException.class)
    public void testWithoutHandContent() throws VmRuntimeException {
        this.state.setMemoryAddresses(Arrays.asList(Value.empty()));
        fallibleExecute(new CopyTo(0));
    }
}
