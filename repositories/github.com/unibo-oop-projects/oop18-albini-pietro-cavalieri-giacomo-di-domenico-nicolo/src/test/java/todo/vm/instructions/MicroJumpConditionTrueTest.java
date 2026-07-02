package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Value;

public class MicroJumpConditionTrueTest extends BaseMicroJumpConditionTest {
    @Test
    public void test() {
        testCondition(42, true);
        testCondition(-42, true);
        testCondition(0, true);
        testCondition(Value.empty(), true);
    }

    @Override
    protected MicroInstruction getMicroInstruction() {
        return new MicroJumpConditionTrue();
    }
}
