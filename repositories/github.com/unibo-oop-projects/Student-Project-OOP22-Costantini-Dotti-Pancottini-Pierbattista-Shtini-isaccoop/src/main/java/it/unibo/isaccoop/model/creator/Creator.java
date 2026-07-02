package it.unibo.isaccoop.model.creator;

import java.util.List;

/**
 * Functional interface that manages the creation of items and enemies.
 *
 * @param <E> type of {@link Creator}
 * */
public interface Creator<E> {
    /**
     * Method to create a {@link List} of <E> type objects.
     *
     * @return {@link List} of <E> type with created objects
     * */
    List<E> create();
}
