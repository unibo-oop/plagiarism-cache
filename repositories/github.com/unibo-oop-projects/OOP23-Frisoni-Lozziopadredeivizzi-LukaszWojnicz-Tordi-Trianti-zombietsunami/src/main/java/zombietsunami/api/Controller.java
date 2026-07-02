package zombietsunami.api;

import java.util.List;

import zombietsunami.Pair;

/**
 * This interface is the controller of the MVC pattern, and it allows to
 * comunicate with the model classes and with the view classes.
 */
public interface Controller {

  /**
   * This method sets a new Model and sets this controller as the model
   * controller.
   */
  void setModel();

  /**
   * This method sets a new VConreoller (view's controller) and sets this
   * controller as the VController controller.
   */
  void setView();

  /**
   * @return the screen's cols
   */
  int getScreenCol();

  /**
   * @return the screen's rows
   */
  int getScreenRow();

  /**
   * @return the title's size
   */
  int getTitleSize();

  /**
   * @return the FPS value
   */
  int getFPS();

  /**
   * @return the screen width
   */
  int getScreenWidth();

  /**
   * @return the screen height
   */
  int getScreenHigh();

  /**
   * @return the List of Integers with all the values in the map's txt file
   */
  List<Integer> mapList();

  /**
   * @return the List of Strings with the file's name of the different tile
   *         elements
   */
  List<String> tileElements();

  /**
   * @return the List of Pair of Integers with the positions of all the tiles in
   *         the map
   */
  List<Pair<Integer, Integer>> screenTilePos();

  /**
   * @return the zombie's map X coordinate
   */
  int getZombieMapX();

  /**
   * @return the zombie's map Y coordinate
   */
  int getZombieMapY();

  /**
   * Calls the update method of the zombie
   * {@link zombietsunami.model.zombiemodel.api.Zombie}.
   */
  void updateZombie();

  /**
   * @return the map's (world) cols
   */
  int getWorldCol();

  /**
   * @return the map's (world) rows
   */
  int getWorldRow();

  /**
   * @return the map (world) width
   */
  int getWorldWidth();

  /**
   * @return the map (world) height
   */
  int getWorldHight();

  /**
   * @return the strength of the zombie
   *         {@link zombietsunami.model.zombiemodel.api.Zombie}.
   */
  int getStrength();

  /**
   * Sets the Person list from the map.
   */
  void setPersonFromMap();

  /**
   * @return the Person list.
   */
  List<Integer> getPersonList();

  /**
   * List of Person.
   * @return the list of Person
   */
  List<Integer> personList();

  /**
   * @return the zombie's screen X coordiante
   *         {@link zombietsunami.model.zombiemodel.api.Zombie}
   */
  int getZombieScreenX();

  /**
   * @return the zombie's screen Y coordiante
   *         {@link zombietsunami.model.zombiemodel.api.Zombie}
   */
  int getZombieScreenY();

  /**
   * Initiates the jump action by invoking the setStrength method of the
   * jumpZombie model.
   * {@link zombietsunami.model.zombiemodel.api.Zombie}
   */
  void jumpPress();

  /**
   * Updates the state of the jumpZombie, adjusting its properties based on the
   * game's progression.
   * {@link zombietsunami.model.zombiemodel.api.Zombie}
   */
  void updateJumpZombie();

  /**
   * Retrieves the current jumping status of the jumpZombie.
   * 
   * @return true if the jumpZombie is currently in a jumping state, false
   *         otherwise.
   *         {@link zombietsunami.model.zombiemodel.api.Zombie}
   */
  boolean isJumping();

  /**
   * Returns the loaded obstacle list.
   * in {@link zombietsunami.model.mapmodel.api.GameMap}
   * @return obstacle list.
   */
  List<Integer> obstacleList();

  /**
   * Calls the set end position method in {@link zombietsunami.api.Model}.
   * 
   * @param endX is the flag's X coordinate
   */
  void setEndPos(int endX);

  /**
   * @return true if the flag's X position arrives in a certain axis
   */
  boolean isWin();

  /**
   * Converts the bombs from the txt file to the Bomb object
   * and puts them inside a list.
   */
  void fillBombListFromMap();

    /**
     * Returns the list of breakables from the map.
     */
    void fillBreakableListFromMap();

    /**
     * Checks collision between zombie and Person.
     */
    void collisionZombiePersons();

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * @return true if the zombie's strenght is not enough to break the breakable object in the map
     */
    boolean isNotEnough();

    /**
     * @return true if the zombie's strenght is zero
     */
    boolean isStrenghtZero();

    /**
     * Checks if the zombie collides with the Obstascle.
     */
    void collisionZombieObstacle();
}
