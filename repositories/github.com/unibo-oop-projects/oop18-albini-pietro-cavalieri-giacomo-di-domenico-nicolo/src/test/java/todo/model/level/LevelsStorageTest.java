package todo.model.level;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

import todo.model.level.LevelImpl.LevelBuilder;
import todo.model.level.inputs.InputGenerator;
import todo.model.level.inputs.RandomIntegersGenerator;
import todo.vm.instructions.Input;
import todo.vm.instructions.Jump;
import todo.vm.instructions.Output;

public class LevelsStorageTest {
    private static final String LEVEL_NAME = "Busy Mail Room";

    private Level getFirstLevel() {
        final InputGenerator generator = new RandomIntegersGenerator(new ArrayList<>());
        generator.setMin(1).setMax(20).setSize(12);
        final Jump jump = new Jump();
        return new LevelBuilder().setTitle(LEVEL_NAME)
                                 .setDescription("Grab each thing from the INPUT and drop each one into the OUTPUT.")
                                 .setInputGenerator(generator)
                                 .addAllowedInstruction(Output.class)
                                 .addAllowedInstruction(Input.class)
                                 .addAllowedInstruction(Jump.class)
                                 .addAllowedInstruction(new Jump().getTarget().getClass())
                                 .addToSolution(jump.getTarget())
                                 .addToSolution(new Input())
                                 .addToSolution(new Output())
                                 .addToSolution(jump)
                                 .setMemoryRows(0)
                                 .setMemoryColumns(0)
                                 .build();
    }

    private LevelsStorage getStorageInstance() {
        LevelsStorage storage = null;
        try {
            storage = new LevelsStorageImpl();
        } catch (final IOException e) {
            fail("An error occured while creating a new Levels Storage");
        }
        return storage;
    }

    @Test
    public void testAllLevels() {
        assertThat(getStorageInstance().getAllLevels(), hasItems(getFirstLevel()));
    }

    @Test
    public void testGetLevel() {
        assertEquals(getFirstLevel(), getStorageInstance().getLevel(LEVEL_NAME));
    }

    @Test(expected = NoSuchElementException.class)
    public void testExceptions() {
        getStorageInstance().getLevel("Tester");
    }
}
