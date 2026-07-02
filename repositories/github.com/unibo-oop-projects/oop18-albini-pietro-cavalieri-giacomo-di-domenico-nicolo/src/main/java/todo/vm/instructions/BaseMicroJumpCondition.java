package todo.vm.instructions;

import todo.utils.Checks;
import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.EmptyHandException;
import todo.vm.exceptions.VmRuntimeException;

abstract class BaseMicroJumpCondition implements MicroInstruction {
    @Override
    public final Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final Value hand = state.getRegister(Register.MAIN_HAND);
        final boolean decision;
        if (!hand.isPresent()) {
            Checks.require(emptyHandAllowed(), EmptyHandException.class);
            decision = true;
        } else {
            decision = decide(hand.asNumber());
        }
        state.setRegister(Register.JUMP_CONDITION, Value.number(decision ? 1 : 0));
        return Action.none();
    }

    protected boolean emptyHandAllowed() {
        return false;
    }

    protected abstract boolean decide(int asNumber);
}
