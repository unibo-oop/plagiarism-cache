package todo.vm.instructions;

import todo.utils.Checks;
import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.InvalidMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

class MicroResolveMemoryAddress implements MicroInstruction {
    private final int memoryAddress;

    public MicroResolveMemoryAddress(final int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        Checks.require(state.memoryAddressExists(this.memoryAddress), InvalidMemoryAddressException.class);
        state.setRegister(Register.RESOLVED_ADDRESS, Value.number(this.memoryAddress));
        return Action.locateMemoryAddress(this.memoryAddress);
    }
}
