package bubbleshooter.model.component;

import bubbleshooter.model.bubble.Bubble;

/**
 * Abstract class to implement the generic methods of {@link Component} interface.
 */
public abstract class AbstractComponent implements Component {

    private Bubble container;
    private ComponentType type;

    /**
     * @param container The {@link Bubble} which contains this component.
     */
    public AbstractComponent(final Bubble container) {
        this.container = container;
    }

    @Override
    public void update(final double elapsed) {
    }

    @Override
    public final void setContainer(final Bubble container) {
        this.container = container;
    }

    @Override
    public final Bubble getContainer() {
        return this.container;
    }

    @Override
    public final ComponentType getComponentType() {
        return this.type;
    }

    @Override
    public final void setType(final ComponentType type) {
        this.type = type;
    }

}
