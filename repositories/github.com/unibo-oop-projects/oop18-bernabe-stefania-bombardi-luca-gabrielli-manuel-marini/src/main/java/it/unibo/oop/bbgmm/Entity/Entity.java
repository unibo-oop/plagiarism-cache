package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.EntityBody;
import it.unibo.oop.bbgmm.Entity.Component.EntityComponent;

import java.util.Optional;

public interface Entity {

    /**
     *
     * @return the body of the entity
     */
    EntityBody getBody();

    /**
     * Get a component by its type
     *
     * @param component
     *      Component that we want get
     * @return the component
     */
    <C extends EntityComponent> EntityComponent get(EntityComponent component);

    /**
     * remove a component from the body of the entity
     * @param component to remove
     */
    void remove (EntityComponent component);

    /**
     * add a new component at the entity
     * @param component that will be added
     */
    void add(EntityComponent component);

    /**
     * used to synchronize the entities
     *
     * @param up time since last call
     */
    void update(double up);

    void destroy();
}
