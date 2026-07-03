package hotelmaster.utility.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.TreeMultimap;

/**
 * A TypeMap implemented with guava's SetMultimap.
 * 
 * @param <E>
 *            the upper bound type
 */
public class TypeSetMapImpl<E> implements TypeSetMap<E> {

    private final SetMultimap<Class<? extends E>, ? extends E> elements;

    TypeSetMapImpl() {
        elements = TreeMultimap.create(Ordering.arbitrary(), Ordering.natural());
    }

    @Override
    public boolean add(final E element) {
        if (this.elements.containsValue(element)) {
            return false;
        }
        return this.innerGet(element.getClass()).add(element);
    }

    @Override
    public boolean addAll(final Collection<? extends E> elements) {
        boolean modified = false;
        for (final E element : elements) {
            if (this.add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean remove(final Object element) {
        return this.innerGet(element.getClass()).remove(element);
    }

    @Override
    public boolean contains(final Object element) {
        return this.innerGet(element.getClass()).contains(element);
    }

    @Override
    public <T extends E> Set<T> get(final Class<T> elementType) {
        return this.innerGet(elementType);
    }

    @Override
    public <T extends E> Set<T> getOfInstance(final T element) {
        return this.innerGet(element.getClass());
    }

    @Override
    public Collection<E> getAll() {
        return this.elements.values().stream().map(elem -> (E) elem).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private <T extends E, S extends Set<T>> S innerGet(final Object elementType) {
        return (S) elements.get((Class<E>) elementType);
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        return this.elements.values().containsAll(collection);
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return elements.values().stream().map(elem -> (E) elem).iterator();
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
        return this.removeAll(this.elements.values().stream().filter(elem -> !collection.contains(elem))
                .collect(Collectors.toList()));
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public Object[] toArray() {
        return this.elements.values().toArray();
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        return this.elements.values().toArray(array);
    }

}
