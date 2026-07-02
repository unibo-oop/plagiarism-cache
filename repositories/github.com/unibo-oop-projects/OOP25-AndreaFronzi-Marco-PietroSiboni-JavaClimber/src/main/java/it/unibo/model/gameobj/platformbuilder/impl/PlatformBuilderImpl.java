package it.unibo.model.gameobj.platformbuilder.impl;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.PlatformImpl;
import it.unibo.model.gameobj.platformbuilder.api.PlatformBuilder;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.physics.platformphysic.api.OnTouchBehaviour;

import java.util.Optional;

/**
 * Implementation of the {@link PlatformBuilder} interface for building
 * {@link Platform} objects.
 */
public class PlatformBuilderImpl implements PlatformBuilder {

  /**
   * {@link Vector2d} which represents the position of the Platform.
   */
  private Vector2d position;

  /**
   * Width of the Platform.
   */
  private double width;

  /**
   * Height of the Platform.
   */
  private double height;

  /**
   * Optional {@link MovementBehaviour} of the Platform.
   */
  private Optional<MovementBehaviour> movementBehaviour = Optional.empty();

  /**
   * Optional {@link OnTouchBehaviour} of the Platform.
   */
  private Optional<OnTouchBehaviour> onTouchBehaviour = Optional.empty();

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder addMovementBehaviour(final MovementBehaviour behaviour) {
    this.movementBehaviour = Optional.of(behaviour);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder addJumpBehaviour(final OnTouchBehaviour onTouch) {
    this.onTouchBehaviour = Optional.of(onTouch);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder at(final Vector2d pos) {
    this.position = new Vector2dImpl(pos.getX(), pos.getY());
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Platform build() {
    return new PlatformImpl(this.position, this.width, this.height, this.movementBehaviour, this.onTouchBehaviour);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PlatformBuilder size(final double w, final double h) {
    this.width = w;
    this.height = h;
    return this;
  }

}
