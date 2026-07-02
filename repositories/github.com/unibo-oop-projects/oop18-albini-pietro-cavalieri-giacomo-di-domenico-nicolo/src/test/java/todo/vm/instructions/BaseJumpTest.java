package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import todo.vm.Register;
import todo.vm.Value;
import todo.vm.exceptions.VmRuntimeException;

abstract class BaseJumpTest extends BaseInstructionTest {
    protected abstract BaseJump getInstruction();

    protected void testJump(final int hand, final boolean expected) {
        testJump(Value.number(hand), expected);
    }

    protected void testJump(final Value hand, final boolean expected) {
        this.state.setRegister(Register.MAIN_HAND, hand);
        jump();
        assertEquals(Value.number(expected ? 2 : 0), this.state.getRegister(Register.PROGRAM_COUNTER));
    }

    protected void jump() {
        try {
            fallibleJump();
        } catch (final VmRuntimeException e) {
            fail("jump failed: " + e.toString());
        }
    }

    protected void fallibleJump() throws VmRuntimeException {
        this.state.setRegister(Register.PROGRAM_COUNTER, Value.number(0));
        final BaseJump jump = getInstruction();
        fallibleExecute(jump, jump.getTarget());
    }
}
