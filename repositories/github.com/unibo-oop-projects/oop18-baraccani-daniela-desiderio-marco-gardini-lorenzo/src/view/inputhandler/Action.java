package view.inputhandler;

import java.util.Optional;

import java.util.stream.Stream;
import javafx.scene.input.KeyCode;

/**
 * Enumeration of actions available to the player. it connects an action to its
 * respective KeyCode.
 */
public enum Action {
    /**
     * the player's available actions are JUMP, LEFT, RIGHT, SHOOT and PAUSE.
     */
    JUMP(KeyCode.UP), LEFT(KeyCode.LEFT), RIGHT(KeyCode.RIGHT), SHOOT(KeyCode.SPACE), PAUSE(KeyCode.P);

    private final KeyCode code;

    /**
     * method Action gets input code and associates it to the Action.
     * 
     * @param code
     */
    Action(final KeyCode code) {
        this.code = code;
    }

    /**
     * method getKeyCode, returns a particular Action's {@link KeyCode}.
     * 
     * @return code
     */
    public KeyCode getKeyCode() {
        return this.code;
    }

    /**
     * method getActionFromKeyCode given a {@link KeyCode} it returns the
     * corresponding Action.
     * 
     * @param code is the {@link KeyCode}
     * @return Action
     */
    public static Action getActionFromKeyCode(final KeyCode code) {
        final Optional<Action> action = Stream.of(Action.values()).filter(e -> e.getKeyCode().equals(code)).findAny();
        if (!action.isPresent()) {
            return null;
        }
        return action.get();
    }

}
