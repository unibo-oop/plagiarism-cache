package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyValueInOutputException;
import todo.vm.exceptions.VmRuntimeException;

public class OutputTest extends BaseInstructionTest {
    @Test
    public void testEquals() {
        assertEquals(new Output(), new Output());
        assertNotEquals(new Output().getId(), new Output().getId());
    }

    @Test
    public void testWithHandContent() {
        this.state.setRegister(Register.MAIN_HAND, Value.number(10));
        execute(new Output());
        assertEquals(Arrays.asList(Action.dropOutput()), this.executedActions);
        assertEquals(Arrays.asList(Value.number(10)), this.state.getOutput());
        assertEquals(Value.empty(), this.state.getRegister(Register.MAIN_HAND));
    }

    @Test(expected = EmptyValueInOutputException.class)
    public void testWithoutHandContent() throws VmRuntimeException {
        fallibleExecute(new Output());
    }

    @Test
    public void testPrintable() {
        assertPrintable("output", new Output());
    }
}
