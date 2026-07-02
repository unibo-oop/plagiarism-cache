package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Value;
import todo.vm.exceptions.VmRuntimeException;

public class MicroDecrTest extends BaseMicroUnaryTest {
    @Test
    public void testValidNumbers() throws VmRuntimeException {
        unary(Value.number(43), Value.number(42));
    }

    @Override
    protected MicroInstruction getInstance() {
        return new MicroDecr();
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.decr(memoryAddress);
    }
}
