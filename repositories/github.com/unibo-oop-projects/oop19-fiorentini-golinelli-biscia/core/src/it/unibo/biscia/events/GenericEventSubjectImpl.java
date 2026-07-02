package it.unibo.biscia.events;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Implements a simple notifier of events.
 * 
 * @param <T> object type to consume on notify
 *
 */
public final class GenericEventSubjectImpl<T> implements GenericEventSubject<T> {
    private final Set<T> observers = new HashSet<>();

    @Override
    public void attach(final T observer) {
        // ensure observers unique access
        synchronized (this.observers) {
            observers.add(observer);
        }

    }

    @Override
    public void detach(final T observer) {
        // ensure observers unique access
        synchronized (this.observers) {
            observers.remove(observer);
        }
    }

    @Override
    public void notify(final Consumer<T> event) {
        final Set<T> ob;
        // copy of observers list, all observers at start receive notification
        synchronized (this.observers) {
            ob = new HashSet<>(this.observers);
        }
        for (final T t : ob) {
            try {
                event.accept(t);
            } catch (UnsupportedOperationException uoe) {
                uoe.printStackTrace();
            }
        }

    }
}
