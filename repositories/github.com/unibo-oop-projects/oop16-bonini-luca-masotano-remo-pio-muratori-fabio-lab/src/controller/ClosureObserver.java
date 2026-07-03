package controller;

import controller.event.Event;

/**
 * Observer used to catch the application closure event. The specific event
 * handled is {@link controller.event.StandardEvent}
 */
public class ClosureObserver implements Observer {

    @Override
    public <E extends Event> void onNotify(final E e) {
        if (e.getMessage().equals("CLOSE_REQUEST")) {
            ControllerImpl.get().exit();
        }

    }

}
