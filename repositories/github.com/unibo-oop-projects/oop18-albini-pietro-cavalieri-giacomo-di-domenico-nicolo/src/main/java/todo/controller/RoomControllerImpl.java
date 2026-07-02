package todo.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import todo.controller.events.EventManager;
import todo.controller.events.ExecutionStateChangedEvent;
import todo.controller.events.GameStateChangedEvent;
import todo.utils.Checks;
import todo.vm.instructions.Instruction;

public class RoomControllerImpl implements RoomController {
    private final LevelController levelController;
    private final EventManager eventManager;
    private Optional<ExecutionController> execution;
    private PlayState playState;

    public RoomControllerImpl(final LevelController levelController) {
        this.levelController = Objects.requireNonNull(levelController);
        this.execution = Optional.empty();
        this.playState = PlayState.NONE;
        this.eventManager = levelController.getEventManager();
    }

    @Override
    public LevelController getLevelController() {
        return this.levelController;
    }

    @Override
    public Optional<ExecutionController> getExecutionController() {
        return this.execution;
    }

    @Override
    public void onPlay() {
        Checks.require(!isPlaying(), IllegalStateException.class, "The game is already playing.");
        startExecution();
        this.playState = PlayState.FULLY;
        this.eventManager.dispatch(ExecutionStateChangedEvent.PLAY);
        refreshScreen();
    }

    @Override
    public void onStep() {
        Checks.require(canStep(), IllegalStateException.class, "The game can not be stepped.");
        startExecution();
        this.playState = PlayState.STEP;
        this.eventManager.dispatch(ExecutionStateChangedEvent.STEP);
        refreshScreen();
    }

    @Override
    public void onStop() {
        if (this.execution.isPresent()) {
            this.execution.get().stop();
            this.playState = PlayState.NONE;
        }
        refreshRoom();
        refreshScreen();
    }

    @Override
    public void onUndo() {
        this.levelController.getProgram().undo();
        refreshScreen();
    }

    @Override
    public void onRedo() {
        this.levelController.getProgram().redo();
        refreshScreen();
    }

    @Override
    public void onCopy() {
        this.levelController.getProgram().copy();
    }

    @Override
    public void onPaste() {
        this.levelController.getProgram().paste();
        refreshScreen();
    }

    @Override
    public void add(final Instruction instruction, final int to) {
        this.levelController.getProgram().add(to, Objects.requireNonNull(instruction));
        refreshScreen();
    }

    @Override
    public void move(final Instruction instruction, final int to) {
        this.levelController.getProgram().move(getPosition(instruction), to);
        refreshScreen();
    }

    @Override
    public void remove(final Instruction instruction) {
        this.levelController.getProgram().remove(getPosition(instruction));
        refreshScreen();
    }

    @Override
    public void replace(final Instruction oldInstruction, final Instruction newInstruction) {
        this.levelController.getProgram().replace(getPosition(oldInstruction), newInstruction);
        refreshScreen();
    }

    @Override
    public boolean canUndo() {
        return this.levelController.getProgram().canUndo();
    }

    @Override
    public boolean canRedo() {
        return this.levelController.getProgram().canRedo();
    }

    @Override
    public boolean isPlaying() {
        return this.playState != PlayState.NONE;
    }

    @Override
    public void onClear() {
        this.levelController.getProgram().clear();
        refreshScreen();
    }

    @Override
    public boolean canStep() {
        return this.playState != PlayState.FULLY;
    }

    @Override
    public int getPosition(final Instruction instruction) {
        return this.levelController.getProgram().getInstructionIndex(instruction);
    }

    @Override
    public List<Instruction> getInstructions() {
        return this.levelController.getProgram().getInstructions();
    }

    private void startExecution() {
        if (!this.execution.isPresent() || this.execution.get().hasFinished()) {
            this.execution = Optional.of(this.levelController.start());
        }
    }

    private void refreshScreen() {
        this.eventManager.dispatch(new GameStateChangedEvent());
    }

    private void refreshRoom() {
        this.eventManager.dispatch(ExecutionStateChangedEvent.STOP);
    }

    private enum PlayState {
        FULLY, STEP, NONE;
    }
}
