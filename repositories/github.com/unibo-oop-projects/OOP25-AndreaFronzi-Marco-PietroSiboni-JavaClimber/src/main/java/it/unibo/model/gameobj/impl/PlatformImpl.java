package it.unibo.model.gameobj.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.AbstractGameObj;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.physics.platformphysic.api.OnTouchBehaviour;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

import java.util.Optional;

/**
 * A concrete implementation of the {@link Platform} interface.
 */
public class PlatformImpl extends AbstractGameObj implements Platform {

  /**
   * The {@link MovementBehaviour} of the Platform.
   */
  private final Optional<MovementBehaviour> movementBehaviour;

  /**
   * The {@link OnTouchBehaviour} of the Platform.
   */
  private final Optional<OnTouchBehaviour> onTouchBehaviour;

  /**
   * Constructs a new Platform with the specified two-dimensional position,
   * dimension, and movement and touch behavior.
   *
   * @param position the initial Platform's position
   * @param width the Platform's width
   * @param height the Platform's height
   * @param movementBehaviour the movement behavior
   * @param onTouchBehaviour the touch behavior
   */
  public PlatformImpl(
      final Vector2d position,
      final double width,
      final double height,
      final Optional<MovementBehaviour> movementBehaviour,
      final Optional<OnTouchBehaviour> onTouchBehaviour) {
    super(height, width, position);
    this.movementBehaviour = movementBehaviour;
    this.onTouchBehaviour = onTouchBehaviour;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final GameWorld gameWorld,
      final LaunchedGame launchedGame, final ActiveUpgrades activeUpgrades) {
    physic.hitPlatform(alien, this, boundary, gameWorld, activeUpgrades);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onTouch(final Boundary boundary, final GameWorld gameWorld) {
    this.onTouchBehaviour.ifPresent(otb -> otb.onTouch(this, boundary, gameWorld));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePosition(final double dt, final Boundary boundary) {
    this.movementBehaviour
        .ifPresent(mb -> mb.updatePosition(super.getPosition(), super.getWidth(), super.getHeight(), dt, boundary));
  }

}
