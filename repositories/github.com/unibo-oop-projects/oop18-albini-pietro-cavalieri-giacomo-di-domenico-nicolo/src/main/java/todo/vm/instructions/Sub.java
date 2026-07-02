package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The sub instruction subtracts the value in the memory address from the value
 * in the hand, storing the result in the hand.
 */
public class Sub extends BaseInstruction implements AddressableInstruction {
    private final int memoryAddress;

    public Sub(final int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroResolveMemoryAddress(this.memoryAddress));
        builder.add(new MicroSub());
    }

    @Override
    public int getMemoryAddress() {
        return this.memoryAddress;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Sub && ((Sub) other).memoryAddress == this.memoryAddress;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "sub " + this.memoryAddress;
    }
}
