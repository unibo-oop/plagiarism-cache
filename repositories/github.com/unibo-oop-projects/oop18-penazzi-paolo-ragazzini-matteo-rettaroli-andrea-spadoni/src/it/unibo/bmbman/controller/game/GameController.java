package it.unibo.bmbman.controller.game;

import java.util.Set;

import it.unibo.bmbman.view.entities.EntityView;
import it.unibo.bmbman.model.Level;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.HeroImpl;
/**
 * Interface to game controller, it manages starup and update game.
 */
public interface GameController {
    /**
     * Used to start the game.
     */
    void startGame();
    /**
     * Used to notify the end of game.
     */
    void endGame();
    /**
     * Used to set in pause the game.
     */
    void pause();
    /**
     * Provides level information. 
     * @return an instance of level
     */
    Level getLevel();
    /**
     * Used to store all the entities in game.
     * @param entity the {@link Entity} to add
     * @param enitityView the {@link EntityView} of entity to add
     */
    void addEntity(Entity entity, EntityView enitityView);
    /**
     * Used to add a {@link BombImpl}.
     */
    void addBomb();
    /**
     * used to know all the unwalkable entities.
     * @return a set of entity
     */
    Set<Entity> getUnwalkableEntity();
    /**
     *used to know all the walkable entities.
     * @return a set of entity
     */
    Set<Entity> getWalkableEntity();
    /**
     *used to know all the breakable entities.
     * @return a set of entity
     */
    Set<Entity> getBreakableEntity();
    /**
     *used to know all the unbreakable entities.
     * @return a set of entity
     */
    Set<Entity> getUnbreakableEntity();
    /**
     * Used to get the hero.
     * @return {@link Entity} representing player. 
     */
    HeroImpl getHero();
    /**
     * Used to know if game is over.
     * @return true if hero is dead
     */
    boolean isGameOver();
    /**
     * Used to know if the player has won.
     * @return true if the hero has the key and has found the door
     */
    boolean hasWon();
    /**
     * Used to update any entity in the world.
     */
    void update();
    /**
     * Reset the gameController after the end of the game.
     */
    void reset();
}

