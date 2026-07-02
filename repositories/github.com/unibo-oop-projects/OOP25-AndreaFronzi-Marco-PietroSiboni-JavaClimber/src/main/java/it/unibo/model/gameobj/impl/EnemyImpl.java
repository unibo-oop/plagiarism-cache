package it.unibo.model.gameobj.impl;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.gameobj.api.AbstractGameObj;
import it.unibo.model.physics.alienphysic.api.AlienPhysic;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents an implementation of the {@link Enemy} interface.
 * This class models an enemy entity within a two-dimensional game environment.
 */
public class EnemyImpl extends AbstractGameObj implements Enemy {

  /**
   * Constructs a new EnemyImpl.
   *
   * @param height   Enemy's height
   * @param width    Enemy's width
   * @param position the position of the Enemy
   */
  public EnemyImpl(final double height, final double width, final Vector2d position) {
    super(height, width, position);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void die(final GameWorld gameWorld) {
    gameWorld.removeMonster(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onHitBy(final Alien alien, final AlienPhysic physic, final Boundary boundary, final GameWorld gameWorld,
      final LaunchedGame launchedGame, final ActiveUpgrades activeUpgrades) {
    physic.hitEnemy(alien, this, gameWorld, launchedGame, activeUpgrades);
  }
}
