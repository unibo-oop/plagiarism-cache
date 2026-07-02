package clashclass.view.graphic.components;

import clashclass.ecs.Component;
import clashclass.view.graphic.Graphic;

/**
 * Represents a drawable component.
 */
public interface DrawableComponent extends Component {
    /**
     * Draws the component.
     *
     * @param graphics the graphics
     */
    void draw(Graphic graphics);
}
