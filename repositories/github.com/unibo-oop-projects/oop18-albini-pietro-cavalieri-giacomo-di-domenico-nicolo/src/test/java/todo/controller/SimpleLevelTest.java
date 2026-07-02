package todo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import todo.controller.events.ExecutionErrorEvent;
import todo.controller.events.PathologicalInputEvent;
import todo.controller.events.SuccessfulExecutionEvent;
import todo.controller.events.WrongOutputEvent;
import todo.model.program.DummyClipboard;
import todo.model.program.Program;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Jump;
import todo.vm.instructions.Output;

public class SimpleLevelTest extends AbstractFileSavingTest {
    private static final String LEVEL_TITLE = "Busy Mail Room";

    private final LevelController controller;
    private final List<String> errorsLog;
    private final List<String> successLog;
    private Optional<SuccessfulExecutionEvent> lastSuccessfulEvent;
    private ExecutionController exec;

    public SimpleLevelTest() throws IOException {
        super(LEVEL_TITLE);
        this.errorsLog = new ArrayList<>();
        this.successLog = new ArrayList<>();
        this.lastSuccessfulEvent = Optional.empty();
        this.controller = new ControllerImpl(new DummyClipboard()).loadLevel(LEVEL_TITLE);
        this.controller.getEventManager().listen(ExecutionErrorEvent.class, e -> this.errorsLog.add(e.getMessage()));
        this.controller.getEventManager().listen(PathologicalInputEvent.class, e -> this.errorsLog.add(e.getMessage()));
        this.controller.getEventManager().listen(WrongOutputEvent.class, e -> this.errorsLog.add(e.getMessage()));
        this.controller.getEventManager().listen(SuccessfulExecutionEvent.class, e -> {
            this.successLog.add(e.getMessage());
            this.lastSuccessfulEvent = Optional.of(e);
        });
    }

    @Override
    protected void onBefore() {
        this.errorsLog.clear();
        this.successLog.clear();
        this.controller.getProgram().clear();
    }

    @Override
    protected void onAfter() {
        assertTrue(this.exec.hasFinished());
    }

    @Test
    public void testCorrectProgram() {
        final Program program = this.controller.getProgram();
        program.add(0, new Jump());
        program.move(0, 1);
        program.add(1, new Input());
        program.add(2, new Output());
        runProgram();
        assertTrue(this.lastSuccessfulEvent.get().getInstructionsGoal().isReached());
        assertTrue(this.lastSuccessfulEvent.get().getStepsGoal().isReached());
        assertTrue(this.errorsLog.isEmpty());
        assertEquals(1, this.successLog.size());
    }

    @Test
    public void testProgramWithNoInstructionGoal() {
        runProgram(new Input(), new Output(), new Input(), new Output(), new Input(), new Output(), new Input(),
                new Output(), new Input(), new Output(), new Input(), new Output(), new Input(), new Output(),
                new Input(), new Output(), new Input(), new Output(), new Input(), new Output(), new Input(),
                new Output(), new Input(), new Output());
        assertFalse(this.lastSuccessfulEvent.get().getInstructionsGoal().isReached());
        assertTrue(this.lastSuccessfulEvent.get().getStepsGoal().isReached());
        assertTrue(this.errorsLog.isEmpty());
        assertEquals(1, this.successLog.size());
    }

    @Test
    public void testWrongOutputProgram() {
        runProgram(new Input(), new Output());
        assertEquals(1, this.errorsLog.size());
        assertTrue(this.successLog.isEmpty());
    }

    private void runProgram(final Instruction... instructions) {
        setInstructions(instructions);
        this.exec = this.controller.start();
        while (!this.exec.hasFinished()) {
            this.exec.step();
        }
    }

    private void setInstructions(final Instruction... instructions) {
        final Program program = this.controller.getProgram();
        for (final Instruction instr : instructions) {
            program.add(program.getInstructions().size(), instr);
        }
    }
}
