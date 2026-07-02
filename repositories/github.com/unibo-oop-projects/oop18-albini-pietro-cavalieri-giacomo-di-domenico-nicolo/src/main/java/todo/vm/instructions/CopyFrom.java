package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The copyfrom instruction copies the value from the specified memory address
 * to the hand.
 */
public class CopyFrom extends BaseInstruction implements AddressableInstruction {
    private final int memoryAddress;

    public CopyFrom(final int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroDropMainHand());
        builder.add(new MicroResolveMemoryAddress(this.memoryAddress));
        builder.add(new MicroCopyFrom());
    }

    @Override
    public int getMemoryAddress() {
        return this.memoryAddress;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof CopyFrom && ((CopyFrom) other).memoryAddress == this.memoryAddress;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "copyfrom " + this.memoryAddress;
    }
}
