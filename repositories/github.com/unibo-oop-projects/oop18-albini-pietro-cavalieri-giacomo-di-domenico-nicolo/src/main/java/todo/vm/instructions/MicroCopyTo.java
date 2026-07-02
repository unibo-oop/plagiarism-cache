package todo.vm.instructions;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

class MicroCopyTo implements MicroInstruction {
    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final int memoryAddress = state.getRegister(Register.RESOLVED_ADDRESS)
                                       .orThrow(BrokenCodegenException.class,
                                               "No resolved address before calling copyfrom")
                                       .asNumber();
        final Value hand = state.getRegister(Register.MAIN_HAND).orThrow(EmptyHandException.class);
        state.setMemoryAddress(memoryAddress, hand);
        return Action.copyTo(memoryAddress);
    }
}
