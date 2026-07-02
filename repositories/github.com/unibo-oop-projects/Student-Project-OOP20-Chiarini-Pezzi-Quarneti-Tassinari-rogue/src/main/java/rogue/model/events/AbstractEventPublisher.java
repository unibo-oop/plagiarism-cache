package rogue.model.events;

import com.google.common.eventbus.EventBus;

public abstract class AbstractEventPublisher implements EventPublisher {

    private final EventBus eventBus = new EventBus();

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(final Event event) {
        this.eventBus.post(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final EventSubscriber subscriber) {
        this.eventBus.register(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregister(final EventSubscriber subscriber) {
        this.eventBus.unregister(subscriber);
    }

}
