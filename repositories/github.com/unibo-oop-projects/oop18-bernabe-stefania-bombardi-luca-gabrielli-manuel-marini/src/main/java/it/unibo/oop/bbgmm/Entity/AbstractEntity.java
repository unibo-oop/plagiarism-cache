package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.EntityBody;
import it.unibo.oop.bbgmm.Entity.Component.EntityComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * base class for entity type
 */

public abstract class AbstractEntity implements Entity {
    private final EntityBody body;
    private final List<EntityComponent> components = new ArrayList<>();
    /**
     * constructor for Abstract Entity
     * @param body
     */
    public AbstractEntity(EntityBody body) {
        this.body = body;
        body.attach(this);
    }

    @Override
    public final EntityBody getBody(){
        return body;
    }

    @Override
    public void update(final double up){
        updateComponents(up);
    }

    @Override
    public void destroy(){
        components.forEach(this::remove);
        remove(body);
    }


    /*
    ora restituisce il componente se è presente nella lista, bisogna cambiarlo
    se non è presente nella lista
    --------- DA SISTEMARE ---------------
     */
    @Override
    public final EntityComponent get(EntityComponent component) {
        return components.get(components.indexOf(component));
    }

    @Override
    public void remove(EntityComponent component) {
        components.remove(component);
        component.detach();
    }

    @Override
    public void add(EntityComponent component) {
        components.add(component);
        component.attach(this);
    }

    /**
     * Calls {@link EntityComponent#update} on the component
     * @param up
     *          Time for the update since last call
     */
    protected void updateComponents(final double up){
        components.forEach(c -> c.update(up));
    }
}
