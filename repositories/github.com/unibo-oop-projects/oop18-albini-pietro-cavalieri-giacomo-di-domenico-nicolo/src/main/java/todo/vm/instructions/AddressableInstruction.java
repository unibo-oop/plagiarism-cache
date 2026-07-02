package todo.vm.instructions;

/**
 * Addressable instructions are instructions that act on a specified memory
 * address.
 */
public interface AddressableInstruction extends Instruction {
    /**
     * @return the address this instruction acts on
     */
    int getMemoryAddress();
}
