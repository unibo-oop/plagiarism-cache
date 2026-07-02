package todo.vm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import todo.utils.Checks;
import todo.vm.exceptions.InvalidMemoryAddressException;
import todo.vm.exceptions.VmRuntimeException;

public class DummyVmState implements VmState {
    private final EnumMap<Register, Value> registers;
    private final List<Value> output;
    private List<Value> memoryAddresses;
    private Optional<Deque<Value>> input;
    private boolean running;

    public DummyVmState() {
        this.registers = new EnumMap<>(Register.class);
        this.output = new ArrayList<>();
        this.memoryAddresses = new ArrayList<>();
        this.input = Optional.empty();
        this.running = true;
    }

    public void setInput(final List<Value> input) {
        this.input = Optional.of(new ArrayDeque<Value>(input));
    }

    public void setMemoryAddresses(final List<Value> memoryAddresses) {
        this.memoryAddresses = Objects.requireNonNull(memoryAddresses);
    }

    public List<Value> getOutput() {
        return Collections.unmodifiableList(this.output);
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override
    public Value getRegister(final Register register) {
        return this.registers.containsKey(register) ? this.registers.get(register) : Value.empty();
    }

    @Override
    public void setRegister(final Register register, final Value value) {
        this.registers.put(register, value);
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
        if (this.input.isPresent() && this.input.get().size() > 0) {
            return this.input.get().removeFirst();
        } else {
            return Value.empty();
        }
    }

    @Override
    public void addOutput(final Value value) {
        this.output.add(value);
    }

    @Override
    public void endProgram() {
        this.running = false;
    }
}
