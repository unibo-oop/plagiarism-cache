package todo.model.level;

import java.util.ArrayList;

import org.junit.Test;

import todo.model.level.LevelImpl.LevelBuilder;
import todo.model.level.inputs.RandomIntegersGenerator;
import todo.model.level.parser.BaseParserTest;
import todo.vm.Value;
import todo.vm.instructions.Input;
import todo.vm.instructions.Output;

/**
 * The basic tests for the getter are already included in the class
 * {@link BaseParserTest}.
 */
public class LevelBuilderTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyMemoryAddressesCantAdd() {
        new LevelBuilder().setTitle("Busy Mail Room")
                          .setMemoryRows(0)
                          .setMemoryColumns(0)
                          .setInputGenerator(new RandomIntegersGenerator(new ArrayList<>()))
                          .addAllowedInstruction(Input.class)
                          .addToSolution(new Input())
                          .addValueToAddress(Value.number(0), 0)
                          .build();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDoubleCreationBuilder() {
        final LevelBuilder builder = new LevelBuilder();
        builder.setTitle("Mail Room")
               .setInputGenerator(new RandomIntegersGenerator(new ArrayList<>()))
               .addAllowedInstruction(Input.class)
               .setDescription("Foo")
               .addAllowedInstruction(Output.class)
               .addToSolution(new Input())
               .setMemoryColumns(1)
               .setMemoryRows(1)
               .addValueToAddress(Value.number(0), 0)
               .build();
        builder.setTitle("Mail Room")
               .setInputGenerator(new RandomIntegersGenerator(new ArrayList<>()))
               .addAllowedInstruction(Input.class)
               .setDescription("Foo")
               .addAllowedInstruction(Output.class)
               .addToSolution(new Input())
               .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionOnRows() {
        new LevelBuilder().setMemoryRows(-2);
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionOnColumns() {
        new LevelBuilder().setMemoryColumns(-2);
    }

    @Test(expected = IllegalStateException.class)
    public void testNullExceptionOnAllowed() {
        new LevelBuilder().setNumber(1).addToSolution(new Input()).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testNullExceptionOnSolution() {
        new LevelBuilder().setNumber(1).addAllowedInstruction(Output.class).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongProgressiveNumber() {
        new LevelBuilder().setNumber(-5);
    }
}
