package todo.model.program;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import todo.controller.clipboard.ClipboardProvider;
import todo.controller.events.Event;
import todo.controller.events.EventManager;
import todo.controller.events.ParsingErrorEvent;
import todo.model.program.Program;
import todo.model.program.ProgramImpl;
import todo.vm.instructions.Add;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Output;

public class ProgramTest {
    private final static List<Instruction> BASE_INSTRUCTIONS = Arrays.asList(new Input(), new Output());
    private final static ClipboardProvider CLIPBOARD = new DummyClipboard();
    private Program program;

    @Before
    public void prepareProgram() {
        this.program = new ProgramImpl(BASE_INSTRUCTIONS,
                new LinkedHashSet<>(Arrays.asList(Input.class, Output.class, Decr.class)), CLIPBOARD,
                new DummyEventManager());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0, this.program.getInstructionIndex(BASE_INSTRUCTIONS.get(0)));
        assertEquals(1, this.program.getInstructionIndex(BASE_INSTRUCTIONS.get(1)));
    }

    @Test
    public void testAdd() {
        assertEquals(BASE_INSTRUCTIONS, this.program.getInstructions());
        // Testing an insertion that results in a shift
        this.program.add(1, new Decr(1));
        assertEquals(Arrays.asList(new Input(), new Decr(1), new Output()), this.program.getInstructions());
        // Testing an "out of bound" insertion that results in appending the element
        this.program.add(this.program.getInstructions().size(), new Decr(1));
        assertEquals(Arrays.asList(new Input(), new Decr(1), new Output(), new Decr(1)),
                this.program.getInstructions());
    }

    @Test
    public void testRemove() {
        this.program.remove(1);
        assertEquals(Arrays.asList(new Input()), this.program.getInstructions());
        this.program.remove(0);
        assertTrue(this.program.getInstructions().isEmpty());
    }

    @Test
    public void testMove() {
        this.program.move(0, 1);
        assertEquals(Arrays.asList(new Output(), new Input()), this.program.getInstructions());
        this.program.move(1, 0);
        assertEquals(BASE_INSTRUCTIONS, this.program.getInstructions());
    }

    @Test
    public void testReplace() {
        this.program.replace(0, new Decr(1));
        this.program.replace(1, new Input());
        assertEquals(Arrays.asList(new Decr(1), new Input()), this.program.getInstructions());
    }

    @Test
    public void testClear() {
        this.program.clear();
        assertTrue(this.program.getInstructions().isEmpty());
    }

    @Test
    public void testCopy() {
        this.program.copy();
        assertEquals("input\noutput\n", CLIPBOARD.get().get());
    }

    @Test
    public void testPaste() {
        this.program.copy();
        this.program.clear();
        this.program.paste();
        assertEquals(this.program.getInstructions(), BASE_INSTRUCTIONS);
        this.program.undo();
        assertTrue(this.program.getInstructions().isEmpty());
        this.program.redo();
        assertEquals(this.program.getInstructions(), BASE_INSTRUCTIONS);
    }

    @Test
    public void testWrongPaste() {
        CLIPBOARD.set("errore");
        this.program.paste();
        assertEquals(this.program.getInstructions(), BASE_INSTRUCTIONS);
    }

    @Test
    public void testHistoryNavigation() {
        this.program.clear();
        this.program.add(0, new Decr(1)); // D
        this.program.add(1, new Input()); // D I
        this.program.move(1, 0); // I D
        this.program.replace(0, new Output()); // O D
        this.program.remove(0); // D
        assertEquals(Arrays.asList(new Decr(1)), this.program.getInstructions());
        // Start undoing the changes
        this.program.undo();
        assertEquals(Arrays.asList(new Output(), new Decr(1)), this.program.getInstructions());
        this.program.undo();
        assertEquals(Arrays.asList(new Input(), new Decr(1)), this.program.getInstructions());
        this.program.undo();
        assertEquals(Arrays.asList(new Decr(1), new Input()), this.program.getInstructions());
        this.program.undo();
        assertEquals(Arrays.asList(new Decr(1)), this.program.getInstructions());
        this.program.undo();
        assertTrue(this.program.getInstructions().isEmpty());
        this.program.undo();
        assertEquals(BASE_INSTRUCTIONS, this.program.getInstructions());
        this.program.redo();
        this.program.redo();
        this.program.redo();
        assertEquals(Arrays.asList(new Decr(1), new Input()), this.program.getInstructions());
    }

    @Test
    public void testNotAllowedAdd() {
        this.program.add(0, new Add(0));
        assertEquals(BASE_INSTRUCTIONS, this.program.getInstructions());
    }

    @Test
    public void testNotAllowedReplace() {
        this.program.replace(0, new Add(0));
        assertEquals(BASE_INSTRUCTIONS, this.program.getInstructions());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetWrongIndex() {
        // As the indexing with the getIndex method if called with a different
        // instruction but with the same type should throw an exception
        this.program.getInstructionIndex(new Input());
    }

    @Test(expected = IllegalStateException.class)
    public void testChangeHistory() {
        this.program.add(1, new Input());
        this.program.undo();
        this.program.add(1, new Input());
        // The user shouldn't be able to redo any changes as he modified the program
        // while back in the history, thus rewriting it.
        this.program.redo();
    }

    @Test(expected = IllegalStateException.class)
    public void testOverflowHistory() {
        final int historyLayers = 20;
        for (int i = 0; i < historyLayers; i++) {
            this.program.add(1, new Input());
        }
        for (int i = 0; i < historyLayers + 1; i++) {
            this.program.undo();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongUndo() {
        this.program.undo();
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongRedo() {
        this.program.redo();
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongPasteWithUndo() {
        CLIPBOARD.set("errore");
        this.program.paste();
        this.program.undo();
    }

    @Test(expected = NullPointerException.class)
    public void testNullAdd() {
        this.program.add(0, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullReplace() {
        this.program.replace(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundMove() {
        this.program.move(0, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundReplace() {
        this.program.replace(-1, new Input());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundAdd() {
        this.program.add(-1, new Input());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBoundRemove() {
        this.program.remove(-1);
    }

    private class DummyEventManager implements EventManager {
        @Override
        public <T extends Event> void listen(final Class<T> type, final Consumer<T> handler) {
            // Does nothing.
        }

        @Override
        public <T extends Event> void dispatch(final T event) {
            if (!event.getClass().equals(ParsingErrorEvent.class)) {
                fail();
            }
        }
    }
}
