package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The output instruction puts the value held in the hand in the output queue,
 * and clears the hand. When the hand is empty an exception will be raised.
 */
public class Output extends BaseInstruction {
    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroOutput());
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Output;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "output";
    }
}
