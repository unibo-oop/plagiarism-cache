package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;

public class MicroInputTest extends BaseMicroInstructionTest {
    @Test
    public void testWithInput() {
        this.state.setInput(Arrays.asList(Value.number(0)));
        assertEquals(Action.pickInput(), execute(new MicroInput()));
        assertEquals(Value.number(0), this.state.getRegister(Register.MAIN_HAND));
        assertTrue(this.state.isRunning());
    }

    @Test
    public void testWithoutInput() {
        assertEquals(Action.none(), execute(new MicroInput()));
        assertEquals(Value.empty(), this.state.getRegister(Register.MAIN_HAND));
        assertFalse(this.state.isRunning());
    }
}
