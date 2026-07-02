package view.inputhandler;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.input.KeyEvent;

/**
 * class KeyPressedManager manages key input.
 * it uses a Map of {@link Action} and Booleans and it sets a particular Action's boolean to true 
 * if the Action's corresponding {@link KeyCode} is pressed.
 */
public class KeyPressedManager {
    private final ConcurrentMap<Action, Boolean> actions;

    /**
     * it sets every boolean in map to false.
     */
    public KeyPressedManager() {
        this.actions = Stream.of(Action.values()).collect(Collectors.toConcurrentMap(Function.identity(), e -> false));
    }

    /**
     * in case of a {@link KeyEvent} key pressed, 
     * the method press gets the desired {@link Action} from the given KeyEvent's {@link KeyCode},
     * and sets (in the map) the Action's value to true.
     * @param e KeyEvent. 
     */
    public void press(final KeyEvent e) {
        final Action action = Action.getActionFromKeyCode(Objects.requireNonNull(e).getCode());
        if (Objects.nonNull(action)) {
            this.actions.put(action, true);
        }
    }

    /**
     * in case of a {@link KeyEvent} key released, 
     * the method release gets the desired {@link Action} from the given KeyEvent's {@link KeyCode},
     * and sets (in the map) the Action's value to false.
     * @param e KeyEvent. 
     */
    public void release(final KeyEvent e) {
        final Action action = Action.getActionFromKeyCode(Objects.requireNonNull(e).getCode());
        if (Objects.nonNull(action)) {
            this.actions.put(action, false);
        }
    }

    /**
     * method getActions returns the list of Actions that the user has the Hero do.
     * they are all the actions corresponding to the keys the user has clicked, in other words,
     * map's keys who's values are true.
     * @return List<Action>
     */
    public List<Action> getActions() {
        return this.actions.entrySet().stream().filter(e -> e.getValue()).map(e -> e.getKey())
                .collect(Collectors.toList());
    }
}
