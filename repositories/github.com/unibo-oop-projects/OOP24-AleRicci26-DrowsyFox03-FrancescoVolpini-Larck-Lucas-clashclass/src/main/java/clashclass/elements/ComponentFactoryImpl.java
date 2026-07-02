package clashclass.elements;

import clashclass.commons.HealthComponentImpl;
import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.ecs.Component;
import clashclass.view.graphic.components.GraphicComponentImpl;
import clashclass.view.graphic.components.ProgressBarRendererImpl;

/**
 * Represents an implementation of ComponentFactory.
 */
public class ComponentFactoryImpl implements ComponentFactory {
    private static final int PROGRESS_BAR_WIDTH = 20;
    private static final int PROGRESS_BAR_HEIGHT = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createTransform2D(final Vector2D position) {
        return new Transform2D(position, Vector2D.zero(), Vector2D.one());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createHealth(final int maxValue) {
        return new HealthComponentImpl(maxValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createGraphic() {
        return new GraphicComponentImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createProgressBar(final String colorEx) {
        return new ProgressBarRendererImpl(
                2,
                PROGRESS_BAR_WIDTH,
                PROGRESS_BAR_HEIGHT,
                10,
                colorEx);
    }
}
