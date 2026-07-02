package todo.vm;

import java.util.Objects;
import java.util.Optional;

import todo.utils.Checks;

/**
 * This class represents an action executed by the VM. It can be used to then
 * animate that action.
 */
public final class Action {
    private final ActionKind kind;
    private final Optional<Integer> memoryAddress;

    private Action(final ActionKind kind, final Integer memoryAddress) {
        this.kind = kind;
        this.memoryAddress = Optional.of(memoryAddress);
    }

    private Action(final ActionKind kind) {
        this.kind = kind;
        this.memoryAddress = Optional.empty();
    }

    /**
     * @return an Action representing nothing
     */
    public static Action none() {
        return new Action(ActionKind.NONE);
    }

    /**
     * @return an Action representing a value being picked up from the input
     */
    public static Action pickInput() {
        return new Action(ActionKind.PICK_INPUT);
    }

    /**
     * @return an Action representing a value being dropped on the output
     */
    public static Action dropOutput() {
        return new Action(ActionKind.DROP_OUTPUT);
    }

    /**
     * @return an Action representing the currently held value being dropped
     */
    public static Action dropMainHand() {
        return new Action(ActionKind.DROP_MAIN_HAND);
    }

    /**
     * @param address the index of the memory address to be located
     * @return an Action representing a memory address being located
     */
    public static Action locateMemoryAddress(final int address) {
        return new Action(ActionKind.LOCATE_MEMORY_ADDRESS, address);
    }

    /**
     * @param address the index of the memory address whose content has to be copied
     * @return an Action representing a memory address being copied to the hand
     */
    public static Action copyFrom(final int address) {
        return new Action(ActionKind.COPY_FROM, address);
    }

    /**
     * @param address the index of the memory address where to copy
     * @return an Action representing the hand being copied to the memory address
     */
    public static Action copyTo(final int address) {
        return new Action(ActionKind.COPY_TO, address);
    }

    /**
     * @param address the index of the memory address whose content has to be added
     * @return an Action representing adding the memory address to the hand
     */
    public static Action add(final int address) {
        return new Action(ActionKind.ADD, address);
    }

    /**
     * @param address the index of the memory address whose content has to be subbed
     * @return an Action representing subtracting the memory address from the hand
     */
    public static Action sub(final int address) {
        return new Action(ActionKind.SUB, address);
    }

    /**
     * @param address the index of the memory address whose content has to be
     *            increased
     * @return an Action representing incrementing the content of a memory address
     */
    public static Action incr(final int address) {
        return new Action(ActionKind.INCR, address);
    }

    /**
     * @param address the index of the memory address whose content has to be
     *            decreased
     * @return an Action representing decrementing the content of a memory address
     */
    public static Action decr(final int address) {
        return new Action(ActionKind.DECR, address);
    }

    /**
     * @return the ActionKind representing the action
     */
    public ActionKind getKind() {
        return this.kind;
    }

    /**
     * @return the memory address associated with this action
     * @throws IllegalStateException if the action doesn't carry a memory address
     */
    public int getMemoryAddress() {
        Checks.require(this.memoryAddress.isPresent(), IllegalStateException.class,
                "the action " + toString() + " doesn't have a memory address");
        return this.memoryAddress.get();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Action && ((Action) other).kind.equals(this.kind)
                && ((Action) other).memoryAddress.equals(this.memoryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.kind, this.memoryAddress);
    }

    @Override
    public String toString() {
        if (this.memoryAddress.isPresent()) {
            return this.kind.name() + "(" + this.memoryAddress.get() + ")";
        } else {
            return this.kind.name();
        }
    }
}
