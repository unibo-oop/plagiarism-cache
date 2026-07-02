package todo.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import todo.vm.exceptions.EmptyValueInOutputException;
import todo.vm.exceptions.InfiniteLoopException;
import todo.vm.exceptions.InvalidMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;
import todo.vm.instructions.DummyInstruction;
import todo.vm.instructions.Input;
import todo.vm.instructions.Jump;
import todo.vm.instructions.MicroInstruction;
import todo.vm.instructions.Output;

public class VmTest {
    @Test
    public void testStepByStep() throws VmRuntimeException {
        final Vm vm = presetSimpleIO();
        for (final Action action : Arrays.asList(Action.pickInput(), Action.dropOutput())) {
            assertFalse(vm.hasFinished());
            assertEquals(action, vm.step());
        }
        assertTrue(vm.hasFinished());
        assertEquals(Action.none(), vm.step());
        assertEquals(Arrays.asList(Value.number(1)), vm.getOutput());
    }

    @Test
    public void testExecute() throws VmRuntimeException {
        final Vm vm = presetSimpleIO();
        vm.execute();
        assertTrue(vm.hasFinished());
        assertEquals(Arrays.asList(Value.number(1)), vm.getOutput());
    }

    @Test
    public void testHasFinishedWhenInstructionsEnd() throws VmRuntimeException {
        testHasFinished((state, code) -> {
            state.setRegister(Register.PROGRAM_COUNTER, Value.number(1000));
            return Action.pickInput();
        });
    }

    @Test
    public void testHasFinishedWhenEndProgramIsCalled() throws VmRuntimeException {
        testHasFinished((state, code) -> {
            state.endProgram();
            return Action.pickInput();
        });
    }

    private void testHasFinished(final MicroInstruction micro) throws VmRuntimeException {
        final Vm vm = presetDummy(micro, (state, code) -> Action.none());
        assertEquals(Action.pickInput(), vm.step());
        // There is an extra instruction after the custom one, so the execution
        // *shouldn't* be finished. The custom microinstruction executed earlier
        // overrides this though.
        assertTrue(vm.hasFinished());
    }

    @Test
    public void testStateManipulateRegisters() throws VmRuntimeException {
        presetDummy((state, code) -> {
            assertEquals(Value.empty(), state.getRegister(Register.MAIN_HAND));
            state.setRegister(Register.MAIN_HAND, Value.number(1));
            assertEquals(Value.number(1), state.getRegister(Register.MAIN_HAND));
            return Action.none();
        }).execute();
    }

    @Test(expected = NullPointerException.class)
    public void testStateSetNullRegister() throws VmRuntimeException {
        presetDummy((state, code) -> {
            state.setRegister(Register.MAIN_HAND, null);
            return Action.none();
        }).execute();
    }

    @Test
    public void testStateManipulateMemoryAddresses() throws VmRuntimeException {
        new VmImpl(Arrays.asList(new DummyInstruction((state, code) -> {
            assertEquals(Value.empty(), state.getMemoryAddress(0));
            state.setMemoryAddress(0, Value.number(42));
            assertEquals(Value.number(42), state.getMemoryAddress(0));
            return Action.none();
        })), Collections.emptyList(), Arrays.asList(Value.empty())).execute();
    }

    @Test(expected = InvalidMemoryAddressException.class)
    public void testStateInvalidMemoryAddress() throws VmRuntimeException {
        new VmImpl(Arrays.asList(new DummyInstruction((state, code) -> {
            state.setMemoryAddress(1, Value.number(42));
            return Action.none();
        })), Collections.emptyList(), Arrays.asList(Value.empty())).execute();
    }

    @Test(expected = NullPointerException.class)
    public void testStateSetNullMemoryAddress() throws VmRuntimeException {
        new VmImpl(Arrays.asList(new DummyInstruction((state, code) -> {
            state.setMemoryAddress(0, null);
            return Action.none();
        })), Collections.emptyList(), Arrays.asList(Value.empty())).execute();
    }

    @Test
    public void testStateGetInput() throws VmRuntimeException {
        new VmImpl(Arrays.asList(new DummyInstruction((state, code) -> {
            assertEquals(Value.number(1), state.getInput());
            assertEquals(Value.empty(), state.getInput());
            return Action.none();
        })), Arrays.asList(Value.number(1)), Collections.emptyList()).execute();
    }

