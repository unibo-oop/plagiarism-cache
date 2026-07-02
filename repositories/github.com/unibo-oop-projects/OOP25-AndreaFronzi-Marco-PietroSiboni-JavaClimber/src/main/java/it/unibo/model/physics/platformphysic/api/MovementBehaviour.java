package it.unibo.model.physics.platformphysic.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.world.impl.Boundary;

/**
 * Interface representing game obgect's movement behavior.
 */
@FunctionalInterface
public interface MovementBehaviour {

  /**
   * Update Obj's position. Computed position deals with elapsed time between two
   * updates.
   * 
   * @param position Obj's position to update
   * @param width    Obj's width
   * @param heigth   Obj's heigth
   * @param dt       the time interval between two updates
   * @param boundary the boundary of the world
   */
  void updatePosition(Vector2d position, double width, double heigth, double dt, Boundary boundary);
}
