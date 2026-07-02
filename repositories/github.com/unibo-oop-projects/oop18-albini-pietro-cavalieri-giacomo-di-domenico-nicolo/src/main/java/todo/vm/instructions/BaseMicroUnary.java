package todo.vm.instructions;

import todo.utils.Checks;
import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.UnaryOpOnCharException;
import todo.vm.exceptions.VmRuntimeException;

abstract class BaseMicroUnary implements MicroInstruction {
    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final int memoryAddress = state.getRegister(Register.RESOLVED_ADDRESS)
                                       .orThrow(BrokenCodegenException.class,
                                               "No resolved address before calling a math microinstruction")
                                       .asNumber();
        final Value content = state.getMemoryAddress(memoryAddress).orThrow(EmptyMemoryAddressException.class);
        Checks.require(!content.isASCII(), UnaryOpOnCharException.class);
        state.setMemoryAddress(memoryAddress, Value.number(calc(content.asNumber())));
        return getAction(memoryAddress);
    }

    protected abstract int calc(int currentValue);

    protected abstract Action getAction(int memoryAddress);
}
