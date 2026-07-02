package todo.vm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import todo.vm.instructions.DummyInstruction;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.MicroInstruction;

public class CodeTest {
    @Test
    public void testAddInstruction() {
        final MicroInstruction dummy1 = (state, code) -> Action.none();
        final MicroInstruction dummy2 = (state, code) -> Action.none();
        final VmCodeImpl code = new VmCodeImpl();
        final Instruction dummy = new DummyInstruction(dummy1, dummy2);
        code.addInstruction(dummy);
        assertEquals(2, code.size());
        assertEquals(dummy1, code.get(0));
        assertEquals(dummy2, code.get(1));
        assertEquals(dummy.getId(), code.getInstructionId(0));
        assertEquals(dummy.getId(), code.getInstructionId(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOutOfBounds() {
        final VmCodeImpl code = new VmCodeImpl();
        assertEquals(0, code.size());
        code.get(0);
    }
}
