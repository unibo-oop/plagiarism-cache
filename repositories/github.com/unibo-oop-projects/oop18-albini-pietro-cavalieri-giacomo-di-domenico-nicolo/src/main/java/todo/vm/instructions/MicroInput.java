package todo.vm.instructions;

import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.VmRuntimeException;

class MicroInput implements MicroInstruction {
    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final Value next = state.getInput();
        if (next.isPresent()) {
            state.setRegister(Register.MAIN_HAND, next);
            return Action.pickInput();
        } else {
            state.endProgram();
            return Action.none();
        }
    }
}
