package model;

import java.util.List;

import model.animated.Animated;
import model.animated.Bullet;
import model.inanimated.Button;
import model.room.Room;
import utility.Command;
import utility.Mode;

/**
 * Interface of World.
 * 
 **/
public interface World {

    /**
     * get the current Room.
     * 
     * @return Return a Room type object that define the current room
     */
    Room getActualRoom();

    /**
     * if the player loses all the hearts the method should return true, false otherwise.
     * 
     * @return Return a boolean that define if the game is Over
     */
    boolean isGameOver();

    /**
     * if the Boss is defeated the method should return true, false otherwise.
     * 
     * @return Return a boolean that define if the Boss is defeated
     */
    boolean isBossDefeated();

    /**
     * 
     * @param player
     *          set the playable character
     */
    void createPlayer(Animated player);

    /**
     * This method set the next round to be played.
     * 
     * Initially there are limited and defined rounds.
     */
    void setNextRound();

    /**
     * This method removes a bullet from the enemy bullet list.
     * Usually when the bullet collides this other objects.
     * 
     * @param bullet
     *          the bulled to be deleted
     */
    void removeBulletEnemy(Bullet bullet);

    /**
     * This method removes a bullet from the player bullet list.
     * Usually when the bullet collides this other objects.
     * 
     * @param bullet
     *          the bulled to be deleted
     */
    void removeBulletPlayer(Bullet bullet);

    /**
     * This method removes a enemy from the World.
     * Usually when the enemy loses all his hearts.
     * 
     * @param enemy
     *          the enemy to be deleted
     */
    void removeEnemy(Animated enemy);

    /**
     * When a command to shot is received, a shot is created in the World.
     * 
     * This method adds to a Bullet List of the player the new bullet created.
     * 
     * @param bullet
     *          the bullet that will be added to the List<Bullet>
     */
    void addPlayerBullet(Bullet bullet);

    /**
     * Within a delta time the enemy shots, a shot is created in the World.
     * 
     * This method adds to a Bullet List of the enemy the new bullet created.
     * 
     * @param bullet
     *          the bullet that will be added to the List<Bullet>
     */
    void addEnemyBullet(Bullet bullet);

    /**
     * TO BE CORRECTLY DEFINED.
     * 
     * @param deltaTime
     *                 DT to define
     * @param movements
     *          list of command the player press
     * @param shoots
     *          list of shoots the player do
     */
    void update(double deltaTime, List<Command> movements, List<Command> shoots);

    /**
     * Add a new room.
     * 
     * @param newRoom
     *          the new room to add
     */
    void addRoom(Room newRoom);

    /**
     * This method return a Player type Object. 
     * 
     * @return Return the Player Object
     */
    Animated getPlayer();

    /**
     * Add a Button to the World.
     * 
     * @param btn
     *          the button to add
     */
    void addButton(Button btn);

    /**
     * change button state.
     * @param effect
     *          true the button will be activated, false otherwise.
     */
    void setButton(boolean effect);

    /**
     * World will keep track of the commands pressed by the user (updateMethod will update this list). 
     * @return  the list of the commands pressed by the user for moving.
     * 
     */
    List<Command> getMovementCommandList();

    /**
     * 
     * @return the list of commands pressed by the user for shots.
     */
    List<Command> getShotCommandList();

    /**
     * @return the current round.
     */
    int getCurrentRound();

    /**
     * @param m mode.
     * Set the modality of the game.
     * There are two types of mode currently :
     * Normal Mode
     * Infinite Mode
     */
    void setMode(Mode m);

    /**
     * Create Environment.
     * Creates the rooms and the doors.
     */
    void createEnvironment();
}
