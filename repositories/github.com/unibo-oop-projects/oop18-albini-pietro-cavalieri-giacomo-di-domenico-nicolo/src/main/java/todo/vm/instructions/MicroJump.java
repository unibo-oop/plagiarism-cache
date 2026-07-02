package todo.vm.instructions;

import java.util.Objects;

import todo.utils.Checks;
import todo.vm.Action;
import todo.vm.Register;
import todo.vm.Value;
import todo.vm.VmCode;
import todo.vm.VmState;
import todo.vm.exceptions.VmRuntimeException;

public class MicroJump implements MicroInstruction {
    private final Instruction target;

    public MicroJump(final Instruction target) {
        this.target = Objects.requireNonNull(target);
    }

    @Override
    public Action execute(final VmState state, final VmCode code) throws VmRuntimeException {
        final Value condition = state.getRegister(Register.JUMP_CONDITION);
        Checks.require(condition.isPresent(), IllegalStateException.class,
                "MicroJump called with nothing in the JUMP_CONDITION register");
        if (condition.asNumber() == 1) {
            state.setRegister(Register.PROGRAM_COUNTER, Value.number(code.getInstructionIndex(this.target)));
        }
        state.setRegister(Register.JUMP_CONDITION, Value.empty());
        return Action.none();
    }
}
