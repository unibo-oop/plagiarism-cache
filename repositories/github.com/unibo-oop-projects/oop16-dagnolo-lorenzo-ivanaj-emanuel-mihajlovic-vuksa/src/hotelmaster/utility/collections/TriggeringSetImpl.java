package hotelmaster.utility.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A {@link HashSet}-like set with {@link Triggering} properties.
 * 
 * @param <E>
 *            the type of the elements
 */
public class TriggeringSetImpl<E> implements TriggeringSet<E> {

    private final Map<E, Object> elements;
    private static final Object BLANK = new Object();
    private final TriggerManager<E> triggers;

    TriggeringSetImpl() {
        elements = new HashMap<>();
        triggers = TriggerManager.create();
    }

    TriggeringSetImpl(final Collection<? extends E> collection) {
        this();
        for (final E elem : collection) {
            this.elements.put(elem, BLANK);
        }
    }

    @Override
    public void addTrigger(final Trigger<E> trigger) throws IllegalArgumentException {
        switch (trigger.getOperation()) {
        case ADD:
        case REMOVE:
            triggers.add(trigger);
            break;
        default:
            throw new IllegalArgumentException("Unsupported TriggeringOperation");
        }
    }

    @Override
    public boolean add(final E element) {
        if (this.elements.putIfAbsent(element, BLANK) == null) {
            triggers.execute(TriggeringOperation.ADD, element);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        if (this.elements.keySet().stream().anyMatch(elem -> collection.contains(elem))) {
            return false;
        }
        for (final E element : collection) {
            this.add(element);
        }
        return true;
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public boolean contains(final Object element) {
        return this.elements.containsKey(element);
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        return this.elements.keySet().containsAll(collection);
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.elements.keySet().iterator();
    }

    @SuppressWarnings("unchecked") // safe cast since the object was in the
                                   // Set<E> keySet
    @Override
    public boolean remove(final Object element) {
        if (this.elements.remove(element) != null) {
            this.triggers.execute(TriggeringOperation.REMOVE, (E) element);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        boolean modified = false;
        for (final Object element : collection) {
            if (this.remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(final Collection<?> collection) {
        return this.removeAll(
                this.elements.values().stream().filter(elem -> !collection.contains(elem)).collect(Collectors.toSet()));
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public Object[] toArray() {
        return this.elements.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        return this.elements.keySet().toArray(array);
    }

    @Override
    public String toString() {
        return this.elements.keySet().toString();
    }

}
