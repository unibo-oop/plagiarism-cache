package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;

public class MicroAddTest extends BaseMicroMathTest {
    @Test
    public void testValid() {
        calc(42, 10, 52);
    }

    @Override
    protected MicroInstruction getInstance() {
        return new MicroAdd();
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.add(memoryAddress);
    }
}
