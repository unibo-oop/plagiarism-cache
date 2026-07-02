package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The jump instruction always jumps to the target.
 */
public class Jump extends BaseJump {
    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroJumpConditionTrue());
        builder.add(new MicroJump(getTarget()));
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Jump;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "jump " + state.getJumpLabel(getTarget().getId());
    }
}
