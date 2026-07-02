package clashclass.view.graphic.components;

import clashclass.view.graphic.Graphic;

import java.util.function.Consumer;

/**
 * Represents a general-purpose UI renderer.
 */
public class UIRendererImpl extends AbstractGraphicComponent {
    private Consumer<Graphic> drawFunction = x -> { };

    /**
     * Constructs the component.
     *
     * @param layer the layer
     */
    public UIRendererImpl(final int layer) {
        super(layer + 2);
    }

    /**
     * Sets the draw function delegate.
     *
     * @param drawFunction the draw function delegate
     */
    public void setDrawFunction(final Consumer<Graphic> drawFunction) {
        this.drawFunction = drawFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphic graphics) {
        this.drawFunction.accept(graphics);
    }
}
