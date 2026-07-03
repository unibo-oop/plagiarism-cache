package utils;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * 
 * A Circular list of elements of type E. A circular list is
 * constructed from a {@link java.util.List}, when the end of the list is reached the
 * pointer returns to the beginning.
 * 
 * @param <E>
 *            the type of the elements contained in the list.
 * 
 */
public interface CircularList<E> extends Serializable, Iterable<E> {

    /**
     * Shifts all the list of one position, in case the end of the list is
     * reached the pointer return to the head.
     */
    void shift();

    /**
     * Method to place the head in a determined position.
     * 
     * @param pos
     *            The position where to place the head.
     */
    void setHead(int pos);

    /**
     * @return The element at the head of the list.
     */
    E getHead();

    /**
     * @return The size of the list.
     */
    int size();

    /**
     * @return True if the list is empty.
     */
    boolean isEmpty();

    /**
     * @param elem
     *            The element to search in the list.
     * @return True if the element is contained.
     */
    boolean contains(Object elem);

    /**
     * @param elem
     *            The element to remove in the list.
     * @return True if the element was effectively removed.
     */
    boolean remove(Object elem);

    /**
     * @return A stream of this object list.
     */
    Stream<E> toStream();

}
