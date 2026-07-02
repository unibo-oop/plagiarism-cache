package view.graphics_component.level;

import model.level.types.Level;
import view.graphics.GraphicsLevel;

/**
 * Interface for components responsible for updating the graphical
 * representation of a game level.
 */
public interface GraphicsLevelComponent {

    /**
     * Updates the graphics of the given level using the provided graphics
     * interface.
     * 
     * @param lvl    the level to be rendered or updated
     * @param gryLvl the graphics interface used to draw the level
     */
    void update(final Level lvl, final GraphicsLevel gryLvl);
}
