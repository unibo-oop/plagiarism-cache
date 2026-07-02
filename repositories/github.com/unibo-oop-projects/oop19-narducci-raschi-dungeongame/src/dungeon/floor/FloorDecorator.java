package dungeon.floor;

import dungeon.character.monster.Monster;
import dungeon.character.player.Player;
import dungeon.gold.Gold;
import dungeon.item.Item;
import dungeon.item.ItemDecorator;
import dungeon.point.Point;
import dungeon.room.Room;
import dungeon.weapon.Weapon;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A skeletal implementation of a {@code Floor} decorator.
 */
public abstract class FloorDecorator extends ItemDecorator implements Floor {

  private final Floor floor;

  /**
   * Instantiates a new {@code FloorDecorator}.
   *
   * @param floor the floor
   * @throws NullPointerException if {@code floor} is {@code null}
   */
  protected FloorDecorator(final Floor floor) {
    super(floor);
    this.floor = Objects.requireNonNull(floor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Type getType() {
    return this.floor.getType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Player> getPlayers() {
    return this.floor.getPlayers();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Monster> getMonsters() {
    return this.floor.getMonsters();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Weapon> getWeapons() {
    return this.floor.getWeapons();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Gold> getGolds() {
    return this.floor.getGolds();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Floor> getExits() {
    return this.floor.getExits();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Point, Item> getItems() {
    return this.floor.getItems();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Room> getRooms() {
    return this.floor.getRooms();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Room> getRoom(final Point point) {
    return this.floor.getRoom(point);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<? extends Item> getItem(final Point point) {
    return this.floor.getItem(point);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBlocking(final Point point) {
    return this.floor.isBlocking(point);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(final Point point) {
    this.floor.remove(point);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void move(final Point from, final Point to) {
    this.floor.move(from, to);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void enter(final Player player, final Floor floor) {
    this.floor.enter(player, floor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void exit(final Point player, final Point floor) {
    this.floor.exit(player, floor);
  }
}
