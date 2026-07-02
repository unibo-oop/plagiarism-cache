package todo.vm.instructions;

import todo.vm.MicrocodeBuilder;
import todo.vm.printer.PrinterState;

/**
 * The decr instruction decrements the content of the provided memory address,
 * copying the result in the hand.
 */
public class Decr extends BaseInstruction implements AddressableInstruction {
    private final int memoryAddress;

    public Decr(final int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    @Override
    public void buildMicrocode(final MicrocodeBuilder builder) {
        builder.add(new MicroDropMainHand());
        builder.add(new MicroResolveMemoryAddress(this.memoryAddress));
        builder.add(new MicroDecr());
        builder.add(new MicroCopyFrom());
    }

    @Override
    public int getMemoryAddress() {
        return this.memoryAddress;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Decr && ((Decr) other).memoryAddress == this.memoryAddress;
    }

    @Override
    public String generatePrintableString(final PrinterState state) {
        return "decr " + this.memoryAddress;
    }
}
