package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Value;
import todo.vm.exceptions.VmRuntimeException;

public class IncrTest extends BaseUnaryTest {
    @Test
    public void testValidNumber() throws VmRuntimeException {
        unary(Value.number(41), Value.number(42));
    }

    @Test
    public void testPrintable() {
        assertPrintable("incr 5", new Incr(5));
    }

    @Override
    protected Instruction getInstance(final int memoryAddress) {
        return new Incr(memoryAddress);
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.incr(memoryAddress);
    }
}
