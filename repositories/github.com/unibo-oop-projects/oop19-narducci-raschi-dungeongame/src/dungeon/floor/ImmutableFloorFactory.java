package dungeon.floor;

import dungeon.character.monster.Monster;
import dungeon.character.player.Player;
import dungeon.floor.Floor.Type;
import dungeon.gold.Gold;
import dungeon.item.Item;
import dungeon.point.Point;
import dungeon.room.Room;
import dungeon.weapon.Weapon;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An immutable implementation of {@code FloorFactory}. The objects created only
 * support the method {@code getType()}, all other methods (excluding
 * {@code Item} ones) throw {@code UnsupportedOperationException}. The entry and
 * exit floors are intended as markers, respectively the entry point and the
 * exit point.
 */
public final class ImmutableFloorFactory implements FloorFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Floor getEntryFloor() {
    return new SimpleFloor("Entry", "Dungeon entry point", Type.ENTRY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Floor getExitFloor() {
    return new SimpleFloor("Exit", "Dungeon exit point", Type.EXIT);
  }

  private static final class SimpleFloor extends GenericFloor {

    private SimpleFloor(
      final String name,
      final String description,
      final Type type) {

      super(name, description, type);
    }

    @Override
    public Map<Point, Player> getPlayers() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Map<Point, Monster> getMonsters() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Map<Point, Weapon> getWeapons() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Map<Point, Gold> getGolds() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Map<Point, Floor> getExits() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Map<Point, Item> getItems() {
      throw new UnsupportedOperationException();
    }

    @Override
    public List<Room> getRooms() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Room> getRoom(final Point point) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Optional<? extends Item> getItem(final Point point) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlocking(final Point point) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final Point point) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void move(final Point from, final Point to) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void enter(final Player player, final Floor floor) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void exit(final Point player, final Point floor) {
      throw new UnsupportedOperationException();
    }
  }
}
