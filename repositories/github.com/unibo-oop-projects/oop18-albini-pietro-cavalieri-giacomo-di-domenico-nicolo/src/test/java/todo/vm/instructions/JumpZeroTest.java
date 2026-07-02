package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

public class JumpZeroTest extends BaseJumpTest {
    @Test
    public void testEquals() {
        assertEquals(new JumpZero(), new JumpZero());
        assertNotEquals(new JumpZero().getId(), new JumpZero().getId());
    }

    @Test
    public void test() {
        testJump(42, false);
        testJump(0, true);
        testJump(-42, false);
    }

    @Test(expected = EmptyHandException.class)
    public void testJumpWithEmptyHand() throws VmRuntimeException {
        this.state.setRegister(Register.MAIN_HAND, Value.empty());
        fallibleJump();
    }

    @Test
    public void testPrintable() {
        final JumpZero jmp = new JumpZero();
        assertPrintable("label0:", jmp.getTarget());
        assertPrintable("jumpzero label0", jmp);
    }

    @Override
    protected BaseJump getInstruction() {
        return new JumpZero();
    }
}
