package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The input instruction takes a value from the input queue, and if that's empty
 * it ends the program. Any pre-existing value in the main hand will be
 * discarded.
 */
public class Input extends BaseInstruction {
    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroDropMainHand());
        builder.add(new MicroInput());
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Input;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "input";
    }
}
