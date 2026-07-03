package hotelmaster.utility.collections;

import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of {@link TriggerManager}.
 * 
 * @param <E>
 *            the type of the consumers
 */
public class TriggerManagerImpl<E> implements TriggerManager<E> {

    private final Set<Trigger<E>> triggers;

    TriggerManagerImpl() {
        triggers = new HashSet<>();
    }

    @Override
    public void add(final Trigger<E> trigger) {
        triggers.add(trigger);
    }

    @Override
    public void execute(final TriggeringOperation operation, final E element) {
        triggers.stream().filter(trigger -> trigger.getOperation().equals(operation))
                .forEach(trigger -> trigger.execute(element));
    }

}
