package todo.vm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import todo.utils.Checks;
import todo.utils.UniqueId;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.MicroInstruction;

/**
 * This class represents the code executed by the VM.
 */
public class VmCodeImpl implements VmCode {
    private final List<MicroInstruction> microcode;
    private final Map<UniqueId, Integer> instrToIdx;
    private final Map<Integer, UniqueId> idxToInstr;

    public VmCodeImpl() {
        this.microcode = new ArrayList<>();
        this.instrToIdx = new HashMap<>();
        this.idxToInstr = new HashMap<>();
    }

    /**
     * Add an instruction to the code. This converts the instruction into microcode
     * and stores it.
     *
     * @param instr the instruction to add
     * @throws IllegalArgumentException if the instruction was added previously
     */
    public void addInstruction(final Instruction instr) {
        Objects.requireNonNull(instr);
        Checks.require(!this.instrToIdx.containsKey(instr.getId()), IllegalArgumentException.class,
                "duplicate instruction: " + instr.getId());
        this.instrToIdx.put(instr.getId(), size());
        instr.buildMicrocode(micro -> {
            this.idxToInstr.put(size(), instr.getId());
            this.microcode.add(micro);
        });
    }

    @Override
    public int size() {
        return this.microcode.size();
    }

    @Override
    public MicroInstruction get(final int idx) {
        Checks.require(idx >= 0 && idx < this.microcode.size(), IllegalArgumentException.class,
                "invalid instruction index: " + idx);
        return this.microcode.get(idx);
    }

    @Override
    public int getInstructionIndex(final Instruction instr) {
        final UniqueId id = Objects.requireNonNull(instr).getId();
        Checks.require(this.instrToIdx.containsKey(id), IllegalArgumentException.class,
                "instruction not part of the code: " + id);
        return this.instrToIdx.get(id);
    }

    @Override
    public UniqueId getInstructionId(final int idx) {
        Checks.require(this.idxToInstr.containsKey(idx), IllegalArgumentException.class,
                "Index is not part of the code: " + idx);
        return this.idxToInstr.get(idx);
    }
}
