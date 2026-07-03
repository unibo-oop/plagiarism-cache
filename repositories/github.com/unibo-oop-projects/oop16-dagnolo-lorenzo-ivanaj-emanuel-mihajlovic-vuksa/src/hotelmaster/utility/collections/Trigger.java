package hotelmaster.utility.collections;

import java.util.function.Consumer;

/**
 * A trigger, consisting of a {@link TriggeringOperation} and a
 * {@link Consumer}.
 * 
 * @param <E>
 *            the type of the consumer
 */
public interface Trigger<E> {

    /**
     * Returns the operation of this trigger.
     * 
     * @return the operation
     */
    TriggeringOperation getOperation();

    /**
     * Executes this trigger.
     * 
     * @param element
     *            the element that will be consumed
     */
    void execute(E element);

    /**
     * Creates a new trigger with the given operation and consumer.
     * 
     * @param <E>
     *            the type of the consumer
     * @param operation
     *            the operation which will trigger this trigger
     * @param consumer
     *            the consumer which will be run on the trigger
     * @return the new {@link Trigger}
     */
    static <E> Trigger<E> create(final TriggeringOperation operation, final Consumer<E> consumer) {
        return new TriggerImpl<>(operation, consumer);
    }
}
