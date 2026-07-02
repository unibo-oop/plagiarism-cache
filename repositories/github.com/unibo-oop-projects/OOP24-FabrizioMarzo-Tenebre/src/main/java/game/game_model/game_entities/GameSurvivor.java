package game.game_model.game_entities;

import input.input_component.InputSurvivorComponent;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import view.graphics.GraphicsSurvivor;
import view.graphics_component.survivor.GraphicsSurvivorComponent;

/**
 * Implementation of the {@link IGameSurvivor} interface.
 * Represents a game survivor entity, managing its state, input handling,
 * and graphical representation.
 */
public class GameSurvivor implements IGameSurvivor {

    private Survivor sur;
    private GraphicsSurvivorComponent imgSur;
    private InputSurvivorComponent inpSur;

    /**
     * Constructs a {@link GameSurvivor} object with the given survivor, graphics
     * component, and input component.
     *
     * @param sur    the {@link Survivor} entity
     * @param imgSur the {@link GraphicsSurvivorComponent} responsible for rendering
     *               the survivor
     * @param inpSur the {@link InputSurvivorComponent} handling survivor input
     */
    public GameSurvivor(final Survivor sur, final GraphicsSurvivorComponent imgSur,
            final InputSurvivorComponent inpSur) {
        this.sur = sur;
        this.imgSur = imgSur;
        this.inpSur = inpSur;
    }

    /**
     * Returns the survivor entity associated with this game survivor.
     *
     * @return the {@link Survivor} object
     */
    @Override
    public Survivor getSurvivor() {
        return this.sur;
    }

    /**
     * Updates the graphical representation of the survivor.
     *
     * @param graphicsSur the {@link GraphicsSurvivor} used to render the survivor
     */
    @Override
    public void updateGraphics(final GraphicsSurvivor graphicsSur) {
        imgSur.update(this.getSurvivor(), graphicsSur);
    }

    /**
     * Updates the survivor's state based on the provided input controller.
     *
     * @param c the {@link InputController} capturing user input
     */
    @Override
    public void updateInput(final InputController c) {
        inpSur.update(this.getSurvivor(), c);
    }

}
