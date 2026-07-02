package todo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import todo.model.program.DummyClipboard;
import todo.model.program.Program;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Output;

public class SaveManagerTest extends AbstractFileSavingTest {
    private final static String LEVEL_TITLE = "Busy Mail Room";
    private final static List<Instruction> BASE_INSTRUCTIONS = Arrays.asList(new Input(), new Output());

    private final Program program;

    public SaveManagerTest() throws IOException {
        super(LEVEL_TITLE);
        this.program = createSimpleProgram(BASE_INSTRUCTIONS);
    }

    @Test
    public void testSave() {
        SaveManager.saveProgram(this.program, this.levelController);
        assertTrue(new File(this.savingFile).exists());
    }

    @Test
    public void testOverwriteSave() {
        SaveManager.saveProgram(this.program, this.levelController);
        final Program finalProgram = createSimpleProgram(Arrays.asList(new Input()));
        SaveManager.saveProgram(finalProgram, this.levelController);
        assertTrue(new File(this.savingFile).exists());
        assertEquals(finalProgram, SaveManager.loadProgram(this.levelController, new DummyClipboard()));
    }

    @Test
    public void testLoad() {
        SaveManager.saveProgram(this.program, this.levelController);
        assertEquals(this.program, SaveManager.loadProgram(this.levelController, new DummyClipboard()));
    }

    @Test
    public void testLoadUnexistingFile() {
        assertTrue(SaveManager.loadProgram(this.levelController, new DummyClipboard()).getInstructions().isEmpty());
    }

    @Test
    public void testWrongSave() {
        final Program wrongProgram = createSimpleProgram(Arrays.asList(new Decr(0)));
        SaveManager.saveProgram(wrongProgram, this.levelController);
        assertFalse(new File(this.savingFile).exists());
    }

    @Override
    protected void onBefore() {
    }

    @Override
    protected void onAfter() {
    }
}
