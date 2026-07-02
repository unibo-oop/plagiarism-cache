package it.unibo.model.gameobj.platformbuilder.api;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.physics.platformphysic.api.OnTouchBehaviour;

/**
 * Builder interface for creating {@link Platform} with specific attributes.
 */
public interface PlatformBuilder {

  /**
   * This method sets {@link Platform}'s position.
   * 
   * @param position the position of the Platform
   * @return this builder
   */
  PlatformBuilder at(Vector2d position);

  /**
   * This method sets {@link Platform}'s size.
   * 
   * @param width  the width of the Platform
   * @param height the height of the Platform
   * @return this builder
   */
  PlatformBuilder size(double width, double height);

  /**
   * This method sets the movement behavior of the {@link Platform}.
   * 
   * @param movementBehaviour the movement behavior of the Platform
   * @return this builder
   */
  PlatformBuilder addMovementBehaviour(MovementBehaviour movementBehaviour);

  /**
   * This method adds the behavior the {@link Platform} has when touched.
   * 
   * @param onTouchBehaviour the behavior when the Platform is touched
   * @return this builder
   */
  PlatformBuilder addJumpBehaviour(OnTouchBehaviour onTouchBehaviour);

  /**
   * This method builds the {@link Platform} with the specified attributes.
   * 
   * @return the built Platform
   */
  Platform build();
}
