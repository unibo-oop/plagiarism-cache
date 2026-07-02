package view.graphics_component.armory;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;

/**
 * Interface for graphical components responsible for rendering munitions.
 */
public interface GraphicsMunitionComponent {

    /**
     * Updates the graphical representation of the given munition.
     * 
     * @param mun  the munition to be drawn or updated
     * @param grsy the graphics interface used to draw the munition
     */
    void update(final Munition mun, final GraphicsMunition grsy);
}
