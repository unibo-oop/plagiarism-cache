package model.components;

import model.entities.EntityModel;

/**
 * Represents a component that can be contained in one entity. Components can
 * have many different behaviors.
 */
public interface AdvancedComponent extends Component {

    /**
     * Set the component's owner. A component can have only ONE owner.
     * 
     * @param owner the EntityModel that will own the component
     */
    void setOwner(EntityModel owner);

    /**
     * Remove the actually owner.
     */
    void removeOwner();

    /**
     * @return the owner
     */
    EntityModel getOwner();
}
