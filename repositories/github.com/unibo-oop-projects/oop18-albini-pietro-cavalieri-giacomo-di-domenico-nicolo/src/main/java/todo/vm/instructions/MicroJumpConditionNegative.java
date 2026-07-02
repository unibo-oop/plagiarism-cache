package todo.vm.instructions;

class MicroJumpConditionNegative extends BaseMicroJumpCondition {
    @Override
    protected boolean decide(final int asNumber) {
        return asNumber < 0;
    }
}
