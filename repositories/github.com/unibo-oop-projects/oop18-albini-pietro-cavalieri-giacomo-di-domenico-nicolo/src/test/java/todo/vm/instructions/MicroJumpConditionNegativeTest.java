package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

public class MicroJumpConditionNegativeTest extends BaseMicroJumpConditionTest {
    @Test
    public void test() {
        testCondition(42, false);
        testCondition(0, false);
        testCondition(-42, true);
    }

    @Test(expected = EmptyHandException.class)
    public void testWithEmptyHand() throws VmRuntimeException {
        this.state.setRegister(Register.MAIN_HAND, Value.empty());
        fallibleExecute(getMicroInstruction());
    }

    @Override
    protected MicroInstruction getMicroInstruction() {
        return new MicroJumpConditionNegative();
    }
}
