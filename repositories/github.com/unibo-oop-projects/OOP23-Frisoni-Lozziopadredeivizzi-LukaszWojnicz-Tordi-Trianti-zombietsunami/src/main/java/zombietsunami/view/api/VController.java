package zombietsunami.view.api;

import java.util.List;

import zombietsunami.api.Controller;
import zombietsunami.Pair;

/**
 * Interface representing the View Controller in the Zombie Tsunami game.
 * It provides methods to retrieve and update various game-related information.
 */
public interface VController {

  /**
   * This method sets a Controller for the View.
   * 
   * @param c is the Controller that will be setted as view's controller
   */
  void set(Controller c);

  /**
   * @return the screen width
   */
  int getScreenWC();

  /**
   * @return the screen hight
   */
  int getScreenHC();

  /**
   * @return the screen's cols
   */
  int screenColC();

  /**
   * @return the screen's row
   */
  int screenRowC();

  /**
   * @return the size of titles
   */
  int titleSizeC();

  /**
   * @return the FPS for tha game
   */
  int getFPSC();

  /**
   * @return the List of Strings with the file's name of the different tile
   *         elements
   */
  List<String> tileElementsC();

  /**
   * @return the List of Pair of Integers with the positions of all the tiles in
   *         the map
   */
  List<Pair<Integer, Integer>> screenTilePosC();

  /**
   * @return the List of Integers with all the values in the map's txt file
   */
  List<Integer> mapIndexListC();

  /**
   * Gets the current X-coordinate of the zombie on the map.
   *
   * @return The X-coordinate of the zombie on the map.
   */
  int getZombieMapX();

  /**
   * Gets the current Y-coordinate of the zombie on the map.
   *
   * @return The Y-coordinate of the zombie on the map.
   */
  int getZombieMapY();

  /**
   * Updates the position and state of the zombie in the game.
   */
  void updateZombie();

  /**
   * @return the map's (world) cols
   */
  int getWorldColC();

  /**
   * @return the map's (world) rows
   */
  int getWorldRowC();

  /**
   * @return the map (world) width
   */
  int getWorldWidthC();

  /**
   * @return the map (world) hight
   */
  int getWorldHightC();

  /**
   * Sets the list of Person from the map.
   */
  void setPersonFromMapC();

  /**
   * @return the Person list
   */
  List<Integer> getPersonListC();

  /**
   * @return the list of integers in the person's map txt file
   */
  List<Integer> personIndexListC();

  /**
   * Gets the current strength of the zombie character in the game.
   *
   * @return The strength of the zombie character.
   */
  int getStrength();

  /**
   * Gets the current X-coordinate of the zombie on the screen.
   *
   * @return The X-coordinate of the zombie on the screen.
   */
  int getZombieScreenX();

  /**
   * Gets the current Y-coordinate of the zombie on the screen.
   *
   * @return The Y-coordinate of the zombie on the screen.
   */
  int getZombieScreenY();

  /**
   * Initiates a jump action for the zombie in the game.
   */
  void jumpPress();

  /**
   * Updates the jump action for the zombie in the game.
   * This method is called to handle the animation and position update during a
   * zombie jump.
   */
  void updateJumpZombie();

  /**
   * Checks if the zombie in the game is currently jumping.
   *
   * @return True if the zombie is jumping, otherwise false.
   */
  boolean isJumping();

  /**
   * @return the List of Integers with all the values in the obstacle map's txt
   *         file
   */
  List<Integer> obstacleIndexListC();

  /**
   * Calls the set end position method in {@link zombietsunami.api.Controller}.
   * 
   * @param endX is the flag's X coordinate
   */
  void setEndPosC(int endX);

  /**
   * @return true if the flag's X position arrives in a certain axis
   */
  boolean isWinC();

  /**
   * Returns the list of bombs from the map.
   */
  void fillBombListFromMapC();

  /**
   * Returns the list of breakables from the map.
   */
  void fillBreakableListFromMapC();

  /**
   * Checks whether the obstacle has collided with the zombie.
   */
  void collisionZombieOstacleC();

  /**
   * Checks when the Zombie collided with the Person.
   */
  void collisionZombiePersonsC();

  /**
   * Checks if the game is over.
   * 
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * @return true if the zombie's strength is not enough to break the breakable
   *         object in the map
   */
  boolean isNotEnoughC();

  /**
   * @return true if the zombie's strength is zero
   */
  boolean isStrenghtZeroC();
}
