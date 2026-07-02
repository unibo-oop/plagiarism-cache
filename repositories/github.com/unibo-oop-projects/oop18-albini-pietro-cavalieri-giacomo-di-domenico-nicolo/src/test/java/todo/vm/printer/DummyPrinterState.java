package todo.vm.printer;

import java.util.HashMap;
import java.util.Map;

import todo.utils.UniqueId;

public class DummyPrinterState implements PrinterState {
    private final Map<UniqueId, String> labels;

    public DummyPrinterState() {
        this.labels = new HashMap<>();
    }

    @Override
    public String getJumpLabel(final UniqueId id) {
        if (this.labels.containsKey(id)) {
            return this.labels.get(id);
        } else {
            final String label = "label" + this.labels.size();
            this.labels.put(id, label);
            return label;
        }
    }

}
