package todo.model.level.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todo.model.level.LevelImpl;
import todo.model.level.inputs.EnsureNegatives;
import todo.model.level.inputs.EnsureZeros;
import todo.model.level.inputs.InputModifier;
import todo.model.level.inputs.ModifierWithBound;
import todo.model.level.inputs.ModifierWithCount;
import todo.model.level.inputs.RandomIntegersGenerator;
import todo.model.level.inputs.ShuffleInput;
import todo.vm.Value;
import todo.vm.instructions.Add;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.CopyTo;
import todo.vm.instructions.Input;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.JumpNegative;
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;
import todo.vm.instructions.Sub;

public class AbsolutePositivityTest extends BaseParserTest {
    private static final String FILE_PATH = "assets/levels/AbsolutePositivity.xml";

    @Before
    public void init() {

        final List<InputModifier> modifiers = new ArrayList<>();
        final ModifierWithCount zeros = new EnsureZeros();
        zeros.setCount(1);
        final ModifierWithCount negatives = new EnsureNegatives();
        negatives.setCount(1);
        final ModifierWithBound negBound = (ModifierWithBound) negatives;
        negBound.setBound(-10);

        modifiers.add(zeros);
        modifiers.add(negatives);
        modifiers.add(new ShuffleInput());
        final RandomIntegersGenerator generator = new RandomIntegersGenerator(modifiers);
        generator.setMax(10).setMin(-10).setSize(10);

        final JumpInstruction goToInput = new Jump();
        final JumpInstruction ifPositive = new Jump();
        final JumpInstruction ifNegative = new JumpNegative();

        super.builtLevel = new LevelImpl.LevelBuilder().setTitle("Absolute Positivity")
                                                       .setDescription("Send each thing from INPUT to OUTPUT. "
                                                               + "BUT, if a number is negative, "
                                                               + "first remove its negative sign.")
                                                       .setMemoryRows(3)
                                                       .setMemoryColumns(1)
                                                       .setInputGenerator(generator)
                                                       .addAllowedInstruction(Input.class)
                                                       .addAllowedInstruction(Output.class)
                                                       .addAllowedInstruction(CopyFrom.class)
                                                       .addAllowedInstruction(CopyTo.class)
                                                       .addAllowedInstruction(Add.class)
                                                       .addAllowedInstruction(Sub.class)
                                                       .addAllowedInstruction(Jump.class)
                                                       .addAllowedInstruction(JumpZero.class)
                                                       .addAllowedInstruction(JumpNegative.class)
                                                       .addAllowedInstruction(new Jump().getTarget().getClass())
                                                       .addToSolution(goToInput)
                                                       .addToSolution(ifNegative.getTarget())
                                                       .addToSolution(new CopyTo(0))
                                                       .addToSolution(new Sub(0))
                                                       .addToSolution(new Sub(0))
                                                       .addToSolution(ifPositive.getTarget())
                                                       .addToSolution(new Output())
                                                       .addToSolution(goToInput.getTarget())
                                                       .addToSolution(new Input())
                                                       .addToSolution(ifNegative)
                                                       .addToSolution(ifPositive)
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
        values.stream().map(Value::asNumber).forEach(nat -> assertTrue(nat >= -10 && nat <= 10));
        assertTrue(values.stream().filter(val -> val.asNumber() == 0).count() > 0);
        assertTrue(values.stream().filter(val -> val.asNumber() < 0).count() > 0);
        assertEquals(10, super.parsedLevel.getInput().size());
    }

}
