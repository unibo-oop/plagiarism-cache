package todo.model.level.inputs;

/**
 * This extension of {@link InputModifier} adds specific methods handling the
 * fact that this modifier has a bound for the elements to add.
 */
public interface ModifierWithBound extends InputModifier {
    /**
     * @param bound the maximum/minimum {@link Value} for the element to be added
     */
    void setBound(int bound);
}
