package todo.model.program;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todo.model.program.ReplaceAllCommand;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.Output;

public class CommandTest {
    private final static List<Instruction> BASE_INSTRUCTIONS = Arrays.asList(new Input(), new Output());
    private final List<Instruction> testInstructions;

    public CommandTest() {
        this.testInstructions = new ArrayList<>();
    }

    @Before
    public void prepareInstructions() {
        this.testInstructions.clear();
        this.testInstructions.addAll(BASE_INSTRUCTIONS);
    }

    private void testSingleCommand(final ProgramCommand command, final List<Instruction> expected) {
        final List<Instruction> starting = new ArrayList<>(this.testInstructions);
        command.execute();
        assertEquals(expected, this.testInstructions);
        command.unexecute();
        assertEquals(starting, this.testInstructions);
    }

    @Test
    public void testAdd() {
        testSingleCommand(new AddCommand(this.testInstructions, 1, new Input()),
                Arrays.asList(new Input(), new Input(), new Output()));
    }

    @Test
    public void testAddJump() {
        final JumpInstruction jump = new Jump();
        testSingleCommand(new AddCommand(this.testInstructions, this.testInstructions.size(), jump),
                Arrays.asList(new Input(), new Output(), jump, jump.getTarget()));
    }

    @Test
    public void testRemove() {
        testSingleCommand(new RemoveCommand(this.testInstructions, 1), Arrays.asList(new Input()));
    }

    @Test
    public void testRemoveJump() {
        new AddCommand(this.testInstructions, 0, new Jump()).execute();
        new MoveCommand(this.testInstructions, 0, 3).execute();
        testSingleCommand(new RemoveCommand(this.testInstructions, 3), BASE_INSTRUCTIONS);
    }

    @Test
    public void testMove() {
        testSingleCommand(new MoveCommand(this.testInstructions, 0, 1), Arrays.asList(new Output(), new Input()));
    }

    @Test
    public void testReplace() {
        testSingleCommand(new ReplaceCommand(this.testInstructions, 1, new Decr(0)),
                Arrays.asList(new Input(), new Decr(0)));
    }

    @Test
    public void testClear() {
        testSingleCommand(new ClearCommand(this.testInstructions), Collections.emptyList());
    }

    @Test
    public void testReplaceAll() {
        testSingleCommand(new ReplaceAllCommand(this.testInstructions, Arrays.asList(new Input())),
                Arrays.asList(new Input()));
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongExecute() {
        final ProgramCommand add = new AddCommand(this.testInstructions, 0, new Input());
        add.execute();
        add.execute();
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongUnexecute() {
        final ProgramCommand add = new AddCommand(this.testInstructions, 0, new Input());
        add.unexecute();
    }

    @Test(expected = NullPointerException.class)
    public void testNullReceiver() {
        new AddCommand(null, 0, new Input());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullInstruction() {
        new AddCommand(this.testInstructions, 0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongAdd() {
        new AddCommand(this.testInstructions, 0, new Jump().getTarget());
    }
}
