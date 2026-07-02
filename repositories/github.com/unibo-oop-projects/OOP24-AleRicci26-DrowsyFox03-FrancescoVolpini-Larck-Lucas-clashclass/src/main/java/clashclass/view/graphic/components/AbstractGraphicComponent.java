package clashclass.view.graphic.components;

import clashclass.ecs.AbstractComponent;

/**
 * Abstract class that represents the GraphicComponent of a GameObject.
 */
public abstract class AbstractGraphicComponent extends AbstractComponent implements DrawableComponent {
    private final int layer;

    /**
     * Protected Builder for GraphicComponent.
     *
     * @param layer of the GraphicComponent
     */
    protected AbstractGraphicComponent(final int layer) {
        this.layer = layer;
    }

    /**
     * Gets the layer of the GraphicComponent.
     *
     * @return the layer of the GraphicsComponent
     */
    public int getLayer() {
        return this.layer;
    }
}
