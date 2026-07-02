package todo.vm.instructions;

import static org.junit.Assert.assertEquals;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;

abstract class BaseMicroJumpConditionTest extends BaseMicroInstructionTest {
    protected abstract MicroInstruction getMicroInstruction();

    protected void testCondition(final int hand, final boolean expected) {
        testCondition(Value.number(hand), expected);
    }

    protected void testCondition(final Value hand, final boolean expected) {
        this.state.setRegister(Register.MAIN_HAND, hand);
        assertEquals(Action.none(), execute(getMicroInstruction()));
        assertEquals(Value.number(expected ? 1 : 0), this.state.getRegister(Register.JUMP_CONDITION));
    }
}
