package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Value;

public class SubTest extends BaseMathTest {
    @Test
    public void testValid() {
        execMath(Value.number(1), Value.number(2), -1, Action::sub);
    }

    @Test
    public void testPrintable() {
        assertPrintable("sub 5", new Sub(5));
    }

    @Override
    protected Instruction getInstruction(final int memoryAddress) {
        return new Sub(memoryAddress);
    }
}
