package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Value;
import todo.vm.exceptions.VmRuntimeException;

public class MicroIncrTest extends BaseMicroUnaryTest {
    @Test
    public void testValidContent() throws VmRuntimeException {
        unary(Value.number(41), Value.number(42));
    }

    @Override
    protected MicroInstruction getInstance() {
        return new MicroIncr();
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.incr(memoryAddress);
    }
}
