package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;

public class MicroSubTest extends BaseMicroMathTest {
    @Test
    public void testValid() {
        calc(42, 10, 32);
    }

    @Override
    protected MicroInstruction getInstance() {
        return new MicroSub();
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.sub(memoryAddress);
    }
}
