package todo.vm.instructions;

import todo.vm.Action;

class MicroIncr extends BaseMicroUnary {

    @Override
    protected int calc(final int currentValue) {
        return currentValue + 1;
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.incr(memoryAddress);
    }
}
