package clashclass.ai.behaviourtree.blackboard;

/**
 * Represents a property in a {@link Blackboard}.
 *
 * @param <T> the type of the property
 */
public interface BlackboardProperty<T> {
    /**
     * Gets the property's value.
     *
     * @return the property's value
     */
    T getValue();

    /**
     * Sets the property's value.
     *
     * @param value the property's new value
     */
    void setValue(T value);

    /**
     * Gets the property's type.
     *
     * @return the property's type
     */
    Class<T> getType();
}
