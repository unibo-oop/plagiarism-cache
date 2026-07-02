package clashclass.view.graphic.components;

import clashclass.view.graphic.Graphic;

/**
 * Represents a {@link AbstractGraphicComponent} implementation which can render images.
 */
public class ImageRendererImpl extends AbstractGraphicComponent {
    private final String spriteName;

    /**
     * Constructs the component.
     *
     * @param spriteName the name of the image
     * @param layer the layer
     */
    public ImageRendererImpl(final String spriteName, final int layer) {
        super(layer);
        this.spriteName = spriteName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphic graphics) {
        graphics.drawSprites(this.getGameObject(), this.spriteName);
    }
}
