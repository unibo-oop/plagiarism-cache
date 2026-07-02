package todo.controller.events;

import todo.controller.OptimizationGoal;

public class SuccessfulExecutionEvent implements Event {
    private final OptimizationGoal instructionsGoal;
    private final OptimizationGoal stepsGoal;

    public SuccessfulExecutionEvent(final OptimizationGoal instructionsGoal, final OptimizationGoal stepsGoal) {
        this.instructionsGoal = instructionsGoal;
        this.stepsGoal = stepsGoal;
    }

    @Override
    public String getMessage() {
        return "Your program is correct!";
    }

    public OptimizationGoal getInstructionsGoal() {
        return this.instructionsGoal;
    }

    public OptimizationGoal getStepsGoal() {
        return this.stepsGoal;
    }
}
