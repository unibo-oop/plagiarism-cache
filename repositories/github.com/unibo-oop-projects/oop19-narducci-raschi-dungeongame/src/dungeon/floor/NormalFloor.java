package dungeon.floor;

import dungeon.character.monster.Monster;
import dungeon.character.player.Player;
import dungeon.gold.Gold;
import dungeon.item.Item;
import dungeon.point.Point;
import dungeon.room.Room;
import dungeon.room.Rooms;
import dungeon.weapon.Weapon;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A standard implementation of {@code Floor}. Every getter of a map returns an
 * {@code unmodifiableMap} and every getter of a list returns an
 * {@code unmodifiableList}. The priority list used is the same as the one
 * defined in {@code Floor}.
 */
public final class NormalFloor extends GenericFloor {

  private final Map<Point, Player> players;
  private final Map<Point, Monster> monsters;
  private final Map<Point, Weapon> weapons;
  private final Map<Point, Gold> golds;
  private final Map<Point, Floor> exits;
  private final Map<Point, Item> items;
  private final List<Room> rooms;

  /**
   * Instantiates a new {@code NormalFloor}.
   *
   * @param name the name
   * @param description the description
   * @param players the players
   * @param monsters the monsters
   * @param weapons the weapons
   * @param golds the golds
   * @param exits the exits
   * @param items the items
   * @param rooms the rooms
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  public NormalFloor(
    final String name,
    final String description,
    final Map<Point, Player> players,
    final Map<Point, Monster> monsters,
    final Map<Point, Weapon> weapons,
    final Map<Point, Gold> golds,
    final Map<Point, Floor> exits,
    final Map<Point, Item> items,
    final List<Room> rooms) {

    super(name, description, Type.NORMAL);

    this.players = Objects.requireNonNull(players);
    this.monsters = Objects.requireNonNull(monsters);
    this.weapons = Objects.requireNonNull(weapons);
    this.golds = Objects.requireNonNull(golds);
    this.exits = Objects.requireNonNull(exits);
    this.items = Objects.requireNonNull(items);
    this.rooms = Objects.requireNonNull(rooms);
  }

  private <K, V> void move(
    final Map<K, V> map,
    final K from,
    final K to) {

    map.put(to, map.remove(from));
  }

  private Optional<Map<Point, ? extends Item>> getMap(final Point point) {
    Objects.requireNonNull(point);

    if (this.players.containsKey(point)) {
      return Optional.of(this.players);
    } else if (this.monsters.containsKey(point)) {
      return Optional.of(this.monsters);
    } else if (this.weapons.containsKey(point)) {
      return Optional.of(this.weapons);
    } else if (this.golds.containsKey(point)) {
      return Optional.of(this.golds);
    } else if (this.exits.containsKey(point)) {
      return Optional.of(this.exits);
    } else if (this.items.containsKey(point)) {
      return Optional.of(this.items);
    } else {
      return Optional.empty();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Player> getPlayers() {
    return Collections.unmodifiableMap(this.players);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Monster> getMonsters() {
    return Collections.unmodifiableMap(this.monsters);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Weapon> getWeapons() {
    return Collections.unmodifiableMap(this.weapons);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Gold> getGolds() {
    return Collections.unmodifiableMap(this.golds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Floor> getExits() {
    return Collections.unmodifiableMap(this.exits);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Item> getItems() {
    return Collections.unmodifiableMap(this.items);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Room> getRooms() {
    return Collections.unmodifiableList(this.rooms);
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   * @throws IllegalStateException if the number of rooms found is &gt; 1
   */
  @Override
  public Optional<Room> getRoom(final Point point) {
    return Rooms.findRoom(this.rooms, Objects.requireNonNull(point));
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   */
  @Override
  public Optional<? extends Item> getItem(final Point point) {
    Objects.requireNonNull(point);

    final Optional<Map<Point, ? extends Item>> map = this.getMap(point);

    if (map.isPresent()) {
      return Optional.of(map.get().get(point));
    } else {
      return Optional.empty();
    }
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   */
  @Override
  public boolean isBlocking(final Point point) {
    Objects.requireNonNull(point);

    final Optional<Map<Point, ? extends Item>> map = this.getMap(point);

    if (map.isPresent()) {
      return map.get().get(point).isBlocking();
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   */
  @Override
  public void remove(final Point point) {
    Objects.requireNonNull(point);

    this.getMap(point).ifPresent(map -> map.remove(point));
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  @Override
  public void move(final Point from, final Point to) {
    Objects.requireNonNull(from);
    Objects.requireNonNull(to);

    if (!this.isBlocking(to)) {
      this.getMap(from).ifPresent(map -> this.move(map, from, to));
    }
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if {@code floor} was not found
   */
  @Override
  public void enter(final Player player, final Floor floor) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(floor);

    for (final Point point : this.exits.keySet()) {
      if (floor.equals(this.exits.get(point))) {
        this.players.put(point, player);
        player.setFloor(this);
        player.setPoint(point);

        return;
      }
    }

    throw new IllegalArgumentException("floor not found");
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  @Override
  public void exit(final Point player, final Point floor) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(floor);

    this.exits.get(floor).enter(this.players.remove(player), this);
  }
}
