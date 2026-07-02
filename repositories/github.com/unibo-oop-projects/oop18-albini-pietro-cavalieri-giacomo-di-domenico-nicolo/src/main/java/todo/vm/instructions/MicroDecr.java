package todo.vm.instructions;

import todo.vm.Action;

public class MicroDecr extends BaseMicroUnary {
    @Override
    protected int calc(final int currentValue) {
        return currentValue - 1;
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.decr(memoryAddress);
    }
}
