package todo.utils;

import java.util.Iterator;

/**
 * A peekable iterator is an iterator that also allows to "peek" the next value,
 * returning it without consuming it.
 * 
 * @param <T> the type of the elements of the iterator
 */
public interface PeekableIterator<T> extends Iterator<T> {
    /**
     * When this method is called the next element of the iterator will be returned
     * without being consumed: subsequent calls of the method will return the same
     * element until {@link #next()} is called. That {@link #next()} call will also
     * return the same element.
     *
     * @return the next element of the iterator
     */
    T peek();
}
