package todo.view.drawables.level.ui;

/**
 * This interface represents an instruction actor that has a context in the
 * program and has an address.
 */
interface AddressableProgramInstructionActor extends ProgramInstructionActor {
    /**
     * @return the specified memory address
     */
    int getMemoryAddress();

    /**
     * Se the address to the specified one.
     *
     * @param address is the new memory address
     */
    void setMemoryAddress(int address);
}
