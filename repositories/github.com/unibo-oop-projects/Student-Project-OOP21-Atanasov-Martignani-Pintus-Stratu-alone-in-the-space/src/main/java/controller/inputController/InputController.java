package controller.inputController;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommands;

/**
 * Interface for the InputController.
 */
public interface InputController {

    /**
     * Change Scene of this InputController.
     * 
     * @param scene where this InputController can detect keys.
     */
    void changeScene(Scene scene);

    /**
     * Set states of all Key and Commands to not active.
     */
    void resetState();

    /**
     * Get the key mapped to the command.
     * 
     * @param command
     * @return the mapped key.
     */
    KeyCode getKeyMapped(InputCommands command);

    /**
     * Add an association key-command. One association key-command per command type.
     *
     * @param key
     * @param command
     */
    void addCommandKeys(KeyCode key, InputCommands command);

    /**
     * Check if key is pressed.
     * 
     * @param key
     * @return status of the key.
     */
    boolean isKeyPressed(KeyCode key);

    /**
     * Check if player's task is active.
     * 
     * @param task
     * @return the player's task state.
     */
    boolean isTaskActive(InputCommands task);

    /**
     * Set the state of a key.
     *
     * @param key
     * @param state
     */
    void setKeyState(KeyCode key, boolean state);

    /**
     * Managing tasks.
     */
    void updatePlayerTasks();
}
