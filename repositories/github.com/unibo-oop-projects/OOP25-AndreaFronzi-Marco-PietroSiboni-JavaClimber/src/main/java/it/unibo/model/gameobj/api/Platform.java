package it.unibo.model.gameobj.api;

import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents a Platform entity in a two-dimensional game environment.
 */
public interface Platform extends StaticEntity {

  /**
   * Platform's touch reaction.
   *
   * @param boundary the boundary of the world
   * @param gameWorld the {@link GameWorld} which contains all game objects
   */
  void onTouch(Boundary boundary, GameWorld gameWorld);

  /**
   * Update Platform's position. Computed position deals with elapsed time
   * between two updates.
   *
   * @param dt the time interval between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(double dt, Boundary boundary);
}
