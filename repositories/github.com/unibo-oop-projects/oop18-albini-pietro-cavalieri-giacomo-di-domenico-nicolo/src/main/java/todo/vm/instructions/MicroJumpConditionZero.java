package todo.vm.instructions;

class MicroJumpConditionZero extends BaseMicroJumpCondition {
    @Override
    protected boolean decide(final int asNumber) {
        return asNumber == 0;
    }
}
