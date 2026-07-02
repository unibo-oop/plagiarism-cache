package spacesurvival.model;

import java.awt.event.KeyListener;
import java.util.List;
import spacesurvival.controller.CallerCommandShip;
import spacesurvival.controller.collision.CollisionController;
import spacesurvival.model.commandship.MovementKeyListener;
import spacesurvival.model.common.Pair;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.model.gui.settings.SkinSpaceShip;
import spacesurvival.model.worldevent.WorldEventListener;
import spacesurvival.utilities.CommandKey;
import spacesurvival.utilities.CommandType;

/**
 * Controller interface.
 */
public interface Controller {

    /**
     * Return the current World.
     * 
     * @return return the World entity
     */
    World getWorld();

    /**
     * Assign the world to the view.
     * 
     */
    void assignWorld();

    /**
     * Return the ship.
     * 
     * @return the SpaceShipSingleton
     */
    SpaceShipSingleton getShip();

    /**
     * Return the command list of the ship composed by the input key code and the command type.
     * 
     * @return the command list of the ship
     */
    List<Pair<CommandKey, CommandType>> getSpaceShipCommandList();

    /**
     * Clear the ship command list.
     */
    void clearSpaceShipCommandList();

    /**
     * Return the caller for the actions of the spaceship.
     * 
     * @return a CallerCommandShip
     */
    CallerCommandShip getCallerShip();

    /**
     * Execute the passed command on the current CallerCommandShip.
     * 
     * @param cmd the CommandKey to execute.
     */
    void executeOnShip(CommandKey cmd);

    /**
     * Create new entities.
     * 
     */
    void createNewEntities();

    /**
     * Initialize the HUD. 
     *
     */
    void initHUD();

    /**
     * Update the HUD. 
     *
     */
    void updateHUD();

    /**
     * Start the timer of the HUD.
     *
     */
    void startTimer();

    /**
     * Stop the timer of the HUD.
     *
     */
    void stopTimer();

    /**
     * Update the timer in the HUD.
     *
     */
    void updateTimer();

    /**
     * Return the current ControllerCollision.
     *
     *@return the CollisionController
     */
    CollisionController getControllerCollision();

    /**
     * Return the current MovementKeyListener.
     *
     *@return the MovementKeyListener
     */
    MovementKeyListener getMovementKeyListener();

    /**
     * Set the MovementKeyListener to the SpaceShipSingleton.
     *
     */
    void assignMovementListenerInShip();

    /**
     * Add a KeyListener to the SpaceShipSingleton.
     *
     *@param keyListener the KeyListener to add to the SpaceShipSingleton
     */
    void addKeyListenerShip(KeyListener keyListener);

    /**
     * Set the event listener to the World.
     *
     *@param worldEventListener 
     */
    void setEventListenerInWorld(WorldEventListener worldEventListener);

    /**
     * Set the pause state of the animations, true pause the animation.
     *
     *@param isPause true set the pause to the animations.
     */
    void setPauseAnimationAllObject(boolean isPause);

    /**
     * Set the skin if the SpaceShipSingleton.
     *
     *@param currentSkin the skin to set
     */
    void setSkin(SkinSpaceShip currentSkin);

    /**
     * Update the score.
     *
     */
    void updateScore();

    /**
     * Update the round.
     *
     */
    void updateRound();

    /**
     * Update the count of the alive enemies.
     *
     */
    void updateCountEnemies();

    /**
     * Update the bullet icon in the HUD.
     *
     */
    void updateBulletHUD();

    /**
     * Update the number of lives in the HUD.
     *
     */
    void updateNHeart();

    /**
     * Update the life of the ship in the HUD.
     *
     */
    void updateLifeShip();

    /**
     * Update the life of the boss in the HUD if present.
     *
     */
    void updateLifeBoss();

    /**
     * Update the round in the HUD.
     *
     */
    void updateRoundState();

    /**
     * Set the passed visibility to the life bar of the boss in the HUD.
     * 
     * @param visible the boolean repersenting the visibility
     *
     */
    void setVisibleLifeBarBoss(boolean visible);

    /**
     * Return true if the player is in game over state.
     * 
     * @return a boolean representing the GameOver state
     *
     */
    boolean isGameOver();

    /**
     * Restart the game for a next game.
     *
     */
    void restartGame();

    /**
     * Increment the score of the passed score.
     *
     *@param score the score to increase.
     */
    void incrScore(int score);

    /**
     * Decrease the life of the SpaceShipSingleton of the passed damage.
     * 
     * @param damage the damage to decrease
     * 
     */
    void decreaseLife(int damage);

    /**
     * Increase the life of the SpaceShipSingleton of the passed heal amount.
     * 
     * @param healAmount the heal amount to increase
     * 
     */
    void increaseLife(int healAmount);

    /**
     * Increase the live of the SpaceShipSingleton of the passed amount.
     * 
     * @param amount the amount to increase
     * 
     */
    void increaseLives(int amount);

    /**
     * Return true if the passed damage is greater than the current life of the SpaceShipSingleton.
     * 
     * @param damage the damage to check
     * @return true if the passed damage is greater than the current life
     * 
     */
    boolean damageOverFlow(int damage);

    /**
     * Return true if the number of lives of the SpaceShipSingleton is greater than zero.
     * 
     * @return a true boolean if the number of lives of the SpaceShipSingleton is greater than zero.
     */
    boolean hasLivesShip();

    /**
     * Repaint game objects.
     * 
     */
    void repaintWorld();

    /**
     * Update the state of the current World.
     * 
     */
    void updateStateWorld();
}
