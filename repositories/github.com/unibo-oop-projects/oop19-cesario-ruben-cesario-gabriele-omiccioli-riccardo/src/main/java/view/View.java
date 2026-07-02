package view;

import controller.ControllerEvent;
import controller.stagehandler.playerhandler.PlayerHandler;
import model.Stage;
import view.arena.ArenaUIController;
import view.keyboardhandler.KeyEvent;

/**
 * Connects the controller to the graphical interface.
 */
public interface View {

    /**
     * Returns KeyEvent.
     * @return keyEvent KeyEvent
     */
    KeyEvent getKeyEvent();

    /**
     * Sets ArenaUIController.
     * @param controller ArenaUIController
     */
    void setArenaUIController(ArenaUIController controller);

    /**
     * Returns the ArenaUIController.
     * @return arenaController ArenaUIController
     */
    ArenaUIController getArenaUIController();

    /**
     * Sets the PlayerHandler.
     * @param playerHandler PlayerHandler
     */
    void setPlayerHandler(PlayerHandler playerHandler);

    /**
     * Refresh elements in arena.
     * @param stage The game stage
     */
    void refreshArena(Stage stage);

    /**
     * Receive an Event.
     * @param event ControllerEvent
     */
    void receiveEvent(ControllerEvent event);

    /**
     * Exits the arena.
     */
    void exitArenaWindow();

    /**
     * Resets the easter eggs.
     */
    void reset();

    /**
     * Activate or deactivate easter eggs.
     * @param value Boolean used to activate or deactivate easter eggs
     */
    void setEasterEggs(Boolean value);

    /**
     * Toggle easter eggs.
     */
    void toggleEasterEggs();

    /**
     * Returns if the easter eggs are active.
     * @return easterEggs Boolean value indicating if the easter eggs are active or not
     */
    Boolean isEasterEggs();

}
