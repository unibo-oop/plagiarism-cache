package model.entity.cell.standard.genome;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 *a cool new linked list with a a circular implementations.
 *
 *WARNING only get and set for now;
 *
 * @param <E>
 */
public class CircularLinkedList<E> extends LinkedList<E> implements List<E> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public final E get(final int index) {
        return super.get(this.round(index));
    }

    @Override
    public final E set(final int index, final E element) {
        return super.set(this.round(index), element);
    }

    private int round(final int index) {
        int count = index;
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        while (count < 0) {
            count = this.size() + count;
        }
        return count % size();
    }
}
