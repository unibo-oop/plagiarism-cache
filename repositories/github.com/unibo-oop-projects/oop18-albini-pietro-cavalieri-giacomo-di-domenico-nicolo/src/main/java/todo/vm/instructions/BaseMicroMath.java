package todo.vm.instructions;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.BrokenCodegenException;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.EmptyMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

abstract class BaseMicroMath implements MicroInstruction {
    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final Value hand = state.getRegister(Register.MAIN_HAND).orThrow(EmptyHandException.class);
        final int memoryAddress = state.getRegister(Register.RESOLVED_ADDRESS)
                                       .orThrow(BrokenCodegenException.class,
                                               "No resolved address before calling a math microinstruction")
                                       .asNumber();
        final Value memory = state.getMemoryAddress(memoryAddress).orThrow(EmptyMemoryAddressException.class);
        state.setRegister(Register.MAIN_HAND, Value.number(calc(hand.asNumber(), memory.asNumber())));
        return getAction(memoryAddress);
    }

    protected abstract int calc(int number1, int number2);

    protected abstract Action getAction(int memoryAddress);
}
