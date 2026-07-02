package it.unibo.arkanoid.event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents an event which accepts a {@link EventHandler} as an observer.
 * 
 * @param <T>
 *            The type of the argument sent to all {@link EventHandler} objects
 *            registered to this event.
 */
public final class EventSource<T> implements Event<T> {

    private final BlockingQueue<EventHandler<T>> registerHandler;

    /**
     * Creates a new Event object.
     */
    public EventSource() {
        this.registerHandler = new LinkedBlockingQueue<>();
    }

    @Override
    public void register(final EventHandler<T> handler) {
        this.registerHandler.add(handler);
    }

    @Override
    public void unregister(final EventHandler<T> handler) {
        this.registerHandler.remove(handler);
    }

    @Override
    public void trigger(final T arg) {
        this.registerHandler.forEach(h -> h.handle(arg));

    }
}
