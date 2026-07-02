package spacesurvival.model.gui.game;

import java.util.Optional;
import java.util.Set;
import spacesurvival.controller.collision.CollisionController;
import spacesurvival.model.World;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gameobject.fireable.weapon.Weapon;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.settings.SkinSpaceShip;
import spacesurvival.model.worldevent.WorldEventListener;

public interface Engine extends EngineGUI {

    /**
     * Set a controller collision. 
     * 
     * @param collisionController
     */
    void setCollisionController(CollisionController collisionController);
    /**
     * set in pause all objects.
     * 
     * @param isPause
     */
    void setPauseAnimationAllObject(boolean isPause);
    /**
     * Return the timer of the current game.
     * 
     * @return a representing string
     */
    String getTimer();
    /**
     * Start the game timer.
     */
    void startTimer();
    /**
     * Stop the game timer.
     */
    void stopTimer();
    /**
     * Get the score of the current game.
     * 
     * @return the current score
     */
    int getScore();
    /**
     * Return the current round.
     * 
     * @return the number of the current round
     */
    int getRound();
    /**
     * Return the count of the current enemies.
     * 
     * @return the number of current enemies
     */
    int getCountEnemies();
    /**
     * Return the numer of remaining lives.
     * 
     * @return the current number of lives
     */
    int getLives();
    /**
     * Return the life of the ship.
     * 
     * @return the current life of the ship
     */
    int getLifeShip();
    /**
     * Return the life of the boss.
     * 
     * @return the current life of the boss
     */
    int getLifeBoss();
    /**
     * Increment the score.
     * 
     * @param score the score to increase
     */
    void incrScore(int score);
    /**
     * Increment the round.
     * 
     */
    void incrRound();
    /**
     * Decrease the life of the ship.
     * 
     * @param damage the damage occcurred
     */
    void decreaseLifeShip(int damage);
    /**
     * Decrease the live of the ship.
     * 
     */
    void decreaseLives();
    /**
     * Increase the live of the ship.
     * 
     * @param amount of live to increase
     */
    void increaseLives(int amount);
    /**
     * Set the life of the ship.
     * 
     * @param life to set.
     */
    void setLifeShip(int life);
    /**
     * Reset the life to the starting value.
     */
    void resetLifeShip();
    /**
     * Return true if is in game over state.
     * 
     * @return a boolean representing the state
     */
    boolean isGameOver();
    /**
     * Return the current World.
     * 
     * @return the current World
     */
    World getWorld();
    /**
     * Restart and reset all entities of the game.
     */
    void restartGame();
    /**
     * Return all alive objects.
     * 
     * @return a set composed by all alive GameObjects
     */
    Set<GameObject> getAllObjects();
    /**
     * Return the SpaceShipSingleton.
     * 
     * @return the SpaceShipSingleton
     */
    SpaceShipSingleton getShip();
    /**
     * Set the passed WorldEventListener to the world.
     * 
     * @param worldEventListener the WorldEventListener to set
     */
    void setEventListenerInWorld(WorldEventListener worldEventListener);
    /**
     * Update the state of the world.
     */
    void updateStateWorld();
    /**
     * Set the skin to the SpaceShipSingleton.
     * 
     * @param currentSkin
     */
    void setSkin(SkinSpaceShip currentSkin);
    /**
     * Return the boss, if present.
     * 
     * @return an Optional of FireableGameObject empty if there isn't a boss
     */
    Optional<FireableObject> getBoss();
    /**
     * Get the current weapon of the ship.
     * 
     * @return the current Weapon
     */
    Weapon getWeaponShip();
    /**
     * Get the ammo type of the current weapon.
     * 
     * @return the current AmmoType
     */
    AmmoType getAmmoTypeShip();
    /**
     * Assign the bullet type in the HUD.
     */
    void assignBulletShipInHUD();
    /**
     * Return the ammo type present in HUD.
     * 
     * @return the displayed AmmoType
     */
    AmmoType getAmmoTypeHUD();
    /**
     * Set the ammo type to the SpaceShipSingleton.
     * 
     * @param ammoType AmmoTrype to set
     */
    void setAmmoType(AmmoType ammoType);

}
