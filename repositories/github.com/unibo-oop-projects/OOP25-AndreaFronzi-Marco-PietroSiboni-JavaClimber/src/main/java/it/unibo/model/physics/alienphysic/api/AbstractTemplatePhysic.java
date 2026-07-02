package it.unibo.model.physics.alienphysic.api;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.launchedgame.impl.EndState;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents a template for the alien physic. It implements the update method,
 * which is common for all the alien physic, and it defines an abstract method
 * moveAlien, which is implemented by the different alien physic.
 */
public abstract class AbstractTemplatePhysic implements AlienPhysic {

  /**
   * Template Methods which update alien position and speed.
   *
   * @param alien          the alien to update
   * @param dt             the time elapsed since the last update
   * @param boundWorld     the boundary of the world
   * @param activeUpgrades the active upgrades affecting the Alien
   * @param launchedGame   the launched game
   */
  protected abstract void moveAlien(Alien alien, double dt, BoundWorld boundWorld,
      ActiveUpgrades activeUpgrades, LaunchedGame launchedGame);

  /**
   * Verify if the alien go beyond the boundary.
   *
   * @param alien        the alien which can go beyond the boundary
   * @param boundWorld   the boundaries, which can be crossed by the alien
   * @param launchedGame the launched game, which can be ended if the alien go
   *                     beyond the vertical boundary
   */
  private void verifyBoundaryTouch(final Alien alien, final BoundWorld boundWorld, final LaunchedGame launchedGame) {
    this.verifyHorizontalBoundaryTouch(alien, boundWorld.getBoundX());
    this.verifyVerticalBoundaryTouch(alien, boundWorld.getBoundY(), launchedGame);
  }

  /**
   * Verify if the alien go beyond the horizontal boundary. Apply pacman effect if
   * it
   * happens.
   *
   * @param alien    the {@link Alien} which can go beyond the horizontal boundary
   * @param boundary the {@link Boundary} horizontal boundary, which can be
   *                 crossed by the alien
   */
  private void verifyHorizontalBoundaryTouch(final Alien alien, final Boundary boundary) {
    if (alien.getPosX() + alien.getWidth() < boundary.x0()) {
      alien.setPosition(new Vector2dImpl(boundary.x1() - alien.getWidth(), alien.getPosY()));
    }
    if (alien.getPosX() > boundary.x1()) {
      alien.setPosition(new Vector2dImpl(boundary.x0(), alien.getPosY()));
    }
  }

  /**
   * Verify if the alien go beyond the vertical boundary. If it happens, the game
   * is ended.
   *
   * @param alien          the {@link Alien} which can go beyond the vertical
   *                       boundary
   * @param verticalBounds the {@link BoundY} vertical boundaries, which can be
   *                       crossed by the alien
   * @param launchedGame   the {@link LaunchedGame}, which can be ended if the
   *                       alien go beyond the vertical boundary
   */
  private void verifyVerticalBoundaryTouch(final Alien alien, final BoundY verticalBounds,
      final LaunchedGame launchedGame) {
    if (alien.getPosY() + alien.getHeight() > verticalBounds.maxY()) {
      launchedGame.setState(new EndState(launchedGame));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(final Alien alien, final double dt, final BoundWorld boundWorld,
      final ActiveUpgrades activeUpgrades, final LaunchedGame launchedGame) {
    moveAlien(alien, dt, boundWorld, activeUpgrades, launchedGame);
    verifyBoundaryTouch(alien, boundWorld, launchedGame);
  }

}
