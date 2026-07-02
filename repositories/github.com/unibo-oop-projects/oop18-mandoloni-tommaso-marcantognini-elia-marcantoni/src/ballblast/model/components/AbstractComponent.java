package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Generic implementation of the {@link Component} interface. Defines base
 * behavior that all components share.
 */
public abstract class AbstractComponent implements Component {
    private final ComponentType type;
    private GameObject parent;
    private boolean isAvailable;

    /**
     * Class constructor.
     * 
     * @param type the type of a specific {@link Component}.
     */
    public AbstractComponent(final ComponentType type) {
        this.type = type;
    }

    @Override
    public abstract void update(double elapsed);

    /** {@inheritDoc} */
    @Override
    public void enable() {
        this.isAvailable = true;
    }

    /** {@inheritDoc} */
    @Override
    public void disable() {
        this.isAvailable = false;
    }

    @Override
    public final ComponentType getType() {
        return type;
    }

    /** {@inheritDoc} */
    @Override
    public void setParent(final GameObject parent) {
        this.parent = parent;
    }

    @Override
    public final GameObject getParent() {
        return this.parent;
    }

    /**
     * Returns a boolean which notifies if the {@link Component} is enabled.
     * 
     * @return true if {@link Component} is enabled, false otherwise.
     */
    protected final boolean isEnabled() {
        return this.isAvailable;
    }
}
