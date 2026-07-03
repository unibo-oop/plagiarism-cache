package com.jlearn.view.voice_recognition;

//Inspired by prof.Viroli's slides
/**
 * This interface models the concept of observer of the Observer pattern.
 *
 * @param <T>
 *            the param
 *
 */
public interface EObserver<T> {

    /**
     * Updates the observer status.
     *
     * @param src
     *            the source that notifies this observer.
     *
     * @param arg
     *            the notified status.
     */
    void update(ESource<? extends T> src, T arg);
}
