package model.components;

import model.entities.EntityModel;

/**
 * Represents an attack damage to be applied on others entities.
 */
public interface Attack extends Component {

    /**
     * Apply a damage to the given entity only if it contains a {@link Life}.
     * component
     * 
     * @param entity the entity on which to apply the damage
     */
    void applyDamage(EntityModel entity);
}
