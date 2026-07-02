package todo.vm.instructions;

import todo.vm.Action;

class MicroAdd extends BaseMicroMath {
    @Override
    protected int calc(final int number1, final int number2) {
        return number1 + number2;
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.add(memoryAddress);
    }
}
