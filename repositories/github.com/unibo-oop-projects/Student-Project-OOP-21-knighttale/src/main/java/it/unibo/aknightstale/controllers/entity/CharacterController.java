package it.unibo.aknightstale.controllers.entity;

import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

public interface CharacterController<M extends Character, V extends AnimatedEntityView>
        extends EntityController<M, V> {
    /**
     * Moves right the entity and updates the view.
     */
    void moveRight();

    /**
     * Moves left the entity and updates the view.
     */
    void moveLeft();

    /**
     * Moves up the entity and updates the view.
     */
    void moveUp();

    /**
     * Moves down the entity and updates the view.
     */
    void moveDown();

    /**
     * Attacks the entities that are in attack range of this entity and updates the
     * view.
     */
    void attack();



}
