package game.game_model.game_armory;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;

/**
 * Interface representing a game munition entity.
 * <p>
 * Provides methods to access the underlying munition model
 * and to update its graphical representation.
 * </p>
 */
public interface IGameMunition {

    /**
     * Returns the underlying {@link Munition} model object.
     * 
     * @return the {@link Munition} instance associated with this game munition
     */
    Munition getMunition();

    /**
     * Updates the graphical representation of the munition.
     * 
     * @param graphicsMun the {@link GraphicsMunition} instance used to render the
     *                    munition
     */
    void updateGraphics(final GraphicsMunition graphicsMun);

}
