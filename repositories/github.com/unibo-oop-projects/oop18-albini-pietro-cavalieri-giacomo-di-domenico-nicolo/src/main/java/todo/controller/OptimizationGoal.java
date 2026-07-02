package todo.controller;

public class OptimizationGoal {
    private final boolean reached;
    private final int target;
    private final int result;

    OptimizationGoal(final int target, final int result) {
        this.reached = result <= target;
        this.target = target;
        this.result = result;
    }

    public boolean isReached() {
        return this.reached;
    }

    public int getTarget() {
        return this.target;
    }

    public int getResult() {
        return this.result;
    }

}
