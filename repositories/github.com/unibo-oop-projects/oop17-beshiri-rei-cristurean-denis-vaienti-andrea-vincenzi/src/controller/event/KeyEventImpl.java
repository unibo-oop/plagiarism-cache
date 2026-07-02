package controller.event;

import javafx.scene.input.KeyCode;
import view.utility.SceneType;
/**
 *Event created by pressing a key.
 */
public class KeyEventImpl implements KeyEvent {

    private final KeyCode key;
    private final SceneType gameState;
    private final KeyType type;

    /**
     * The class constructor.
     * @param key was pressed.
     * @param gameState when the key was pressed.
     * @param keyType to specify if the button was pressed or released.
     */
    public KeyEventImpl(final KeyCode key, final SceneType gameState, final KeyType keyType) {
        this.key = key;
        this.gameState = gameState;
        this.type = keyType;
    }

    /**
     * Get the key pressed.
     */
    @Override
    public String getEvent() {
        return this.key.getName();
    }

    /**
     * Get the scene when the key was pressed.
     */
    @Override
    public SceneType getGameState() {
        return this.gameState;
    }

    /**
     * Get if key was pressed or released.
     */
    @Override
    public KeyType getType() {
        return this.type;
    }
}
