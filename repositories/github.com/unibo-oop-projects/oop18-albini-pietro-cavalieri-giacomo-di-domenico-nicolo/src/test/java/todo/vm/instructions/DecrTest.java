package todo.vm.instructions;

import org.junit.Test;

import todo.vm.Action;
import todo.vm.Value;
import todo.vm.exceptions.VmRuntimeException;

public class DecrTest extends BaseUnaryTest {
    @Test
    public void testValidNumbers() throws VmRuntimeException {
        unary(Value.number(43), Value.number(42));
    }

    @Test
    public void testPrintable() {
        assertPrintable("decr 5", new Decr(5));
    }

    @Override
    protected Instruction getInstance(final int memoryAddress) {
        return new Decr(memoryAddress);
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.decr(memoryAddress);
    }
}
