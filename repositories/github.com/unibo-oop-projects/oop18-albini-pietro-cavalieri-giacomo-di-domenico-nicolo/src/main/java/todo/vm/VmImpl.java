package todo.vm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import todo.utils.Checks;
import todo.utils.UniqueId;
import todo.vm.exceptions.EmptyValueInOutputException;
import todo.vm.exceptions.InfiniteLoopException;
import todo.vm.exceptions.InvalidMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;
import todo.vm.instructions.Instruction;

public class VmImpl implements Vm {
    private static final int LOOP_DETECTION_TRESHOLD = 1000000;
    private final VmCode code;
    private final State state;

    public VmImpl(final List<Instruction> program, final List<Value> input, final List<Value> memoryAddresses) {
        final VmCodeImpl code = new VmCodeImpl();
        program.forEach(code::addInstruction);
        this.code = code;
        this.state = new State(new ArrayDeque<>(Objects.requireNonNull(input)), memoryAddresses);
    }

    @Override
    public void execute() throws VmRuntimeException {
        while (!hasFinished()) {
            step();
        }
    }

    @Override
    public Action step() throws VmRuntimeException {
        int loopCount = 0;
        while (!hasFinished()) {
            final int idx = programCounter();
            final UniqueId instrId = this.code.getInstructionId(idx);
            if (!instrId.equals(this.state.lastExecutedInstruction)) {
                this.state.lastExecutedInstruction = instrId;
                this.state.executedInstructionsCount++;
            }
            // The program counter is incremented before executing the instruction because
            // the instruction can override it (for example with a jump).
            this.state.setRegister(Register.PROGRAM_COUNTER, Value.number(idx + 1));
            final Action result = this.code.get(idx).execute(this.state, this.code);
            if (result.getKind() != ActionKind.NONE) {
                return result;
            }
            // Detect infinite loops. This detection method is not perfect, but it should
            // work perfectly fine for the programs executed in the game.
            loopCount++;
            if (loopCount > LOOP_DETECTION_TRESHOLD) {
                throw new InfiniteLoopException();
            }
        }
        return Action.none();
    }

    @Override
    public List<Value> getOutput() {
        return Collections.unmodifiableList(this.state.output);
    }

    @Override
    public boolean hasFinished() {
        return this.state.shouldEnd || programCounter() >= this.code.size();
    }

    @Override
    public UniqueId getNextInstructionId() {
        return this.code.getInstructionId(programCounter());
    }

    @Override
    public int getExecutedInstructionsCount() {
        return this.state.executedInstructionsCount;
    }

    @Override
    public Value getMemoryAddressContent(final int memoryAddress) {
        Checks.require(memoryAddress < this.state.memoryAddresses.size(), NoSuchElementException.class);
        Checks.require(memoryAddress >= 0, NoSuchElementException.class);
        return this.state.memoryAddresses.get(memoryAddress);
    }

    @Override
    public Value getMainHandContent() {
        return this.state.getRegister(Register.MAIN_HAND);
    }

    @Override
    public boolean isAtInstructionBoundary() {
        return hasFinished() || this.code.getInstructionId(programCounter()) != this.state.lastExecutedInstruction;
    }

    private int programCounter() {
        final Value register = this.state.getRegister(Register.PROGRAM_COUNTER);
        return register.isPresent() ? register.asNumber() : 0;
    }

    private final class State implements VmState {
        private boolean shouldEnd;
        private final EnumMap<Register, Value> registers;
        private final List<Value> memoryAddresses;
        private final Deque<Value> input;
        private final List<Value> output;
        private UniqueId lastExecutedInstruction;
        private int executedInstructionsCount;

        private State(final Deque<Value> input, final List<Value> memoryAddresses) {
            this.shouldEnd = false;
            this.registers = new EnumMap<>(Register.class);
            this.memoryAddresses = new ArrayList<>(Objects.requireNonNull(memoryAddresses));
            this.input = input;
            this.output = new ArrayList<>();
            this.lastExecutedInstruction = null;
            this.executedInstructionsCount = 0;
        }

        @Override
        public Value getRegister(final Register register) {
            return this.registers.containsKey(register) ? this.registers.get(register) : Value.empty();
        }

        @Override
        public void setRegister(final Register register, final Value value) {
            this.registers.put(register, Objects.requireNonNull(value));
        }

        @Override
        public Value getMemoryAddress(final int address) throws VmRuntimeException {
            return this.memoryAddresses.get(validateMemoryAddress(address));
        }

        @Override
        public void setMemoryAddress(final int address, final Value value) throws VmRuntimeException {
            this.memoryAddresses.set(validateMemoryAddress(address), Objects.requireNonNull(value));
        }

        @Override
        public boolean memoryAddressExists(final int address) {
            return address >= 0 && address < this.memoryAddresses.size();
        }

        private int validateMemoryAddress(final int address) throws VmRuntimeException {
            Checks.require(memoryAddressExists(address), InvalidMemoryAddressException.class);
            return address;
        }

        @Override
        public Value getInput() {
            return this.input.size() > 0 ? this.input.removeFirst() : Value.empty();
        }

        @Override
        public void addOutput(final Value value) throws VmRuntimeException {
            Checks.require(Objects.requireNonNull(value).isPresent(), EmptyValueInOutputException.class);
            this.output.add(value);
        }

        @Override
        public void endProgram() {
            this.shouldEnd = true;
        }
    }
}
