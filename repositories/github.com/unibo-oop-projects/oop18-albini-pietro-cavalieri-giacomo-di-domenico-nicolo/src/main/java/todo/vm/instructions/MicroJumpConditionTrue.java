package todo.vm.instructions;

class MicroJumpConditionTrue extends BaseMicroJumpCondition {
    @Override
    protected boolean emptyHandAllowed() {
        return true;
    }

    @Override
    protected boolean decide(final int asNumber) {
        return true;
    }
}
