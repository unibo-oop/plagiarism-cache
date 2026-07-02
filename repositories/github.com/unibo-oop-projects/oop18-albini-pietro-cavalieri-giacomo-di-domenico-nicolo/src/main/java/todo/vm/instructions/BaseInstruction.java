package todo.vm.instructions;

import todo.utils.Identifiable;
import todo.utils.UniqueId;
import todo.utils.UniqueIdGenerator;
import todo.vm.printer.PrintableInstruction;

public abstract class BaseInstruction implements Identifiable, PrintableInstruction {
    private final UniqueId id;

    public BaseInstruction() {
        this.id = UniqueIdGenerator.getInstance().next();
    }

    @Override
    public UniqueId getId() {
        return this.id;
    }

    // This is abstract to force subclasses to override it
    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
