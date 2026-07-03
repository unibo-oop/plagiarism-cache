package controller;

import controller.event.Event;
import controller.event.ButtonEvent;

/**
 * Observer used to catch a ButtonEvent. It handles the event based on the
 * message contained in the event. For this reason, ButtonEvent that cannot be
 * handled may be created.
 */
public class ButtonObserver implements Observer {

    @Override
    public <E extends Event> void onNotify(final E e) {
        if (e instanceof ButtonEvent) {
            final ButtonEvent event = (ButtonEvent) e;
            switch (event.getMessage()) {
            case "CONTINUE":
                ControllerImpl.get().resumeGameLoop();
                break;
            case "EXIT":
                ControllerImpl.get().exit();
                Runtime.getRuntime().exit(0);
                break;
            case "NEW":
                ControllerImpl.get().newGame();
                break;
            case "LOAD":
                ControllerImpl.get().loadGame();
            default:
                break;

            }
        }

    }

}
