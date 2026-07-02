package dungeon.floor;


import dungeon.character.monster.Monster;
import dungeon.character.player.Player;
import dungeon.gold.Gold;
import dungeon.item.Item;
import dungeon.point.Point;
import dungeon.room.Room;
import dungeon.weapon.Weapon;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An object representing a floor. The priority list used in some methods is
 * (from highest to lowest): players, monsters, weapons, golds, exits, items.
 */
public interface Floor extends Item {

  /**
   * Returns the type.
   *
   * @return the type
   */
  Type getType();

  /**
   * Returns the map of the players.
   *
   * @return the map
   */
  Map<Point, Player> getPlayers();

  /**
   * Returns the map of the monsters.
   *
   * @return the map
   */
  Map<Point, Monster> getMonsters();

  /**
   * Returns the map of the weapons.
   *
   * @return the map
   */
  Map<Point, Weapon> getWeapons();

  /**
   * Returns the map of the golds.
   *
   * @return the map
   */
  Map<Point, Gold> getGolds();

  /**
   * Returns the map of the exits.
   *
   * @return the map
   */
  Map<Point, Floor> getExits();

  /**
   * Returns the map of the items.
   *
   * @return the map
   */
  Map<Point, Item> getItems();

  /**
   * Returns the list of the rooms.
   *
   * @return the list
   */
  List<Room> getRooms();

  /**
   * Returns the room that contains point.
   *
   * @param point the point
   * @return the empty {@code Optional} if the room was not found, otherwise the
   *         full one with the value
   */
  Optional<Room> getRoom(Point point);

  /**
   * Returns the item of the highest priority point.
   *
   * @param point the point
   * @return the empty {@code Optional} if the position is empty, otherwise the
   *         full one with the item
   */
  Optional<? extends Item> getItem(Point point);

  /**
   * Checks if the highest priority point is blocking.
   *
   * @param point the point
   * @return true, if is blocking
   */
  boolean isBlocking(Point point);

  /**
   * Removes the highest priority point.
   *
   * @param point the point
   */
  void remove(Point point);

  /**
   * Move the highest priority point to another position.
   *
   * @param from the point
   * @param to the target point
   */
  void move(Point from, Point to);

  /**
   * Enter on this floor and update the player's floor and point.
   *
   * @param player the player to be placed
   * @param floor the floor from which {@code player} comes
   */
  void enter(Player player, Floor floor);

  /**
   * Exit from this floor and call {@code enter()} on the target floor.
   *
   * @param player the player point
   * @param floor the floor entry point
   */
  void exit(Point player, Point floor);

  /**
   * An object representing the type of {@code Floor}.
   */
  enum Type {

    /** An entry, where the player comes from. */
    ENTRY,

    /** A normal floor. */
    NORMAL,

    /** An exit, where the player can exit. */
    EXIT
  }
}
