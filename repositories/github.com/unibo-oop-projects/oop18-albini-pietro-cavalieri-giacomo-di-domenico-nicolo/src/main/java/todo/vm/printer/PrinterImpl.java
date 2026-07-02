package todo.vm.printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import todo.utils.UniqueId;

public class PrinterImpl implements Printer {
    private final List<PrintableInstruction> instructions;
    private final PrinterState state;

    public PrinterImpl() {
        this.instructions = new ArrayList<>();
        this.state = new PrinterStateImpl();
    }

    @Override
    public void add(final PrintableInstruction instr) {
        this.instructions.add(Objects.requireNonNull(instr));
    }

    @Override
    public String generate() {
        final StringBuilder builder = new StringBuilder();
        for (final PrintableInstruction instr : this.instructions) {
            builder.append(instr.generatePrintableString(this.state));
            builder.append('\n');
        }
        return builder.toString();
    }

    private final class PrinterStateImpl implements PrinterState {
        private final Map<UniqueId, String> jumpLabels;

        private PrinterStateImpl() {
            this.jumpLabels = new HashMap<>();
        }

        @Override
        public String getJumpLabel(final UniqueId id) {
            if (this.jumpLabels.containsKey(id)) {
                return this.jumpLabels.get(id);
            } else {
                final String label = "target" + (this.jumpLabels.size() + 1);
                this.jumpLabels.put(id, label);
                return label;
            }
        }
    }
}
