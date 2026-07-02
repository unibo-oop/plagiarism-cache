package controller.stagehandler.opponentbehaviour;

import java.util.Collection;

import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon;

/**
 * Handles the creation and update of all enemies'AI
 * and the projectiles fired by them.
 */
public interface OpponentHandler {

   /**
    * Demands all alive enemies'AI to manage them.
    * @param player the player in the stage
    */
   void invoke(PlayerShip player);

   /**
    * Create a new AI for each enemy present in the current stage.
    * @param enemies the alive enemies in the stage
    */
   void reset(Collection<EnemyShip> enemies);

   /**
    * Remove the AI of dead enemies.
    * @param deadEnemies the enemies killed by the player
    */
   void update(Collection<EnemyShip> deadEnemies);

   /**
    * Return all the projectiles fired by all enemies in the screen.
    * @return all the projectiles fired by all enemies in the screen.
    */
   Collection<Weapon.Projectile> getProjectiles();

}
