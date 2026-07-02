package game.game_model.game_armory;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_component.armory.GraphicsMunitionComponent;

/**
 * Implementation of the {@link IGameMunition} interface.
 * <p>
 * Represents a game munition entity, managing its state and graphical
 * representation.
 * </p>
 */
public class GameMunition implements IGameMunition {

    private Munition mun;
    private GraphicsMunitionComponent imgM;

    /**
     * Constructs a new {@code GameMunition} with the given munition model and
     * graphics component.
     *
     * @param mun  the underlying {@link Munition} model object
     * @param imgM the {@link GraphicsMunitionComponent} responsible for rendering
     *             the munition's graphics
     */
    public GameMunition(final Munition mun, final GraphicsMunitionComponent imgM) {
        this.mun = mun;
        this.imgM = imgM;
    }

    /**
     * {@inheritDoc}
     * 
     * Returns the underlying munition model.
     *
     * @return the {@link Munition} instance associated with this game munition
     */
    @Override
    public Munition getMunition() {
        return this.mun;
    }

    /**
     * {@inheritDoc}
     * 
     * Updates the graphical representation of the munition.
     *
     * @param graphicsMun the {@link GraphicsMunition} used for rendering
     */
    @Override
    public void updateGraphics(final GraphicsMunition graphicsMun) {
        imgM.update(this.getMunition(), graphicsMun);
    }

}
