package todo.vm.instructions;

import todo.vm.Action;

class MicroSub extends BaseMicroMath {
    @Override
    protected int calc(final int number1, final int number2) {
        return number1 - number2;
    }

    @Override
    protected Action getAction(final int memoryAddress) {
        return Action.sub(memoryAddress);
    }

}
