package todo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;

import todo.model.program.DummyClipboard;
import todo.model.program.Program;
import todo.model.program.ProgramImpl;
import todo.vm.instructions.Instruction;

/**
 * Abstract class extended by any test that involves any action that would save
 * a Program.
 */
public abstract class AbstractFileSavingTest {
    protected final static String SAVE_DIRECTORY = System.getProperty("user.home")
            + System.getProperty("file.separator") + ".todo";
    protected final String savingFile;
    protected final LevelController levelController;
    private Program backupProgram;

    public AbstractFileSavingTest(final String levelTitle) throws IOException {
        this.levelController = new ControllerImpl(new DummyClipboard()).loadLevel(levelTitle);
        this.savingFile = SAVE_DIRECTORY + System.getProperty("file.separator") + levelTitle;
    }

    @Before
    public final void prepareTest() throws IOException {
        this.backupProgram = SaveManager.loadProgram(this.levelController, new DummyClipboard());
        Files.deleteIfExists(Paths.get(this.savingFile));
        onBefore();
    }

    protected abstract void onBefore();

    @After
    public final void restoreProgram() {
        SaveManager.saveProgram(this.backupProgram, this.levelController);
        onAfter();
    }

    protected abstract void onAfter();

    protected Program createSimpleProgram(final List<Instruction> instructionList) {
        return new ProgramImpl(instructionList,
                instructionList.stream()
                               .map(Instruction::getClass)
                               .collect(Collectors.toSet()), new DummyClipboard(),
                this.levelController.getEventManager());
    }

}
