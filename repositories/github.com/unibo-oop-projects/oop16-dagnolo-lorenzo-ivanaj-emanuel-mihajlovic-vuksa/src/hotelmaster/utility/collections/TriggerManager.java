package hotelmaster.utility.collections;

/**
 * A manager for triggers.
 * 
 * @param <E>
 *            the type of the elements for the consumer.
 */
public interface TriggerManager<E> {

    /**
     * Adds the given trigger to this manager.
     * 
     * @param trigger
     *            the trigger to add
     */
    void add(Trigger<E> trigger);

    /**
     * Executes all of the triggers associated with the given
     * {@link TriggeringOperation}.
     * 
     * @param operation
     *            the operation to trigger
     * @param element
     *            the element to be consumed
     */
    void execute(TriggeringOperation operation, E element);

    /**
     * Instances a new TriggerManager.
     * 
     * @param <E>
     *            the type of the elements for the consumer
     * @return the new instance
     */
    static <E> TriggerManager<E> create() {
        return new TriggerManagerImpl<>();
    }
}
