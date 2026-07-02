package todo.model.level.inputs;

/**
 * This extension of {@link InputModifier} adds specific methods handling the
 * fact that this modifier has a count of elements to add.
 */
public interface ModifierWithCount extends InputModifier {
    /**
     * @param minCount the minimum count of elements to add
     */
    void setCount(int minCount);
}
