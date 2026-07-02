package todo.vm.instructions;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.VmRuntimeException;

class MicroDropMainHand implements MicroInstruction {
    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        if (state.getRegister(Register.MAIN_HAND).isPresent()) {
            state.setRegister(Register.MAIN_HAND, Value.empty());
            return Action.dropMainHand();
        } else {
            return Action.none();
        }
    }
}
