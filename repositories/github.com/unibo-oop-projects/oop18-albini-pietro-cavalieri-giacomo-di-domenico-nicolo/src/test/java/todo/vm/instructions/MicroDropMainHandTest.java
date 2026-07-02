package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;

public class MicroDropMainHandTest extends BaseMicroInstructionTest {
    @Test
    public void testWithMainHandContent() {
        this.state.setRegister(Register.MAIN_HAND, Value.number(10));
        assertEquals(Action.dropMainHand(), execute(new MicroDropMainHand()));
        assertEquals(Value.empty(), this.state.getRegister(Register.MAIN_HAND));
    }

    @Test
    public void testWithoutMainHandContent() {
        assertEquals(Action.none(), execute(new MicroDropMainHand()));
    }
}
