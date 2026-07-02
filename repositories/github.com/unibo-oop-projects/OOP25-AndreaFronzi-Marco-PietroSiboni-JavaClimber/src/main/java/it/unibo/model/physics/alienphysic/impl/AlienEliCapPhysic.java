package it.unibo.model.physics.alienphysic.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.alienphysic.api.AbstractTemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;

/**
 * Represents the alien physic when the alien collects the EliCap gadget. The
 * alien will have a vertical speed for a certain time interval, then it will
 * return to normal physic.
 */
public class AlienEliCapPhysic extends AbstractTemplatePhysic {
  /**
   * Represents the duration time of the gadget effect.
   */
  private double timeInterval;

  /**
   * Represents the vertical speed of the gadget effect.
   */
  private final double verticalSpeed;

  /**
   * Constructor of AlienEliCapPhysic.
   *
   * @param timeInterval  the duration time of the gadget effect
   * @param verticalSpeed the vertical speed of the gadget effect
   */
  public AlienEliCapPhysic(final double timeInterval, final double verticalSpeed) {
    this.timeInterval = timeInterval;
    this.verticalSpeed = verticalSpeed;
  }

  /**
   * Update alien position and speed using the gadget effect.
   *
   * @param alien          the alien to update
   * @param dt             the time step
   * @param boundWorld     the boundary of the world
   * @param activeUpgrades the active upgrades affecting the Alien
   * @param launchedGame   the launched game
   */
  @Override
  protected void moveAlien(final Alien alien, final double dt, final BoundWorld boundWorld,
      final ActiveUpgrades activeUpgrades, final LaunchedGame launchedGame) {
    alien.setSpeed(new Vector2dImpl(alien.getSpeedX(), this.verticalSpeed));

    if (this.timeInterval - dt >= 0) {
      if (alien.isMovingLeft()) {
        alien.setSpeed(new Vector2dImpl(GameObjDimension.LEFT_ALIEN_SPEED_X, alien.getSpeedY()));
      } else if (alien.isMovingRight()) {
        alien.setSpeed(new Vector2dImpl(GameObjDimension.RIGHT_ALIEN_SPEED_X, alien.getSpeedY()));
      } else {
        alien.setSpeed(new Vector2dImpl(GameObjDimension.NULL_ALIEN_SPEED, alien.getSpeedY()));
      }
      alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt * activeUpgrades.getSpeedMultiplier(),
          alien.getPosY() + alien.getSpeedY() * dt * activeUpgrades.getSpeedMultiplier()));

      this.timeInterval -= dt;
    } else {
      final double remainingDt = dt - this.timeInterval;

      if (alien.isMovingLeft()) {
        alien.setSpeed(new Vector2dImpl(GameObjDimension.LEFT_ALIEN_SPEED_X, alien.getSpeedY()));
      } else if (alien.isMovingRight()) {
        alien.setSpeed(new Vector2dImpl(GameObjDimension.RIGHT_ALIEN_SPEED_X, alien.getSpeedY()));
      } else {
        alien.setSpeed(new Vector2dImpl(GameObjDimension.NULL_ALIEN_SPEED, alien.getSpeedY()));
      }
      alien.setPosition(new Vector2dImpl(
          alien.getPosX() + alien.getSpeedX() * this.timeInterval * activeUpgrades.getSpeedMultiplier(),
          alien.getPosY() + alien.getSpeedY() * this.timeInterval * activeUpgrades.getSpeedMultiplier()));

      alien.setPhysic(new AlienNormalPhysic());
      alien.updatePosition(remainingDt, boundWorld, launchedGame);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hitPlatform(final Alien alien, final Platform p, final Boundary boundary, final GameWorld gameWorld,
      final ActiveUpgrades activeUpgrades) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hitEnemy(final Alien alien, final Enemy e, final GameWorld gameWorld, final LaunchedGame launchedGame,
      final ActiveUpgrades activeUpgrades) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hitGadget(final Alien alien, final Gadget g, final GameWorld gameWorld) {
    g.onCollect(alien, gameWorld);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hitCoin(final Coin coin, final ActiveUpgrades activeUpgrades, final GameWorld gameWorld) {
    coin.collectCoin(gameWorld, activeUpgrades.getCoinMultiplier());
  }
}
