package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Value;

public class AddTest extends BaseMathTest {
    @Test
    public void testValid() {
        execMath(Value.number(1), Value.number(2), 3, Action::add);
    }

    @Test
    public void testPrintable() {
        assertPrintable("add 5", new Add(5));
    }

    @Override
    protected Instruction getInstruction(final int memoryAddress) {
        return new Add(memoryAddress);
    }
}
