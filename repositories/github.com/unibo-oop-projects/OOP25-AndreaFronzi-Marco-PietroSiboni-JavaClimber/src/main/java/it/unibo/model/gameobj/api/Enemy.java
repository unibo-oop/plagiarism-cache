package it.unibo.model.gameobj.api;

import it.unibo.model.world.api.GameWorld;

/**
 * Represents an enemy entity in a two-dimensional game environment.
 * An enemy is a game object that has the ability to be "killed" during gameplay.
 */
public interface Enemy extends StaticEntity {

  /**
   * Triggers the death of the enemy.
   * This method handles the removal of the enemy from the game world
   * 
   * @param gameWorld the {@link GameWorld} from which the enemy should be removed upon death
   */
  void die(GameWorld gameWorld);
}
