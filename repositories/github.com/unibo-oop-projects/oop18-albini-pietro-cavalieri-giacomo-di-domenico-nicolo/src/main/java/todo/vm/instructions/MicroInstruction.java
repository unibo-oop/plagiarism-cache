package todo.vm.instructions;

import todo.vm.Action;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.VmRuntimeException;

/**
 * This interface represents a microinstruction. Internally the VM represents
 * each instruction with one or more microinstructions, each executing a small
 * part of the parent instruction. This is done to allow returning more than one
 * action for each instruction without showing inconsistent state to the VM
 * consumers.
 */
public interface MicroInstruction {
    /**
     * Execute this microinstruction.
     *
     * @param state the mutable state of the VM
     * @param code the VmCode
     * @return the executed action
     */
    Action execute(VmState state, VmCode code) throws VmRuntimeException;
}
