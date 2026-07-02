package model.components;

import model.entities.EntityModel;

/**
 * ABstract base class for advanced components.
 */
public abstract class AbstractComponent implements AdvancedComponent {

    private EntityModel owner;

    /**
     * @param owner the entity model that owns the component.
     */
    public AbstractComponent(final EntityModel owner) {
        this.owner = owner;
    }

    @Override
    public final void setOwner(final EntityModel owner) {
        this.owner = owner;
    }

    @Override
    public final void removeOwner() {
        this.owner = null;
    }

    @Override
    public final EntityModel getOwner() {
        return this.owner;
    }

}
