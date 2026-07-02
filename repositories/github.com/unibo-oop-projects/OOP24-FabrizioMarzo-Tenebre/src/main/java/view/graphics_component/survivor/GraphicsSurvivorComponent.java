package view.graphics_component.survivor;

import model.entities.survivor.Survivor;
import view.graphics.GraphicsSurvivor;

/**
 * Interface for components responsible for updating the graphical
 * representation of a survivor.
 */
public interface GraphicsSurvivorComponent {

    /**
     * Updates the graphics of the given survivor using the provided graphics
     * handler.
     *
     * @param sur  the Survivor entity to be drawn or updated
     * @param grsy the GraphicsSurvivor instance used to perform the drawing
     */
    void update(final Survivor sur, final GraphicsSurvivor grsy);
}
