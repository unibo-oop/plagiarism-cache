package rogue.model.events;

/**
 * An interface which allows to publish events.
 */
public interface EventPublisher {

    /**
     * Posts a new event.
     * @param event
     *          the {@link Event} to publish
     */
    void post(Event event);

    /**
     * Registers the given subscriber on {@link EventBus}.
     * @param subscriber
     *          the {@link EventSubscriber} to register
     */
    void register(EventSubscriber subscriber);

    /**
     * Unregisters the given subscriber on {@link EventBus}.
     * @param subscriber
     *          the {@link EventSubscriber} to unregister
     */
    void unregister(EventSubscriber subscriber);

}
