package todo.vm.printer;

import todo.vm.instructions.Instruction;

/**
 * This interface represents an instruction that can be printed with a
 * {@link Printer}.
 */
public interface PrintableInstruction extends Instruction {
    /**
     * Generate the printable string that will be returned by the {@link Printer}.
     * This method is called when {@link Printer.generate} is called.
     *
     * @param state the internal printer state
     * @return the printable representation of this instruction
     */
    String generatePrintableString(PrinterState state);
}
