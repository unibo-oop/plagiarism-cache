package todo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import todo.controller.events.ExecutionErrorEvent;
import todo.controller.events.WrongOutputEvent;
import todo.model.program.DummyClipboard;
import todo.model.program.Program;
import todo.vm.ActionKind;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.Jump;
import todo.vm.instructions.Output;

public class ExecutionControllerTest extends AbstractFileSavingTest {
    private static final String LEVEL_TITLE = "Copy Floor";

    private final Controller controller;
    private final List<String> errors;
    private boolean wrongOutput;

    public ExecutionControllerTest() throws IOException {
        super(LEVEL_TITLE);
        this.controller = new ControllerImpl(new DummyClipboard());
        this.errors = new ArrayList<>();
        this.wrongOutput = false;
    }

    @Test
    public void testStep() {
        final ExecutionController exec = startDefaultLevel();
        assertFalse(exec.hasFinished());
        assertTrue(exec.getOutput().isEmpty());
        assertEquals(ActionKind.LOCATE_MEMORY_ADDRESS, exec.step().getKind());
        assertEquals(ActionKind.COPY_FROM, exec.step().getKind());
        assertEquals(ActionKind.DROP_OUTPUT, exec.step().getKind());
        assertEquals(ActionKind.LOCATE_MEMORY_ADDRESS, exec.step().getKind());
        assertEquals(ActionKind.COPY_FROM, exec.step().getKind());
        assertEquals(ActionKind.DROP_OUTPUT, exec.step().getKind());
        // Executes the wrong instruction
        assertTrue(this.errors.isEmpty());
        assertEquals(ActionKind.NONE, exec.step().getKind());
        assertFalse(this.errors.isEmpty());
        assertTrue(exec.hasFinished());
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongStep() {
        final ExecutionController exec = startDefaultLevel();
        exec.stop();
        exec.step();
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongStepAfterError() {
        final ExecutionController exec = startDefaultLevel();
        IntStream.range(0, 7).forEach(i -> exec.step());
        exec.step();
    }

    @Test
    public void testWrongOutputInfiniteLoop() throws IOException {
        final LevelController level = loadLevel(LEVEL_TITLE);
        final Program program = level.getProgram();
        // a JumpTarget is later moved here
        program.add(0, new CopyFrom(4));
        program.add(1, new Output());
        program.add(2, new Jump());
        program.move(3, 0); // Move the JumpTarget up
        final ExecutionController exec = level.start();
        while (!exec.hasFinished()) {
            exec.step();
        }
        assertTrue(this.wrongOutput);
    }

    @Test
    public void testWrongOutputAfterGoodOutput() throws IOException {
        final LevelController level = loadLevel(LEVEL_TITLE);
        final Program program = level.getProgram();
        program.add(0, new CopyFrom(4));
        program.add(1, new Output());
        program.add(2, new CopyFrom(4));
        program.add(3, new Output());
        program.add(4, new CopyFrom(3));
        program.add(5, new Output());
        program.add(6, new CopyFrom(0));
        program.add(7, new Output());
        final ExecutionController exec = level.start();
        while (!exec.hasFinished()) {
            exec.step();
        }
        assertTrue(this.wrongOutput);
    }

    private LevelController loadLevel(final String name) {
        final LevelController level = this.controller.loadLevel(name);
        level.getEventManager().listen(ExecutionErrorEvent.class, e -> this.errors.add(e.getMessage()));
        level.getEventManager().listen(WrongOutputEvent.class, e -> this.wrongOutput = true);
        return level;
    }

    private ExecutionController startDefaultLevel() {
        final LevelController level = loadLevel(LEVEL_TITLE);
        final Program program = level.getProgram();
        program.add(0, new CopyFrom(1));
        program.add(1, new Output());
        program.add(2, new CopyFrom(4));
        program.add(3, new Output());
        program.add(4, new Output()); // Wrong Instruction to generate an error
        return level.start();
    }

    @Override
    protected void onBefore() {
    }

    @Override
    protected void onAfter() {
    }
}
