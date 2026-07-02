package controller;

import controller.inputController.InputController;
import controller.inputController.InputControllerImpl;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import utilities.InputCommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class Test to check the right behavior of the player's tasks based on the user's inputs.
 */
public class InputControllerTest {

    private InputController inputController;

    InputControllerTest() {
        new JFXPanel();
        this.inputController = new InputControllerImpl(new Scene(new Group()));
    }

    /**
     * Testing Player Movements tasks with simulated inputs.
     * Up, Down, Left, Right, Stay.
     */
    @Test
    void checkMovement() {
        //Go Up
        this.simulateTask(InputCommands.UP, true);
        assertTrue(this.inputController.isTaskActive(InputCommands.UP));
        this.simulateTask(InputCommands.UP, false);
        //Go Down
        this.simulateTask(InputCommands.DOWN, true);
        assertTrue(this.inputController.isTaskActive(InputCommands.DOWN));
        this.simulateTask(InputCommands.DOWN, false);
        //Stay when up and down are in conflict
        this.simulateTask(InputCommands.UP, true);
        this.simulateTask(InputCommands.DOWN, true);
        assertFalse(this.inputController.isTaskActive(InputCommands.UP));
        assertFalse(this.inputController.isTaskActive(InputCommands.DOWN));
        this.simulateTask(InputCommands.UP, false);
        this.simulateTask(InputCommands.DOWN, false);
        //Go Left
        this.simulateTask(InputCommands.LEFT, true);
        assertTrue(this.inputController.isTaskActive(InputCommands.LEFT));
        this.simulateTask(InputCommands.LEFT, false);
        //Go Right
        this.simulateTask(InputCommands.RIGHT, true);
        assertTrue(this.inputController.isTaskActive(InputCommands.RIGHT));
        this.simulateTask(InputCommands.RIGHT, false);
        //Stay when left and right are in conflict
        this.simulateTask(InputCommands.LEFT, true);
        this.simulateTask(InputCommands.RIGHT, true);
        assertFalse(this.inputController.isTaskActive(InputCommands.LEFT));
        assertFalse(this.inputController.isTaskActive(InputCommands.RIGHT));
        this.simulateTask(InputCommands.LEFT, false);
        this.simulateTask(InputCommands.RIGHT, false);
        //Stay
        this.simulateTask(InputCommands.UP, false);
        this.simulateTask(InputCommands.DOWN, false);
        this.simulateTask(InputCommands.LEFT, false);
        this.simulateTask(InputCommands.RIGHT, false);
        assertFalse(this.inputController.isTaskActive(InputCommands.UP));
        assertFalse(this.inputController.isTaskActive(InputCommands.DOWN));
        assertFalse(this.inputController.isTaskActive(InputCommands.LEFT));
        assertFalse(this.inputController.isTaskActive(InputCommands.RIGHT));
    }

    /**
     * Testing Player attack tasks with simulated inputs.
     */
    @Test
    void checkAttack() {
        //Fire
        this.simulateTask(InputCommands.ATTACK, true);
        assertTrue(this.inputController.isTaskActive(InputCommands.ATTACK));
        this.simulateTask(InputCommands.ATTACK, false);
    }

    /**
     * Simulate the state of a Command.
     * @param command
     * @param state
     */
    private void simulateTask(final InputCommands command, final boolean state) {
        this.manageKey(this.inputController.getKeyMapped(command), state);
        this.inputController.updatePlayerTasks();
    }

    /**
     * Sets up the state(if pressed or not) of the key.
     *
     * @param key
     * @param state
     */
    private void manageKey(final KeyCode key, final boolean state) {
        this.inputController.setKeyState(key, state);
    }
}
