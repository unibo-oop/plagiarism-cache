package it.unibo.model.physics.alienphysic.api;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.Boundary;

/**
 * Represents the application of the chosen physic over the Alien.
 */
public interface AlienPhysic {

  /**
   * Handles the interaction between an {@link Alien} and a {@link Coin}.
   *
   * @param coin           the {@link Coin} involved in the interaction
   * @param activeUpgrades the {@link ActiveUpgrades} affecting the Alien
   * @param gameWorld      the {@link GameWorld} which contains all gameObj
   */
  void hitCoin(Coin coin, ActiveUpgrades activeUpgrades, GameWorld gameWorld);

  /**
   * Handles the interaction between an {@link Alien} and an {@link Enemy}.
   * This method applies the appropriate physics effect when the Alien collides
   * with an Enemy.
   *
   * @param alien          the {@link Alien} involved in the collision
   * @param e              the {@link Enemy} involved in the collision
   * @param gameWorld      the {@link GameWorld} which contains all gameObj
   * @param launchedGame   the {@link LaunchedGame} which can be ended if the
   *                       Alien collides with an Enemy
   * @param activeUpgrades the {@link ActiveUpgrades} affecting the Alien
   */
  void hitEnemy(Alien alien, Enemy e, GameWorld gameWorld, LaunchedGame launchedGame, ActiveUpgrades activeUpgrades);

  /**
   * Handles the interaction between an {@link Alien} and a {@link Gadget}.
   * This method applies the appropriate physics effect when the Alien interacts
   * with the Gadget.
   *
   * @param alien     the {@link Alien} involved in the interaction
   * @param g         the {@link Gadget} being interacted with
   * @param gameWorld the {@link GameWorld} which contains all gameObj
   */
  void hitGadget(Alien alien, Gadget g, GameWorld gameWorld);

  /**
   * Handles the collision between an {@link Alien} and a {@link Platform}.
   * This method applies the appropriate physics effect when the Alien interacts
   * with the Platform.
   *
   * @param alien          the {@link Alien} involved in the collision
   * @param p              the {@link Platform} involved in the collision
   * @param boundary       the boundary of the world
   * @param gameWorld      the {@link GameWorld} which contains alla gameObj
   * @param activeUpgrades the active upgrades affecting the Alien
   */
  void hitPlatform(Alien alien, Platform p, Boundary boundary, GameWorld gameWorld, ActiveUpgrades activeUpgrades);

  /**
   * Apply chosen physical effect to alien's speed.
   *
   * @param alien          the alien to update
   * @param dt             the time step
   * @param boundWorld     the boundary of the world
   * @param activeUpgrades the active upgrades affecting the Alien
   * @param launchedGame   the launched game
   */
  void update(Alien alien, double dt, BoundWorld boundWorld, ActiveUpgrades activeUpgrades, LaunchedGame launchedGame);
}
