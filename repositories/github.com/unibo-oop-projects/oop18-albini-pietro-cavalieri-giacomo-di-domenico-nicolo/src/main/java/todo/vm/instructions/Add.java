package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The add instruction adds the values of the hand and the provided memory
 * address, storing the result in the hand.
 */
public class Add extends BaseInstruction implements AddressableInstruction {
    private final int memoryAddress;

    public Add(final int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroResolveMemoryAddress(this.memoryAddress));
        builder.add(new MicroAdd());
    }

    @Override
    public int getMemoryAddress() {
        return this.memoryAddress;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Add && ((Add) other).memoryAddress == this.memoryAddress;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "add " + this.memoryAddress;
    }
}
