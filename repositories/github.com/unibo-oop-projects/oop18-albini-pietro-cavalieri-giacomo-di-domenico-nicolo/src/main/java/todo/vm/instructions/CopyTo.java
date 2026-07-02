package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The copyto instruction copies the value from the hand to the specified memory
 * address.
 */
public class CopyTo extends BaseInstruction implements AddressableInstruction {
    private final int memoryAddress;

    public CopyTo(final int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroResolveMemoryAddress(this.memoryAddress));
        builder.add(new MicroCopyTo());
    }

    @Override
    public int getMemoryAddress() {
        return this.memoryAddress;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof CopyTo && ((CopyTo) other).memoryAddress == this.memoryAddress;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "copyto " + this.memoryAddress;
    }
}
