package clashclass.view.graphic.components;

import clashclass.view.graphic.Graphic;

/**
 * Concrete implementation of GraphicComponent for rendering GameObjects.
 */
public class GraphicComponentImpl extends AbstractGraphicComponent {
    /**
     * Creates a GraphicComponent with specified dimensions.
     */
    public GraphicComponentImpl() {
        super(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphic graphics) {
        // graphics.drawRectangle(this.getGameObject());
    }
}
