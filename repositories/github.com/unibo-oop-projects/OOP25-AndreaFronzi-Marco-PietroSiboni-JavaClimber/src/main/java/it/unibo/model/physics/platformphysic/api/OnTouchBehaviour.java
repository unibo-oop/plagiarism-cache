package it.unibo.model.physics.platformphysic.api;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Interface representing Platform's touch reaction.
 */
@FunctionalInterface
public interface OnTouchBehaviour {

  /**
   * Platform's touch reaction.
   *
   * @param platform  the {@link Platform} touched platform
   * @param boundary  the {@link Boundary} boundary of the world
   * @param realWorld the {@link GameWorld} which contains all gameObjects
   */
  void onTouch(Platform platform, Boundary boundary, GameWorld realWorld);
}
