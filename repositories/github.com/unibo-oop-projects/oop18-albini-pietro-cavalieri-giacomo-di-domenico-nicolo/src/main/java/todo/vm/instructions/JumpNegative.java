package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The jumpnegative instruction jumps to the target when the value in the hand
 * is negative.
 */
public class JumpNegative extends BaseJump {
    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroJumpConditionNegative());
        builder.add(new MicroJump(getTarget()));
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof JumpNegative;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "jumpneg " + state.getJumpLabel(getTarget().getId());
    }
}
