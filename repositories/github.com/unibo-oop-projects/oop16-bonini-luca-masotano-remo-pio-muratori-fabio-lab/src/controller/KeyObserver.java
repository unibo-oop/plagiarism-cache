package controller;

import controller.event.Event;
import controller.event.KeyEvent;
import model.utils.Direction;

/**
 * This observer handles the key events by the user and provided by the View.
 * {@link KeyEvent}
 */
public class KeyObserver implements Observer {

    @Override
    public <E extends Event> void onNotify(final E e) {
        if (e instanceof KeyEvent) {
            final KeyEvent event = (KeyEvent) e;
            if (e.getMessage().equals("ESCAPE") && (event.getState().equals("GAME"))
                    && event.getType().equals(KeyEvent.KeyEventType.KEY_RELEASED)) {

                ControllerImpl.get().stopGameLoop();

            } else if (e.getMessage().equals("ESCAPE") && (event.getState().equals("PAUSE"))
                    && event.getType().equals(KeyEvent.KeyEventType.KEY_RELEASED)) {
                ControllerImpl.get().resumeGameLoop();
            }
            if (event.getType().equals(KeyEvent.KeyEventType.KEY_PRESSED)) {
                addKey(event.getMessage());

            } else if (event.getType().equals(KeyEvent.KeyEventType.KEY_RELEASED)) {
                removeKey(event.getMessage());
            }
        }
    }

    private void addKey(final String e) {

        switch (e) {
        case "W":
            ControllerImpl.get().addMovements(Direction.UP);
            break;
        case "A":
            ControllerImpl.get().addMovements(Direction.LEFT);
            break;
        case "S":
            ControllerImpl.get().addMovements(Direction.DOWN);
            break;
        case "D":
            ControllerImpl.get().addMovements(Direction.RIGHT);
            break;
        case "UP":
            ControllerImpl.get().addShoots(Direction.UP);
            break;
        case "LEFT":
            ControllerImpl.get().addShoots(Direction.LEFT);
            break;
        case "DOWN":
            ControllerImpl.get().addShoots(Direction.DOWN);
            break;
        case "RIGHT":
            ControllerImpl.get().addShoots(Direction.RIGHT);
            break;
        default:
            break;
        }
    }

    private void removeKey(final String e) {

        switch (e) {
        case "W":
            ControllerImpl.get().removeMovements(Direction.UP);
            break;
        case "A":
            ControllerImpl.get().removeMovements(Direction.LEFT);
            break;
        case "S":
            ControllerImpl.get().removeMovements(Direction.DOWN);
            break;
        case "D":
            ControllerImpl.get().removeMovements(Direction.RIGHT);
            break;
        case "UP":
            ControllerImpl.get().removeShoots(Direction.UP);
            break;
        case "LEFT":
            ControllerImpl.get().removeShoots(Direction.LEFT);
            break;
        case "DOWN":
            ControllerImpl.get().removeShoots(Direction.DOWN);
            break;
        case "RIGHT":
            ControllerImpl.get().removeShoots(Direction.RIGHT);
            break;
        default:
            break;
        }
    }
}
