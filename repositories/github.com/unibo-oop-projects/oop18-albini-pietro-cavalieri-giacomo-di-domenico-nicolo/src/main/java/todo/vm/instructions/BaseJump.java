package todo.vm.instructions;

import java.util.Objects;

import todo.utils.UniqueId;
import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrintableInstruction;
import todo.vm.printer.PrinterState;

public abstract class BaseJump extends BaseInstruction implements JumpInstruction {
    private final PrintableInstruction target;

    public BaseJump() {
        this.target = new JumpTarget(getId());
    }

    @Override
    public PrintableInstruction getTarget() {
        return this.target;
    }

    private static final class JumpTarget extends BaseInstruction implements JumpTargetInstruction {
        private final UniqueId sourceId;

        private JumpTarget(final UniqueId sourceId) {
            this.sourceId = Objects.requireNonNull(sourceId);
        }

        @Override
        public UniqueId getSourceId() {
            return this.sourceId;
        }

        @Override
        public void buildMicrocode(final MicrocodeBuilder builder) {
            // No microcode is generated for a jump target.
        }

        @Override
        public boolean isDummy() {
            return true;
        }

        @Override
        public boolean equals(final Object other) {
            return other instanceof JumpTarget;
        }

        @Override
        public String generatePrintableString(final PrinterState state) {
            return state.getJumpLabel(getId()) + ":";
        }
    }
}
