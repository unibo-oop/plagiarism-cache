package it.unibo.biscia.core;

import java.util.Map;
import java.util.Set;

interface InteractionManager {

    boolean performMovement(Map<Entity.EntityManaged.Movable.Directable, Direction> commands);

    /**
     * list of eat on last movement.
     * 
     * @return Map of entity to eat and energy assumed
     */
    Map<Entity, Integer> getEat();

    /**
     * list of entity trimmed on last movement..
     * 
     * @return Map of entity to eat and length removed
     */
    Map<Entity, Integer> getTrimmed();

    /**
     * list of removed entity on last movement (eat or crush).
     * 
     * @return list of entity
     */
    Set<Entity> getRemoved();

    /**
     * list of entity moved.
     * 
     * @return
     */
    Set<Entity> getUpdated();

}
