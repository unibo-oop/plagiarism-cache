package clashclass.ai.behaviourtree.blackboard;

/**
 * Represents a {@link BlackboardProperty} implementation.
 *
 * @param <T> the type of the property
 */
public class BlackboardPropertyImpl<T> implements BlackboardProperty<T> {
    private T value;
    private final Class<T> type;

    /**
     * Constructs the blackboard property.
     *
     * @param value the value of the property
     * @param type the type of the property
     */
    public BlackboardPropertyImpl(final T value, final Class<T> type) {
        this.value = value;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(final T value) {
       this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }
}
