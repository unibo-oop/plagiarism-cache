package todo.vm;

import todo.utils.UniqueId;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.MicroInstruction;

public interface VmCode {
    /**
     * @return the number of microinstructions in the code
     */
    int size();

    /**
     * @param idx the index to fetch
     * @throws IllegalArgumentException if idx is not valid
     * @return the microinstruction at the provided index
     */
    MicroInstruction get(int idx);

    /**
     * Get the index of the first microinstruction which is part of the provided
     * instruction.
     *
     * @param instr the instruction you want to look for
     * @throws IllegalArgumentException if the instruction is not in the code
     * @return the index of the related microinstruction
     */
    int getInstructionIndex(Instruction instr);

    /**
     * Get the {@link UniqueID} of the instruction that generated the
     * microinstruction at the provided index.
     *
     * @param idx the index of the instruction whose id has to be returned
     * @return the {@link UniqueId} of the related instruction
     */
    UniqueId getInstructionId(int idx);
}
