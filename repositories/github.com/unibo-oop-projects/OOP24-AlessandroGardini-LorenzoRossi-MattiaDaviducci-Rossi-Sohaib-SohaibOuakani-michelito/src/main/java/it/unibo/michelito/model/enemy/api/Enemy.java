package it.unibo.michelito.model.enemy.api;

import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.modelutil.Updatable;

/**
 *A type of entity present in the maze that can be removed form and subject to change in time,
 * it will kill Michelito on touch, can't pass wall or box
 * it will move in maze independently.
 */
public interface Enemy extends Temporary, Updatable {
}
