package hotelmaster.utility.collections;

import java.util.Collection;
import java.util.Set;

/**
 * A collection of elements which are sub-classes of the type T. For every
 * sub-class type, there may be no copies of elements.
 * 
 * @param <T>
 *            the upper bound type for the collection
 */
public interface TypeSetMap<T> extends Collection<T> {

    /**
     * Returns all the elements of a certain type in the collection.
     * 
     * @param <E>
     *            the type of the elements
     * 
     * @param elementType
     *            the type of the elements to be retrieved
     * @return an ordered set of elements of the given type
     */
    <E extends T> Set<E> get(Class<E> elementType);

    /**
     * Returns all the elements of a certain type in the collection, from an
     * instance of the element.
     * 
     * @param <E>
     *            the type of the elements
     * @param element
     *            the element of the type to be returned.
     * @return an ordered set of elements of the given type
     */
    <E extends T> Set<E> getOfInstance(E element);

    /**
     * Returns all the elements in this {@link TypeSetMap}.
     * 
     * @return the elements
     */
    Collection<T> getAll();

    /**
     * Instances a new TypeSetMap.
     * 
     * @param <T>
     *            the upper bound type
     * @return the new TypeSetMap
     */
    static <T> TypeSetMap<T> create() {
        return new TypeSetMapImpl<T>();
    }
}
