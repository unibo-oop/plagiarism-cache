package common;

import com.google.common.eventbus.EventBus;

/**
 * A class that easily allows to be register on the main EventBusWrapper.
 */
public class EventBusConnection {

    /**
     * Public constructor of a connection on the event bus.
     */
    public EventBusConnection() {
        init();
    }

    private void init() {
        EventBusWrapper.getInstance().getBus().register(this);
    }

    /**
     * Return the instance of the EventBus.
     * 
     * @return the EventBus
     */
    public EventBus getBus() {
        return EventBusWrapper.getInstance().getBus();
    }

    /**
     * Register the class on the main bus.
     */
    public void registerOnBus() {
        init();
    }

    /**
     * Unregister the class on the main bus.
     */
    public void unregister() {
        EventBusWrapper.getInstance().getBus().unregister(this);
    }
}
