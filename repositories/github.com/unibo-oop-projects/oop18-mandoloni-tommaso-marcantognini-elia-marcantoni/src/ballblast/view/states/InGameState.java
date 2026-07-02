package ballblast.view.states;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import ballblast.controller.Controller;
import ballblast.model.inputs.InputType;
import ballblast.settings.KeyCodeSet;
import ballblast.model.inputs.InputManager.PlayerTag;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Class for the in game state. It is the state which comes when the player is
 * interacting with the game.
 */
public class InGameState extends GUIState {
    private final Map<KeyCode, KeyCodeProcessor> pressedInputMap;
    private final Map<KeyCode, KeyCodeProcessor> releasedInputMap;

    /**
     * Initialize a new in game state.
     * 
     * @param gui        The GUISceneContrller.
     * @param controller The {@link Controller} of the game.
     */
    public InGameState(final GUISceneController gui, final Controller controller) {
        super(gui, controller);
        final KeyCodeSet keySet = KeyCodeSet.valueOf(controller.getCurrentUser().getKeySetting());
        pressedInputMap = ImmutableMap.of(
                keySet.getMoveLeft(),  () -> this.translateKeyCode(PlayerTag.FIRST, InputType.MOVE_LEFT), 
                keySet.getMoveRight(), () -> this.translateKeyCode(PlayerTag.FIRST, InputType.MOVE_RIGHT), 
                keySet.getShoot(),     () -> this.translateKeyCode(PlayerTag.FIRST, InputType.SHOOT), 
                KeyCode.P,             () -> this.translatePauseKeyCode(), 
                KeyCode.ESCAPE,        () -> this.translatePauseKeyCode());
        releasedInputMap = ImmutableMap.of(
                keySet.getMoveLeft(),  () -> this.translateKeyCode(PlayerTag.FIRST, InputType.STOP_MOVING_LEFT), 
                keySet.getMoveRight(), () -> this.translateKeyCode(PlayerTag.FIRST, InputType.STOP_MOVING_RIGHT), 
                keySet.getShoot(),     () -> this.translateKeyCode(PlayerTag.FIRST, InputType.STOP_SHOOTING));
    }

    @Override
    public void onStateEntry() {
    }

    @Override
    public final void onStateExit() {
        this.releasedInputMap.forEach((k, f) -> f.process());
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (this.pressedInputMap.containsKey(event.getCode())) {
            this.pressedInputMap.get(event.getCode()).process();
        }
    }

    @Override
    public final void onKeyReleased(final KeyEvent event) {
        if (this.releasedInputMap.containsKey(event.getCode())) {
            this.releasedInputMap.get(event.getCode()).process();
        }
    }

    private void translateKeyCode(final PlayerTag tag, final InputType input) {
        this.getController().receiveInput(tag, input);
    }

    private void translatePauseKeyCode() {
        this.getGUI().setState(this.getGUI().getPausedState());
    }

    /**
     * Functional interface used to translate a KeyCode in its corresponding action.
     */
    private interface KeyCodeProcessor {
        void process();
    }
}
