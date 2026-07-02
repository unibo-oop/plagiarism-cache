package todo.model.level.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todo.model.level.LevelImpl;
import todo.model.level.inputs.EnsureLastZero;
import todo.model.level.inputs.EnsureZeros;
import todo.model.level.inputs.InputModifier;
import todo.model.level.inputs.ModifierWithCount;
import todo.model.level.inputs.RandomIntegersGenerator;
import todo.model.level.inputs.ShuffleInput;
import todo.vm.Value;
import todo.vm.instructions.Add;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.CopyTo;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Incr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.JumpNegative;
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;
import todo.vm.instructions.Sub;

public class ZeroTerminatedSumTest extends BaseParserTest {
    private static final String FILE_PATH = "assets/levels/ZeroTerminatedSum.xml";

    @Before
    public void init() {

        final List<InputModifier> modifiers = new ArrayList<>();
        final ModifierWithCount zeros = new EnsureZeros();
        zeros.setCount(5);
        modifiers.add(zeros);
        modifiers.add(new ShuffleInput());
        modifiers.add(new EnsureLastZero());
        final RandomIntegersGenerator generator = new RandomIntegersGenerator(modifiers);
        generator.setMax(10).setMin(-10).setSize(20);

        final JumpInstruction goToInput = new Jump();
        final JumpInstruction inputZero = new JumpZero();
        final JumpInstruction terminateSum = new JumpZero();
        final JumpInstruction iterateSum = new Jump();

        super.builtLevel = new LevelImpl.LevelBuilder().setTitle("Zero Terminated Sum")
                                                       .setDescription(
                                                               "The INPUT is filled with zero terminated strings!"
                                                                       + "\nAdd together all the numbers in each string. "
                                                                       + "When you reach the end of a string (marked by a ZERO), "
                                                                       + "put your sum in the OUTPUT.\nReset and repeat for each string.")
                                                       .setInputGenerator(generator)
                                                       .setMemoryRows(2)
                                                       .setMemoryColumns(3)
                                                       .addAllowedInstruction(Input.class)
                                                       .addAllowedInstruction(Output.class)
                                                       .addAllowedInstruction(CopyFrom.class)
                                                       .addAllowedInstruction(CopyTo.class)
                                                       .addAllowedInstruction(Add.class)
                                                       .addAllowedInstruction(Sub.class)
                                                       .addAllowedInstruction(Incr.class)
                                                       .addAllowedInstruction(Decr.class)
                                                       .addAllowedInstruction(Jump.class)
                                                       .addAllowedInstruction(JumpZero.class)
                                                       .addAllowedInstruction(JumpNegative.class)
                                                       .addAllowedInstruction(new Jump().getTarget().getClass())
                                                       .addToSolution(goToInput)
                                                       .addToSolution(terminateSum.getTarget())
                                                       .addToSolution(new CopyFrom(0))
                                                       .addToSolution(inputZero.getTarget())
                                                       .addToSolution(new Output())
                                                       .addToSolution(goToInput.getTarget())
                                                       .addToSolution(new Input())
                                                       .addToSolution(inputZero)
                                                       .addToSolution(iterateSum.getTarget())
                                                       .addToSolution(new CopyTo(0))
                                                       .addToSolution(new Input())
                                                       .addToSolution(terminateSum)
                                                       .addToSolution(new Add(0))
                                                       .addToSolution(iterateSum)
                                                       .addValueToAddress(Value.number(0), 5)
                                                       .build();
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    @Test
    public void testInput() {
        final List<Value> values = super.parsedLevel.getInput();
        values.stream().map(Value::asNumber).forEach(val -> assertTrue(val <= 10 && val >= -10));
        assertTrue(values.stream().filter(val -> val.asNumber() == 0).count() >= 5);
        assertTrue(values.stream().filter(val -> val.asNumber() < 0).count() >= 2);
        assertEquals(Value.number(0), values.get(values.size() - 1));
    }
}
