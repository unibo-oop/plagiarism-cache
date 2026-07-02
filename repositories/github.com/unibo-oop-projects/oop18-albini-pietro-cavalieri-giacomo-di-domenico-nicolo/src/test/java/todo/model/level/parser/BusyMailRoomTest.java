package todo.model.level.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import todo.model.level.LevelImpl;
import todo.model.level.inputs.RandomIntegersGenerator;
import todo.vm.Value;
import todo.vm.instructions.Input;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.Output;

public class BusyMailRoomTest extends BaseParserTest {
    private static final String FILE_PATH = "assets/levels/BusyMailRoom.xml";

    @Before
    public void init() {

        final RandomIntegersGenerator generator = new RandomIntegersGenerator(new ArrayList<>());
        generator.setMax(10).setMin(1).setSize(12);

        final JumpInstruction mainLoop = new Jump();

        super.builtLevel = new LevelImpl.LevelBuilder().setTitle("Busy Mail Room")
                                                       .setDescription(
                                                               "Grab each thing from the INPUT and drop each one into the OUTPUT. "
                                                                       + "You have a new instruction!")
                                                       .setInputGenerator(generator)
                                                       .setMemoryRows(0)
                                                       .setMemoryColumns(0)
                                                       .addAllowedInstruction(Input.class)
                                                       .addAllowedInstruction(Output.class)
                                                       .addAllowedInstruction(Jump.class)
                                                       .addAllowedInstruction(new Jump().getTarget().getClass())
                                                       .addToSolution(mainLoop.getTarget())
                                                       .addToSolution(new Input())
                                                       .addToSolution(new Output())
                                                       .addToSolution(mainLoop)
                                                       .build();
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    @Test
    public void testInput() {
        super.parsedLevel.getInput().stream().map(Value::asNumber).forEach(nat -> assertTrue(nat >= 1 && nat <= 20));
        assertEquals(12, super.parsedLevel.getInput().size());
    }
}
