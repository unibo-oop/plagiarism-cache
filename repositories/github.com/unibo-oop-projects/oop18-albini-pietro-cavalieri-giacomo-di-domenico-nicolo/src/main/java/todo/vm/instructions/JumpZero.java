package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The jumpzero instruction jumps to the target when the value in the hand
 * equals zero.
 */
public class JumpZero extends BaseJump {
    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroJumpConditionZero());
        builder.add(new MicroJump(getTarget()));
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof JumpZero;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "jumpzero " + state.getJumpLabel(getTarget().getId());
    }
}
