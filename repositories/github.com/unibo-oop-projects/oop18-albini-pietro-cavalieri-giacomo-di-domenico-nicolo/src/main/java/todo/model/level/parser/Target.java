package todo.model.level.parser;

import java.util.Objects;

import todo.vm.MicrocodeBuilder;
import todo.vm.instructions.BaseInstruction;
import todo.vm.instructions.Jump;
import todo.vm.printer.PrinterState;

/**
 * This class is exclusively used by the parser to manage the {@link Jump}
 * labels.
 */
class Target extends BaseInstruction {
    private String label;

    public void setLabel(final String label) {
        this.label = Objects.requireNonNull(label);
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        // No microcode generated for DummyTarget
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Target;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        throw new UnsupportedOperationException("dummy instructions can't be printed");
    }
}
