package it.unibo.model.gameobj.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.AbstractGameObj;
import it.unibo.model.gameobj.api.StaticEntity;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.alienphysic.impl.AlienNormalPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * A concrete implementation of the {@link Alien} interface.
 * This class represents an alien entity within the game, providing
 * logic for its dimensions, speed, physic behavior, and position updates.
 */
public class AlienImpl extends AbstractGameObj implements Alien {

  /**
   * The active upgrades affecting the Alien.
   */
  private final ActiveUpgrades activeUpgrades;

  /**
   * The Alien's physic.
   */
  private AlienPhysic physic;

  /**
   * The two-dimensional speed of the Alien.
   */
  private final Vector2d speed;

  /**
   * {@code true} if the Alien is moving left, {@code false} otherwise.
   */
  private boolean movingLeft;

  /**
   * {@code true} if the Alien is moving right, {@code false} otherwise.
   */
  private boolean movingRight;

  /**
   * Constructs a new Alien with the specified two-dimensional position, null
   * speed, and specified width and height.
   *
   * @param position       the initial position of the Alien
   * @param speed          the initial speed of the Alien
   * @param width          the width of the Alien
   * @param height         the height of the Alien
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  public AlienImpl(final Vector2d position, final Vector2d speed, final double width, final double height,
      final ActiveUpgrades activeUpgrades) {
    super(height, width, position);
    this.speed = new Vector2dImpl(speed.getX(), speed.getY());
    this.physic = new AlienNormalPhysic();
    this.activeUpgrades = activeUpgrades;
    this.movingLeft = false;
    this.movingRight = false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSpeedX() {
    return this.speed.getX();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getSpeedY() {
    return this.speed.getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMovingLeft() {
    return this.movingLeft;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMovingRight() {
    return this.movingRight;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveLeft() {
    if (this.movingRight) {
      this.movingRight = false;
    }
    if (!this.movingLeft) {
      this.movingLeft = true;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveRight() {
    if (this.movingLeft) {
      this.movingLeft = false;
    }
    if (!this.movingRight) {
      this.movingRight = true;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyCollision(final StaticEntity gObj, final Boundary boundary, final GameWorld gameWorld,
      final LaunchedGame launchedGame) {
    gObj.onHitBy(this, this.physic, boundary, gameWorld, launchedGame, this.activeUpgrades);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPhysic(final AlienPhysic physic) {
    this.physic = physic;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSpeed(final Vector2d speed) {
    this.speed.setX(speed.getX());
    this.speed.setY(speed.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stopMoving() {
    this.movingLeft = false;
    this.movingRight = false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePosition(final double dt, final BoundWorld boundWorld, final LaunchedGame launchedGame) {
    this.physic.update(this, dt, boundWorld, activeUpgrades, launchedGame);
  }
}
