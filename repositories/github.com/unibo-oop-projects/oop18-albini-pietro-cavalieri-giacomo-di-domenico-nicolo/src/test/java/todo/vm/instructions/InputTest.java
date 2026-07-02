package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;

public class InputTest extends BaseInstructionTest {
    @Test
    public void testEquals() {
        assertEquals(new Input(), new Input());
        assertNotEquals(new Input().getId(), new Input().getId());
    }

    @Test
    public void testWithInput() {
        this.state.setInput(Arrays.asList(Value.number(10)));
        execute(new Input());
        assertEquals(Arrays.asList(Action.pickInput()), this.executedActions);
        assertEquals(Value.number(10), this.state.getRegister(Register.MAIN_HAND));
        assertTrue(this.state.isRunning());
    }

    @Test
    public void testWithInputAndBusyHand() {
        this.state.setInput(Arrays.asList(Value.number(10)));
        this.state.setRegister(Register.MAIN_HAND, Value.number(20));
        execute(new Input());
        assertEquals(Arrays.asList(Action.dropMainHand(), Action.pickInput()), this.executedActions);
        assertEquals(Value.number(10), this.state.getRegister(Register.MAIN_HAND));
        assertTrue(this.state.isRunning());
    }

    @Test
    public void testWithoutInput() {
        execute(new Input());
        assertTrue(this.executedActions.isEmpty());
        assertEquals(Value.empty(), this.state.getRegister(Register.MAIN_HAND));
        assertFalse(this.state.isRunning());
    }

    @Test
    public void testWithoutInputAndBusyHand() {
        this.state.setRegister(Register.MAIN_HAND, Value.number(20));
        execute(new Input());
        assertEquals(Arrays.asList(Action.dropMainHand()), this.executedActions);
        assertEquals(Value.empty(), this.state.getRegister(Register.MAIN_HAND));
        assertFalse(this.state.isRunning());
    }

    @Test
    public void testPrintable() {
        assertPrintable("input", new Input());
    }
}
