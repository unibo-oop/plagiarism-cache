package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

public class JumpNegativeTest extends BaseJumpTest {
    @Test
    public void test() {
        testJump(42, false);
        testJump(0, false);
        testJump(-42, true);
    }

    @Test(expected = EmptyHandException.class)
    public void testWithEmptyHand() throws VmRuntimeException {
        this.state.setRegister(Register.MAIN_HAND, Value.empty());
        fallibleJump();
    }

    @Test
    public void testPrintable() {
        final JumpNegative jmp = new JumpNegative();
        assertPrintable("label0:", jmp.getTarget());
        assertPrintable("jumpneg label0", jmp);
    }

    @Override
    protected BaseJump getInstruction() {
        return new JumpNegative();
    }
}
