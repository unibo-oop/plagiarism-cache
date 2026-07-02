package game.game_model.game_entities;

import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import view.graphics.GraphicsSurvivor;

/**
 * Interface defining the behavior of a game survivor entity.
 * <p>
 * Provides methods for accessing the survivor, updating it based on input,
 * and rendering its graphical representation.
 * </p>
 */
public interface IGameSurvivor {

    /**
     * Returns the survivor associated with this game entity.
     *
     * @return the {@link Survivor} instance representing the game survivor
     */
    Survivor getSurvivor();

    /**
     * Updates the survivor's state based on the provided input controller.
     * <p>
     * This method processes user input and modifies the survivor accordingly.
     * </p>
     *
     * @param controller the {@link InputController} used to capture user input
     */
    void updateInput(final InputController controller);

    /**
     * Updates the graphical representation of the survivor.
     * <p>
     * This method is responsible for rendering the survivor on screen.
     * </p>
     *
     * @param grapSur the {@link GraphicsSurvivor} used for drawing the survivor
     */
    void updateGraphics(final GraphicsSurvivor grapSur);

}
