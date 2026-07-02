package todo.vm.printer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import todo.vm.instructions.Input;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;

public class PrinterTest {
    @Test
    public void testGenerateSimpleIO() {
        final Printer printer = new PrinterImpl();
        final Jump jmp = new Jump();
        printer.add(jmp.getTarget());
        printer.add(new Input());
        printer.add(new Output());
        printer.add(jmp);
        assertEquals("target1:\ninput\noutput\njump target1\n", printer.generate());
    }

    @Test
    public void testGenerateMultipleJumps() {
        final Printer printer = new PrinterImpl();
        final Jump jmp = new Jump();
        final JumpZero jz = new JumpZero();
        printer.add(jmp.getTarget());
        printer.add(jz.getTarget());
        printer.add(new Input());
        printer.add(jz);
        printer.add(new Output());
        printer.add(jmp);
        assertEquals("target1:\ntarget2:\ninput\njumpzero target2\noutput\njump target1\n", printer.generate());
    }
}
