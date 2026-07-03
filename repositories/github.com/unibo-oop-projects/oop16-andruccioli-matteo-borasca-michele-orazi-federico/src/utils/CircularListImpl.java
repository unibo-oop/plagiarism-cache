package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * Implementation of the interface CircularList<E>.
 *
 * @param <E>
 *            The type of the elements contained in the list.
 */
public class CircularListImpl<E> extends ArrayList<E> implements CircularList<E> {

    /**
     * 
     */
    private static final long serialVersionUID = -3108274240646179285L;

    private int head;

    /**
     * The constructor of the circular list.
     * 
     * @param list
     *            The list to convert to circular list.
     */
    public CircularListImpl(final List<E> list) {
        super(list);
        this.head = 0;
    }

    @Override
    public void shift() {
        if (this.head == super.size() - 1) {
            this.head = 0;
        } else {
            this.head++;
        }
    }

    @Override
    public E getHead() {
        return super.get(head);
    }

    @Override
    public E get(final int index) {
        if (index < super.size()) {
            return super.get(index);
        }
        return null;
    }

    @Override
    public void setHead(final int pos) {
        if (pos < super.size()) {
            this.head = pos;
        }
    }

    @Override
    public boolean remove(final Object elem) {
        if (super.contains(elem)) {
            if (indexOf(elem) <= this.head && this.head != 0) {
                this.head--;
            }
            super.remove(elem);
        }
        return false;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < super.size(); i++) {
            if (i == this.head) {
                temp += "->";
            } 
            temp += super.get(i).toString();
            temp += ", ";
        }
        return temp;
    }

    @Override
    public Stream<E> toStream() {
        return super.stream();
    }

}
