package controller.observer;

import controller.GameEngineImpl;
import controller.event.Event;
import controller.event.KeyEvent;
import controller.event.KeyType;
import javafx.scene.input.KeyCode;
import utility.Command;
import view.utility.SceneType;

/**
 * Observer that manages the pressure of the keys on the keyboard.
 */
public class KeyObserver implements Observer {

    /**
     * Manage KeyEvent events.
     */
    @Override
    public <E extends Event> void notifyEvent(final E event) {
        if (event instanceof KeyEvent) { 
            final KeyEvent keyEvent = (KeyEvent) event;
            if (compare(keyEvent, KeyCode.ESCAPE.getName(), SceneType.GAME, KeyType.KEY_PRESSED)) {
                GameEngineImpl.get().stopGame();
            } else if (compare(keyEvent, KeyCode.ESCAPE.getName(), SceneType.PAUSE, KeyType.KEY_PRESSED)) {
                GameEngineImpl.get().resumeGameLoop();
            }
            if (keyEvent.getType().equals(KeyType.KEY_PRESSED)) {
                key(keyEvent.getEvent(), true);
            } else if (keyEvent.getType().equals(KeyType.KEY_RELEASED)) {
                key(keyEvent.getEvent(), false);
            }
        }
    }

    /**
     * Compare the event with the other argument.
     * @param event that happened.
     * @param key that must be compared.
     * @param scene that must be compared.
     * @param type that must be compared
     * @return true if the comparison between the event and the parameters is right, false otherwise.
     */
    private boolean compare(final KeyEvent event, final String key, final SceneType scene, final KeyType type) {
        return event.getEvent().equals(key) && event.getGameState().equals(scene) && event.getType().equals(type);
    }

    /**
     * Adds a command to the list of commands if the key has been pressed, remove it from the list if the button has been released.
     * @param key to check.
     * @param pressed is true if the key has been pressed, false if the key has been released.
     */
    private void key(final String key, final boolean pressed) {
        switch (key) {
        case "W":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addMovement(Command.UP);
            } else {
                GameEngineImpl.get().getGameLoop().removeMovement(Command.UP);
            }
            break;
        case "A":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addMovement(Command.LEFT);
            } else {
                GameEngineImpl.get().getGameLoop().removeMovement(Command.LEFT);
                KeyCode.UP.getName();
            }
            break;
        case "S":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addMovement(Command.DOWN);
            } else {
                GameEngineImpl.get().getGameLoop().removeMovement(Command.DOWN);
            }
            break;
        case "D":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addMovement(Command.RIGHT);
            } else {
                GameEngineImpl.get().getGameLoop().removeMovement(Command.RIGHT);
            }
            break;
        case "Up":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addShot(Command.UP);
            } else {
                GameEngineImpl.get().getGameLoop().removeShot(Command.UP);
            }
            break;
        case "Left":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addShot(Command.LEFT);
            } else {
                GameEngineImpl.get().getGameLoop().removeShot(Command.LEFT);
            }
            break;
        case "Down":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addShot(Command.DOWN);
            } else {
                GameEngineImpl.get().getGameLoop().removeShot(Command.DOWN);
            }
            break;
        case "Right":
            if (pressed) {
                GameEngineImpl.get().getGameLoop().addShot(Command.RIGHT);
            } else {
                GameEngineImpl.get().getGameLoop().removeShot(Command.RIGHT);
            }
            break;
        default:
            break;
        }
    }
}
