package it.unibo.model.physics.alienphysic.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.EndState;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.alienphysic.api.AbstractTemplatePhysic;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;

/**
 * A concrete implementation of the {@link AlienPhysic} interface.
 * This class applies gravitational force on the vertical axis and
 * manages boundary behavior with a Pacman effect.
 */
public class AlienNormalPhysic extends AbstractTemplatePhysic {

  /**
   * Update alien position and speed applying gravity.
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
    final double speedY = alien.getSpeedY();

    final double newVelY = speedY + (GameObjDimension.GRAVITY * dt);
    if (alien.isMovingLeft()) {
      alien.setSpeed(new Vector2dImpl(GameObjDimension.LEFT_ALIEN_SPEED_X, newVelY));
    } else if (alien.isMovingRight()) {
      alien.setSpeed(new Vector2dImpl(GameObjDimension.RIGHT_ALIEN_SPEED_X, newVelY));
    } else {
      alien.setSpeed(new Vector2dImpl(GameObjDimension.NULL_ALIEN_SPEED, newVelY));
    }
    alien.setPosition(new Vector2dImpl(alien.getPosX() + alien.getSpeedX() * dt * activeUpgrades.getSpeedMultiplier(),
        alien.getPosY() + alien.getSpeedY() * dt * activeUpgrades.getSpeedMultiplier()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hitPlatform(final Alien alien, final Platform p, final Boundary boundary, final GameWorld gameWorld,
      final ActiveUpgrades activeUpgrades) {
    final double pTollerance = 0;
    final boolean falling = alien.getSpeedY() > 0;
    final boolean above = (alien.getPosY() + alien.getHeight()) >= (p.getPosY() + p.getHeight() + pTollerance);

    if (falling && !above) {
      final double vx = GameObjDimension.NULL_ALIEN_SPEED;
      final double vy = GameObjDimension.JUMP_ALIEN_SPEED_Y * activeUpgrades.getJumpMultiplier();
      p.onTouch(boundary, gameWorld);
      alien.setPosition(new Vector2dImpl(alien.getPosX(), p.getPosY() - alien.getHeight()));
      alien.setSpeed(new Vector2dImpl(vx, vy));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hitEnemy(final Alien alien, final Enemy e, final GameWorld gameWorld, final LaunchedGame launchedGame,
      final ActiveUpgrades activeUpgrades) {
    final double eTollerance = 0;
    final boolean falling = alien.getSpeedY() > 0;
    final boolean above = (alien.getPosY() + alien.getHeight()) >= (e.getPosY() + e.getHeight() + eTollerance);

    if (falling && !above) {
      final double vx = GameObjDimension.NULL_ALIEN_SPEED;
      final double vy = GameObjDimension.JUMP_ALIEN_SPEED_Y * activeUpgrades.getJumpMultiplier();
      e.die(gameWorld);
      alien.setSpeed(new Vector2dImpl(vx, vy));
    } else {
      launchedGame.setState(new EndState(launchedGame));
    }
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