    @Test
    public void testStateAddCorrectOutput() throws VmRuntimeException {
        testStateAddOutput(Value.number(1));
    }

    @Test(expected = EmptyValueInOutputException.class)
    public void testStateAddEmptyOutput() throws VmRuntimeException {
        testStateAddOutput(Value.empty());
    }

    private void testStateAddOutput(final Value value) throws VmRuntimeException {
        final Vm vm = presetDummy((state, code) -> {
            state.addOutput(value);
            return Action.none();
        });
        assertTrue(vm.getOutput().isEmpty());
        vm.execute();
        assertEquals(Arrays.asList(value), vm.getOutput());
    }

    @Test
    public void testLoop() throws VmRuntimeException {
        final List<Value> data = IntStream.range(0, 10).mapToObj(n -> Value.number(n)).collect(Collectors.toList());
        final Vm vm = presetLoop(data);
        vm.execute();
        assertTrue(vm.hasFinished());
        assertEquals(data, vm.getOutput());
    }

    @Test
    public void testJumpOutsideTheProgram() throws VmRuntimeException {
        final Jump jump = new Jump();
        final Vm vm = new VmImpl(Arrays.asList(jump, jump.getTarget()), Collections.emptyList(),
                Collections.emptyList());
        vm.execute();
        assertTrue(vm.hasFinished());
    }

    @Test
    public void testGetNextInstructionId() throws VmRuntimeException {
        final Input input = new Input();
        final Output output = new Output();
        final Jump jump = new Jump();
        final Vm vm = new VmImpl(Arrays.asList(jump.getTarget(), input, output, jump), Arrays.asList(Value.number(0)),
                Collections.emptyList());
        assertEquals(input.getId(), vm.getNextInstructionId());
        assertEquals(Action.pickInput(), vm.step());
        assertEquals(output.getId(), vm.getNextInstructionId());
        assertEquals(Action.dropOutput(), vm.step());
        assertEquals(jump.getId(), vm.getNextInstructionId());
    }

    @Test
    public void testExecutedInstructionsCount() throws VmRuntimeException {
        final Vm vm = presetLoop(Arrays.asList(Value.number(1), Value.number(2)));
        vm.execute();
        assertEquals(7, vm.getExecutedInstructionsCount());
    }

    @Test(expected = InfiniteLoopException.class)
    public void testInfiniteLoop() throws VmRuntimeException {
        final Jump jump = new Jump();
        final Vm vm = new VmImpl(Arrays.asList(jump.getTarget(), jump), Collections.emptyList(),
                Collections.emptyList());
        vm.execute();
    }

    @Test
    public void testInstructionBoundary() throws VmRuntimeException {
        final MicroInstruction micro = (s, c) -> Action.dropMainHand();
        final Vm vm = new VmImpl(Arrays.asList(new DummyInstruction(micro, micro), new DummyInstruction(micro),
                new DummyInstruction(micro, micro)), Collections.emptyList(), Collections.emptyList());
        assertEquals(Action.dropMainHand(), vm.step());
        assertFalse(vm.isAtInstructionBoundary());
        assertEquals(Action.dropMainHand(), vm.step());
        assertTrue(vm.isAtInstructionBoundary());
        assertEquals(Action.dropMainHand(), vm.step());
        assertTrue(vm.isAtInstructionBoundary());
        assertEquals(Action.dropMainHand(), vm.step());
        assertFalse(vm.isAtInstructionBoundary());
        assertEquals(Action.dropMainHand(), vm.step());
        assertTrue(vm.isAtInstructionBoundary());
    }

    private Vm presetSimpleIO() {
        return new VmImpl(Arrays.asList(new Input(), new Output()), Arrays.asList(Value.number(1)),
                Collections.emptyList());
    }

    private Vm presetLoop(final List<Value> data) {
        final Jump jump = new Jump();
        final Vm vm = new VmImpl(Arrays.asList(jump.getTarget(), new Input(), new Output(), jump), data,
                Collections.emptyList());
        return vm;
    }

    private Vm presetDummy(final MicroInstruction... micros) {
        return new VmImpl(Arrays.asList(new DummyInstruction(micros)), Collections.emptyList(),
                Collections.emptyList());
    }
}
