package todo.vm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCodeImpl;
import todo.vm.exceptions.VmRuntimeException;

public class MicroJumpTest extends BaseMicroInstructionTest {
    private final static int JUMP_IDX = 0;
    private final static int TARGET_IDX = 2;

    @Test(expected = IllegalStateException.class)
    public void testWithEmptyJumpCondition() {
        executeJump();
    }

    @Test
    public void testWithFalseJumpCondition() {
        this.state.setRegister(Register.JUMP_CONDITION, Value.number(0));
        executeJump();
        assertEquals(Value.number(JUMP_IDX), this.state.getRegister(Register.PROGRAM_COUNTER));
        assertEquals(Value.empty(), this.state.getRegister(Register.JUMP_CONDITION));
    }

    @Test
    public void testWithTrueJumpCondition() {
        this.state.setRegister(Register.JUMP_CONDITION, Value.number(1));
        executeJump();
        assertEquals(Value.number(TARGET_IDX), this.state.getRegister(Register.PROGRAM_COUNTER));
        assertEquals(Value.empty(), this.state.getRegister(Register.JUMP_CONDITION));
    }

    protected void executeJump() {
        final Instruction target = new DummyInstruction((s, c) -> Action.none());
        final MicroInstruction micro = new MicroJump(target);
        final Instruction jump = new DummyInstruction(micro);
        // Create a dummy code with a gap between the jump and the target
        final VmCodeImpl code = new VmCodeImpl();
        code.addInstruction(jump);
        code.addInstruction(new DummyInstruction((s, c) -> Action.none()));
        code.addInstruction(target);
        // Ensure the constants are actually correct
        assertEquals(code.getInstructionIndex(jump), JUMP_IDX);
        assertEquals(code.getInstructionIndex(target), TARGET_IDX);
        // And finally execute the instruction
        this.state.setRegister(Register.PROGRAM_COUNTER, Value.number(0));
        try {
            micro.execute(this.state, code);
        } catch (final VmRuntimeException e) {
            fail(e.toString());
        }
    }
}
