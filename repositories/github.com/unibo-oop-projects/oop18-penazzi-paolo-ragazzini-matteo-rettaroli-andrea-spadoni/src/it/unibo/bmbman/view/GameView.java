package it.unibo.bmbman.view;

import java.util.Set;

import it.unibo.bmbman.view.entities.BombView;
import it.unibo.bmbman.view.entities.EntityView;

/**
 * Interface for game view.
 */
public interface GameView {
    /**
     * Used to update the frame.
     * @param entitiesView all the entities' view to update 
     * @param bombs all the planted bombs' view to update
     */
    void render(Set<EntityView> entitiesView, Set<BombView> bombs);
}
