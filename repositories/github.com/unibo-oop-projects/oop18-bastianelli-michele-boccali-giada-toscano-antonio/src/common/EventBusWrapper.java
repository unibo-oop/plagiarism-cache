package common;

import com.google.common.eventbus.EventBus;

/**
 * Create a singleton EventBus.
 */
public final class EventBusWrapper {

    private final EventBus bus;

    private static final EventBusWrapper INSTANCE = new EventBusWrapper();

    private EventBusWrapper() {
        bus = new EventBus();
    }

    /**
     * Return the singleton instance of the EventBusWrapper.
     * 
     * @return the singleton instance of the EventBusWrapper.
     */
    public static EventBusWrapper getInstance() {
        return INSTANCE;
    }

    /**
     * Return the EventBus created by the EventBusWrapper.
     * 
     * @return the EventBus instance.
     */
    public EventBus getBus() {
        return this.bus;
    }
}
