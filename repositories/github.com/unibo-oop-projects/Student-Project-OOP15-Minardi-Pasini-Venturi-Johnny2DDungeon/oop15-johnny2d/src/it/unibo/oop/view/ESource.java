package it.unibo.oop.view;

import java.util.function.Consumer;

/**
 * Interface for classes which generates events for some O-Observer.
 * 
 * @param <O>
 *            type of observer.
 */
public interface ESource<O> {

    /**
     * @param obs
     *            observer to attach.
     */
    void addObserver(O obs);

    /**
     * @param action
     *            to perform.
     */
    void doAction(Consumer<O> action);
}
