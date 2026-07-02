package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyValueInOutputException;
import todo.vm.exceptions.VmRuntimeException;

public class MicroOutputTest extends BaseMicroInstructionTest {
    @Test
    public void testWithHandContent() {
        this.state.setRegister(Register.MAIN_HAND, Value.number(10));
        assertEquals(Action.dropOutput(), execute(new MicroOutput()));
        assertEquals(Arrays.asList(Value.number(10)), this.state.getOutput());
    }

    @Test(expected = EmptyValueInOutputException.class)
    public void testWithoutHandContent() throws VmRuntimeException {
        fallibleExecute(new MicroOutput());
    }
}
