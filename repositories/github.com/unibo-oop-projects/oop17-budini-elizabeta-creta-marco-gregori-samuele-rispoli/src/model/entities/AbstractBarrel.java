package model.entities;

import java.util.Optional;

/**
 * 
 * An interface modeling a Barrel, a {@link DynamicEntity} that {@link Mario} has to avoid,
 * with a method to determine if a collision is happening 
 *
 */
public interface AbstractBarrel extends DynamicEntity {

    
    /**
     * Getter for the trigger that allows to detect 
     * if {@link Mario} is colliding with it
     * 
     * @return 
     *          an {@link Entity} holding the trigger
     */
    Optional<Entity> getTrigger();
    
    
    /**
     * Remover for the trigger that allows to detect 
     * if {@link Mario} is colliding with it
     * 
     */
    void removeTrigger();

}
