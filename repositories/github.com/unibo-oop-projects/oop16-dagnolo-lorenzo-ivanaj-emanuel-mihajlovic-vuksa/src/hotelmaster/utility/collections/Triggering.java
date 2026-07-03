package hotelmaster.utility.collections;

import java.util.function.Consumer;

/**
 * An interface which executes all {@link Consumer}s on the given
 * {@link TriggeringOperation}.
 * 
 * @param <E>
 *            the type of the elements
 */
public interface Triggering<E> {

    /**
     * Adds the given action to the triggers, linking it to the operation.
     * Whenever an implementation performs a given {@link TriggeringOperation},
     * the relevant {@link Consumer}s should be called.
     * 
     * @param trigger
     *            the action to perform
     * @throws IllegalArgumentException
     *             the given {@link Trigger}'s {@link TriggeringOperation} is
     *             not supported by this implementation of Triggering
     */
    void addTrigger(Trigger<E> trigger) throws IllegalArgumentException;
}
