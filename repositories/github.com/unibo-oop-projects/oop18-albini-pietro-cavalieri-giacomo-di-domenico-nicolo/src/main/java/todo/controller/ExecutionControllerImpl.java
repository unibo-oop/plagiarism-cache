package todo.controller;

import java.util.List;
import java.util.Objects;

import todo.controller.events.ExecutionErrorEvent;
import todo.controller.events.PathologicalInputEvent;
import todo.controller.events.SuccessfulExecutionEvent;
import todo.controller.events.WrongOutputEvent;
import todo.model.level.Level;
import todo.utils.Checks;
import todo.vm.Action;
import todo.vm.Value;
import todo.vm.Vm;
import todo.vm.VmImpl;
import todo.vm.exceptions.VmRuntimeException;
import todo.vm.instructions.Instruction;

public class ExecutionControllerImpl implements ExecutionController {
    private static final int N_CHECKS = 20;

    private final LevelController levelController;
    private final Level referenceLevel;
    private final Vm userVm;
    private final Vm correctVm;
    private boolean stopped;

    ExecutionControllerImpl(final LevelController outerController, final Level referenceLevel) {
        this.levelController = Objects.requireNonNull(outerController);
        this.referenceLevel = Objects.requireNonNull(referenceLevel);
        final List<Instruction> userInstructions = outerController.getProgram().getInstructions();
        this.userVm = new VmImpl(userInstructions, outerController.getCurrentInput(),
                outerController.getLevelMemoryAddresses());
        this.correctVm = new VmImpl(referenceLevel.getSolution(), outerController.getCurrentInput(),
                outerController.getLevelMemoryAddresses());
        try {
            this.correctVm.execute();
        } catch (final VmRuntimeException e) {
            throw new IllegalStateException("There is a bug in the level " + referenceLevel.getTitle(), e);
        }
        this.stopped = false;
        // An empty program can't generate the correct output, so let's send the event
        // notifying the view about it.
        if (userInstructions.isEmpty()) {
            this.levelController.getEventManager().dispatch(new WrongOutputEvent());
        }
    }

    @Override
    public Action step() {
        Checks.require(!hasFinished(), IllegalStateException.class);
        Action resultingAction;
        try {
            resultingAction = this.userVm.step();
        } catch (final VmRuntimeException e) {
            this.levelController.getEventManager().dispatch(new ExecutionErrorEvent(e.toString()));
            stop();
            return Action.none();
        }
        // As soon as a step is performed the controller checks whether the output is
        // correct or not
        if (!isOutputCorrect(this.correctVm.getOutput(), this.userVm.getOutput())) {
            this.levelController.getEventManager().dispatch(new WrongOutputEvent());
            stop();
            return resultingAction;
        }
        // If the vm has finished the execution, the controller checks the program with
        // other inputs
        if (hasFinished()) {
            checkProgram();
        }
        return resultingAction;
    }

    private boolean isOutputCorrect(final List<Value> correctOutput, final List<Value> userOutput) {
        return !(userOutput.size() > correctOutput.size()
                || !correctOutput.subList(0, userOutput.size()).equals(userOutput));
    }

    private void checkProgram() {
        // If the program has ended but the outputs have different sizes the program
        // must be wrong
        if (this.correctVm.getOutput().size() != this.userVm.getOutput().size()) {
            this.levelController.getEventManager().dispatch(new WrongOutputEvent());
            stop();
            return;
        }
        // Runs a vm with the user program and one with the correct program; both share
        // the same input to check the user Vm solves not only a particular input but
        // also many different cases
        for (int i = 0; i < N_CHECKS; i++) {
            final List<Value> input = this.referenceLevel.getInput();
            final Vm userVm = new VmImpl(this.levelController.getProgram().getInstructions(), input,
                    this.levelController.getLevelMemoryAddresses());
            final Vm correctVm = new VmImpl(this.referenceLevel.getSolution(), input,
                    this.levelController.getLevelMemoryAddresses());
            try {
                correctVm.execute();
            } catch (final VmRuntimeException e) {
                // At this point the level VM should not throw any exception
            }
            while (!userVm.hasFinished()) {
                try {
                    userVm.step();
                } catch (final VmRuntimeException e) {
                    this.levelController.getEventManager().dispatch(new PathologicalInputEvent(input));
                    stop();
                    return;
                }
                if (!isOutputCorrect(correctVm.getOutput(), userVm.getOutput())) {
                    this.levelController.getEventManager().dispatch(new PathologicalInputEvent(input));
                    stop();
                    return;
                }
            }
        }
        // Now checks whether the user reached the optimization goals
        final int targetSteps = this.correctVm.getExecutedInstructionsCount();
        final int actualSteps = this.userVm.getExecutedInstructionsCount();
        final OptimizationGoal steps = new OptimizationGoal(targetSteps, actualSteps);
        final int targetInstructions = this.referenceLevel.getSolution().size();
        final int actualInstructions = this.levelController.getProgram().getInstructions().size();
        final OptimizationGoal instructions = new OptimizationGoal(targetInstructions, actualInstructions);

        // At this point the program must be correct
        this.levelController.getEventManager().dispatch(new SuccessfulExecutionEvent(instructions, steps));
        stop();
    }

    @Override
    public void stop() {
        this.stopped = true;
    }

    @Override
    public List<Value> getOutput() {
        return this.userVm.getOutput();
    }

    @Override
    public Value getMemoryAddressContent(final int memoryAddress) {
        return this.userVm.getMemoryAddressContent(memoryAddress);
    }

    @Override
    public Value getMainHandContent() {
        return this.userVm.getMainHandContent();
    }

    @Override
    public boolean hasFinished() {
        return this.userVm.hasFinished() || this.stopped;
    }

    @Override
    public boolean isVmAtInstructionBoundary() {
        return this.userVm.isAtInstructionBoundary();
    }
}
