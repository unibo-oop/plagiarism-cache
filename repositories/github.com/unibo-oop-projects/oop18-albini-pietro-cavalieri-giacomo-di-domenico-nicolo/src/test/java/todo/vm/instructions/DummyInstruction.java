package todo.vm.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import todo.utils.UniqueId;
import todo.utils.UniqueIdGenerator;
import todo.vm.MicrocodeBuilder;

public class DummyInstruction implements Instruction {
    private final List<MicroInstruction> microcode;
    private final UniqueId id;

    public DummyInstruction(final MicroInstruction... microcode) {
        this.microcode = new ArrayList<>(Arrays.asList(microcode));
        this.id = UniqueIdGenerator.getInstance().next();
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        for (final MicroInstruction micro : this.microcode) {
            builder.add(micro);
        }
    }

    @Override
    public UniqueId getId() {
        return this.id;
    }
}
