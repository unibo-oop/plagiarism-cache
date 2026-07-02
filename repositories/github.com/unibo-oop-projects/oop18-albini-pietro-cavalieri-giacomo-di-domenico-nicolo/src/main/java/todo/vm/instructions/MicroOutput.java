package todo.vm.instructions;

import todo.utils.Checks;
import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.EmptyValueInOutputException;
import todo.vm.exceptions.VmRuntimeException;

class MicroOutput implements MicroInstruction {
    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final Value hand = state.getRegister(Register.MAIN_HAND);
        Checks.require(hand.isPresent(), EmptyValueInOutputException.class);
        state.addOutput(hand);
        state.setRegister(Register.MAIN_HAND, Value.empty());
        return Action.dropOutput();
    }
}
