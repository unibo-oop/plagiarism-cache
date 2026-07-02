package todo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import todo.controller.clipboard.ClipboardProvider;
import todo.controller.events.EventManager;
import todo.controller.events.EventManagerImpl;
import todo.controller.events.ExecutionErrorEvent;
import todo.controller.events.PathologicalInputEvent;
import todo.controller.events.SuccessfulExecutionEvent;
import todo.controller.events.WrongOutputEvent;
import todo.model.level.Level;
import todo.model.program.Program;
import todo.vm.Value;
import todo.vm.instructions.Instruction;

public class LevelControllerImpl implements LevelController {
    private final EventManager eventManager;
    private final Program program;
    private final Level level;
    private List<Value> currentInput;

    LevelControllerImpl(final Level level, final ClipboardProvider clipboard) {
        this.eventManager = new EventManagerImpl();
        this.level = Objects.requireNonNull(level);
        this.program = SaveManager.loadProgram(this, Objects.requireNonNull(clipboard));
        this.currentInput = this.level.getInput();

        this.eventManager.listen(SuccessfulExecutionEvent.class, e -> this.currentInput = this.level.getInput());
        this.eventManager.listen(ExecutionErrorEvent.class, e -> this.currentInput = this.level.getInput());
        this.eventManager.listen(WrongOutputEvent.class, e -> this.currentInput = this.level.getInput());
        this.eventManager.listen(PathologicalInputEvent.class, e -> this.currentInput = e.getInput());
    }

    @Override
    public String getLevelTitle() {
        return this.level.getTitle();
    }

    @Override
    public List<Value> getLevelMemoryAddresses() {
        return this.level.getMemoryAddresses();
    }

    @Override
    public int getLevelMemoryAreaWidth() {
        return this.level.getMemoryColumns();
    }

    @Override
    public int getLevelMemoryAreaHeight() {
        return this.level.getMemoryRows();
    }

    @Override
    public String getLevelDescription() {
        return this.level.getDescription();
    }

    @Override
    public Set<Class<? extends Instruction>> getLevelAllowedInstructions() {
        return this.level.getAllowedInstructions();
    }

    @Override
    public List<Value> getCurrentInput() {
        return Collections.unmodifiableList(this.currentInput);
    }

    @Override
    public ExecutionController start() {
        SaveManager.saveProgram(this.program, this);
        return new ExecutionControllerImpl(this, this.level);
    }

    @Override
    public Program getProgram() {
        return this.program;
    }

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }
}
