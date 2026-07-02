package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import todo.vm.Value;

public class JumpTest extends BaseJumpTest {
    @Test
    public void testEquals() {
        assertEquals(new Jump(), new Jump());
        assertNotEquals(new Jump().getId(), new Jump().getId());
    }

    @Test
    public void test() {
        testJump(42, true);
        testJump(0, true);
        testJump(-42, true);
        testJump(Value.empty(), true);
    }

    @Test
    public void testPrintable() {
        final Jump jmp = new Jump();
        assertPrintable("label0:", jmp.getTarget());
        assertPrintable("jump label0", jmp);
    }

    @Override
    protected BaseJump getInstruction() {
        return new Jump();
    }
}
