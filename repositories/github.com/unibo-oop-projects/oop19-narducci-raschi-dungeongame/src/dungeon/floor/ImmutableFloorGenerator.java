package dungeon.floor;

import dungeon.Direction;
import dungeon.Utilities;
import dungeon.character.monster.Monster;
import dungeon.character.player.Player;
import dungeon.floor.Floor.Type;
import dungeon.gold.Gold;
import dungeon.item.Item;
import dungeon.point.ImmutablePoint;
import dungeon.point.Point;
import dungeon.room.ImmutableRoom;
import dungeon.room.Room;
import dungeon.room.Rooms;
import dungeon.template.Template;
import dungeon.weapon.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * An immutable implementation of {@code FloorGenerator}. Currently when
 * generating the instances, a non-empty {@code Optional} is passed only to the
 * monster templates. This implementation supports exactly 2 exits per floor.
 */
public final class ImmutableFloorGenerator implements FloorGenerator {

  private static final int MIN_SIZE = 16;

  private final Random random;
  private final int size;
  private final int minSideSections;
  private final int maxWallDistance;
  private final int minRoomSize;
  private final int minSectionSize;
  private final int maxRoomTunnels;
  private final int minRoomTunnels;
  private final int maxSideTunnels;
  private final int maxRoomMonsters;
  private final int minRoomMonsters;
  private final int maxRoomWeapons;
  private final int maxRoomGolds;
  private final int exitsNumber;
  private final List<Template<Monster>> monsters;
  private final List<Template<Weapon>> weapons;
  private final List<Template<Gold>> golds;
  private final List<Template<Item>> walls;
  private final List<Template<Item>> doors;
  private final int monstersBound;
  private final int weaponsBound;
  private final int goldsBound;
  private final int wallsBound;
  private final int doorsBound;

  private ImmutableFloorGenerator(
    final int seed,
    final int size,
    final int minSideSections,
    final int maxWallDistance,
    final int minRoomSize,
    final int maxRoomTunnels,
    final int minRoomTunnels,
    final int maxSideTunnels,
    final int maxRoomMonsters,
    final int minRoomMonsters,
    final int maxRoomWeapons,
    final int maxRoomGolds,
    final int exitsNumber,
    final List<Template<Monster>> monsters,
    final List<Template<Item>> items,
    final List<Template<Weapon>> weapons,
    final List<Template<Gold>> golds) {

    this.random = new Random((long) seed);
    this.size = size;
    this.minSideSections = Utilities.requireGreaterThanZero(minSideSections);
    this.maxWallDistance = Utilities.requirePositive(maxWallDistance);
    this.minRoomSize = Utilities.requirePositive(minRoomSize);
    this.minSectionSize = this.maxWallDistance + this.minRoomSize;
    this.maxRoomTunnels = Utilities.requirePositive(maxRoomTunnels);
    this.minRoomTunnels = Utilities.requirePositive(minRoomTunnels);
    this.maxSideTunnels = Utilities.requireGreaterThanZero(maxSideTunnels);
    this.maxRoomMonsters = Utilities.requirePositive(maxRoomMonsters);
    this.minRoomMonsters = Utilities.requirePositive(minRoomMonsters);
    this.maxRoomWeapons = Utilities.requirePositive(maxRoomWeapons);
    this.maxRoomGolds = Utilities.requirePositive(maxRoomGolds);
    this.exitsNumber = Utilities.requirePositive(exitsNumber);
    this.monsters = Objects.requireNonNull(monsters);
    this.weapons = Objects.requireNonNull(weapons);
    this.golds = Objects.requireNonNull(golds);
    this.walls = new ArrayList<>();
    this.doors = new ArrayList<>();

    Objects.requireNonNull(items).forEach(item -> {
      if (item.getInstance(Optional.empty()).isBlocking()) {
        this.walls.add(item);
      } else {
        this.doors.add(item);
      }
    });

    if (this.walls.size() < 1) {
      throw new IllegalArgumentException(
        "items must contain at least one blocking item");
    }

    if (this.doors.size() < 1) {
      throw new IllegalArgumentException(
        "items must contain at least one non-blocking item");
    }

    this.monstersBound = this.getFrequencyBound(this.monsters);
    this.weaponsBound = this.getFrequencyBound(this.weapons);
    this.goldsBound = this.getFrequencyBound(this.golds);
    this.wallsBound = this.getFrequencyBound(this.walls);
    this.doorsBound = this.getFrequencyBound(this.doors);

    if (this.size < MIN_SIZE) {
      throw new IllegalArgumentException("size cannot be < " + MIN_SIZE);
    }

    if (this.size < this.minSectionSize) {
      throw new IllegalArgumentException(
        "size cannot be < than maxWallDistance + minRoomSize");
    }

    if (this.size / this.minSideSections < this.minSectionSize) {
      throw new IllegalArgumentException(
        "size / minSideSections cannot be <"
        + " than maxWallDistance + minRoomSize");
    }

    if (this.maxRoomMonsters + this.maxRoomWeapons + this.maxRoomGolds + 1
      > Math.pow(this.minRoomSize - 2, 2)) {

      throw new IllegalArgumentException(
        "maxRoomMonsters + maxRoomWeapons + maxRoomGolds + 1 (exit) cannot be >"
        + " than (minRoomSize - 2) ^ 2");
    }

    if (this.maxRoomMonsters < this.minRoomMonsters) {
      throw new IllegalArgumentException(
        "maxRoomMonsters cannot be < than minRoomMonsters");
    }

    if (this.maxRoomTunnels < this.minRoomTunnels) {
      throw new IllegalArgumentException(
        "maxRoomTunnels cannot be < than minRoomTunnels");
    }
  }

  private <T extends Item> int getFrequencyBound(final List<Template<T>> list) {
    int output = 1;

    for (final Template<T> template : list) {
      output += template.getFrequency();
    }

    return output;
  }

  private int randomInt(final int bound) {
    return this.random.nextInt(bound);
  }

  private <T extends Item> T select(
    final List<Template<T>> list,
    final int bound,
    final Optional<Point> point) {

    final int result = this.randomInt(bound);
    int index = 0;
    int frequency = 0;

    while (result > frequency) {
      frequency += list.get(index).getFrequency();

      if (frequency != bound - 1) {
        index++;
      }
    }

    return list.get(index).getInstance(point);
  }

  private Monster selectMonster(final Optional<Point> point) {
    return this.select(this.monsters, this.monstersBound, point);
  }

  private Weapon selectWeapon(final Optional<Point> point) {
    return this.select(this.weapons, this.weaponsBound, point);
  }

  private Gold selectGold(final Optional<Point> point) {
    return this.select(this.golds, this.goldsBound, point);
  }

  private Item selectWall() {
    return this.select(this.walls, this.wallsBound, Optional.empty());
  }

  private Item selectDoor() {
    return this.select(this.doors, this.doorsBound, Optional.empty());
  }

  private <T> void addInstances(
    final int times,
    final List<Point> points,
    final Map<Point, T> instances,
    final Function<Optional<Point>, T> function) {

    for (int count = 0; count < times; count++) {
      final int index = this.randomInt(points.size());
      final Point point = points.get(index);

      points.remove(index);
      instances.put(point, function.apply(Optional.of(point)));
    }
  }

  private List<Point> getRoomPoints(final Room room) {
    final List<Point> points = new LinkedList<>();
    final Point limit = room.getBase()
      .move(room.getSize())
      .move(new ImmutablePoint(-1, -1));

    for (int x = room.getBase().getX() + 1; x < limit.getX(); x++) {
      for (int y = room.getBase().getY() + 1; y < limit.getY(); y++) {
        points.add(new ImmutablePoint(x, y));
      }
    }

    return points;
  }

  private void generateInstances(
    final Room room,
    final Map<Point, Monster> monsters,
    final Map<Point, Weapon> weapons,
    final Map<Point, Gold> golds) {

    final List<Point> points = this.getRoomPoints(room);

    this.addInstances(
      this.randomInt(this.maxRoomMonsters - this.minRoomMonsters + 1)
        + this.minRoomMonsters,
      points, monsters, point -> this.selectMonster(point));

    this.addInstances(this.randomInt(this.maxRoomWeapons + 1),
      points, weapons, point -> this.selectWeapon(point));

    this.addInstances(this.randomInt(this.maxRoomGolds + 1),
      points, golds, point -> this.selectGold(point));
  }

  private void generateRoom(
    final Map<Point, Item> walls,
    final Room section,
    final List<Room> rooms) {

    final Room room = new ImmutableRoom(
      new ImmutablePoint(
        this.randomInt(this.maxWallDistance) + section.getBase().getX(),
        this.randomInt(this.maxWallDistance) + section.getBase().getY()),
      new ImmutablePoint(
        this.randomInt(section.getSize().getX() - this.minRoomSize)
          + this.minRoomSize,
        this.randomInt(section.getSize().getY() - this.minRoomSize)
          + this.minRoomSize));

    for (int index = 0; index < room.getSize().getX(); index++) {
      walls.put(room.getBase().move(index, 0), this.selectWall());
      walls.put(room.getBase().move(index, room.getSize().getY() - 1),
        this.selectWall());
    }

    for (int index = 0; index < room.getSize().getY(); index++) {
      walls.put(room.getBase().move(0, index), this.selectWall());
      walls.put(room.getBase().move(room.getSize().getX() - 1, index),
        this.selectWall());
    }

    rooms.add(room);
  }

  private Point getTunnelModifier(
    final Room room,
    final Point door) {

    final Point roomBase = room.getBase();
    final Point roomSize = room.getSize();
    final Point modifier;

    if (door.getX() == roomBase.getX()) {
      modifier = Direction.UP.getModifier();
    } else if (door.getY() == roomBase.getY()) {
      modifier = Direction.LEFT.getModifier();
    } else if (door.getX() == roomBase.getX() + roomSize.getX() - 1) {
      modifier = Direction.DOWN.getModifier();
    } else if (door.getY() == roomBase.getY() + roomSize.getY() - 1) {
      modifier = Direction.RIGHT.getModifier();
    } else {
      throw new IllegalStateException();
    }

    return modifier;
  }

  private int findDoors(
    final int limit,
    final Map<Point, Item> items,
    final List<Point> walls,
    final Function<Integer, Point> function) {

    final List<Point> wallsFound = new ArrayList<>();
    int doors = 0;

    /* loop from 1 to limit - 1 to avoid corners */
    for (int index = 1; index < limit - 1; index++) {
      final Point wall = function.apply(index);

      if (items.get(wall).isBlocking()) {
        wallsFound.add(wall);
      } else {
        doors++;
      }
    }

    /*
     * A room might still have more doors than maxSideTunnels on one side, this
     * variable was meant to help with the unreachable rooms issue but now is
     * used only to get a more balanced distribution of the doors.
     */
    if (doors < this.maxSideTunnels) {
      walls.addAll(wallsFound);
    }

    return doors;
  }

  private int findUpDoors(
    final Map<Point, Item> items,
    final Room section,
    final Room room,
    final List<Point> walls) {

    if (section.getBase().getX() != 0) {
      return this.findDoors(room.getSize().getY(), items, walls,
        y -> room.getBase().move(0, y));
    } else {
      return 0;
    }
  }

  private int findLeftDoors(
    final Map<Point, Item> items,
    final Room section,
    final Room room,
    final List<Point> walls) {

    if (section.getBase().getY() != 0) {
      return this.findDoors(room.getSize().getX(), items, walls,
        x -> room.getBase().move(x, 0));
    } else {
      return 0;
    }
  }

  private int findDownDoors(
    final Map<Point, Item> items,
    final Room section,
    final Room room,
    final List<Point> walls) {

    if (section.getBase().getX() + section.getSize().getX() != this.size) {
      return this.findDoors(room.getSize().getY(), items, walls,
        y -> room.getBase().move(room.getSize().getX() - 1, y));
    } else {
      return 0;
    }
  }

  private int findRightDoors(
    final Map<Point, Item> items,
    final Room section,
    final Room room,
    final List<Point> walls) {

    if (section.getBase().getY() + section.getSize().getY() != this.size) {
      return this.findDoors(room.getSize().getX(), items, walls,
        x -> room.getBase().move(x, room.getSize().getY() - 1));
    } else {
      return 0;
    }
  }

  private Optional<Point> generateTunnel(
    final Map<Point, Item> items,
    final List<Room> rooms,
    final Room room,
    final List<Point> walls) {

    final List<Point> tunnel = new ArrayList<>();
    final int wall = this.randomInt(walls.size());
    final Point door = walls.get(wall);
    final Point modifier = this.getTunnelModifier(room, door);

    Point position = door;
    Optional<Item> item = Optional.empty();

    walls.remove(wall);
    tunnel.add(door);

    while (item.isEmpty()
      && position.getX() >= 0
      && position.getY() >= 0
      && position.getX() < this.size
      && position.getY() < this.size) {

      position = position.move(modifier);
      tunnel.add(position);
      item = Optional.ofNullable(items.get(position));
    }

    final Point endDoor = tunnel.get(tunnel.size() - 1);

    /* check if the loop reached another tunnel or a room corner */
    if (item.isPresent()
      && Rooms.findRoom(rooms, endDoor).isPresent()
      && !items.containsKey(position.move(modifier))) {

      final Point leftWall;
      final Point rightWall;

      if (Direction.LEFT.getModifier().equals(modifier)
        || Direction.RIGHT.getModifier().equals(modifier)) {

        leftWall = Direction.UP.getModifier();
        rightWall = Direction.DOWN.getModifier();
      } else {
        leftWall = Direction.LEFT.getModifier();
        rightWall = Direction.RIGHT.getModifier();
      }

      items.put(door, this.selectDoor());
      items.put(endDoor, this.selectDoor());
      walls.remove(door.move(leftWall));
      walls.remove(door.move(rightWall));
      tunnel.remove(0);

      if (tunnel.size() > 0) {
        tunnel.remove(tunnel.size() - 1);

        tunnel.forEach(tunnelPoint -> {
          items.putIfAbsent(tunnelPoint.move(leftWall), this.selectWall());
          items.putIfAbsent(tunnelPoint.move(rightWall), this.selectWall());
        });
      }

      return Optional.of(endDoor);
    } else {
      return Optional.empty();
    }
  }

  private void generateTunnels(
    final Map<Point, Item> items,
    final List<Room> sections,
    final List<Room> rooms) {

    final Set<Room> linkedRooms = new HashSet<>();
    final Set<Room> unlinkedRooms = new HashSet<>();

    for (int index = 0; index < sections.size(); index++) {
      final Room section = sections.get(index);
      final Room room = rooms.get(index);
      final Set<Room> group = new HashSet<>();
      final List<Point> walls = new ArrayList<>();
      int tunnelsBuilt = 0;

      if (linkedRooms.isEmpty()) {
        linkedRooms.add(room);
      }

      if (unlinkedRooms.contains(room)) {
        group.addAll(unlinkedRooms);
        unlinkedRooms.clear();
      } else {
        group.add(room);
      }

      tunnelsBuilt += this.findUpDoors(items, section, room, walls);
      tunnelsBuilt += this.findLeftDoors(items, section, room, walls);
      tunnelsBuilt += this.findDownDoors(items, section, room, walls);
      tunnelsBuilt += this.findRightDoors(items, section, room, walls);

      final int tunnelsCount
        = this.randomInt(this.maxRoomTunnels - this.minRoomTunnels + 1)
        + this.minRoomTunnels;

      for (int count = 0; count < tunnelsCount && walls.size() > 0; count++) {
        final Optional<Point> door
          = this.generateTunnel(items, rooms, room, walls);

        if (door.isPresent()) {
          Rooms.findRoom(rooms, door.get()).ifPresent(
            found -> group.add(found));

          tunnelsBuilt++;
        } else if (tunnelsBuilt < this.minRoomTunnels) {
          count--;
        }
      }

      if (linkedRooms.removeAll(group)) {
        linkedRooms.addAll(group);
      } else {
        final List<Point> sideWalls = new ArrayList<>();
        final Set<Supplier<Integer>> suppliers = Set.of(
          () -> this.findUpDoors(items, section, room, sideWalls),
          () -> this.findLeftDoors(items, section, room, sideWalls),
          () -> this.findDownDoors(items, section, room, sideWalls),
          () -> this.findRightDoors(items, section, room, sideWalls));

        suppliers.forEach(supplier -> {
          if (!linkedRooms.contains(room)) {
            if (supplier.get() == 0) {
              Optional<Point> door = Optional.empty();

              while (door.isEmpty() && sideWalls.size() > 0) {
                door = this.generateTunnel(items, rooms, room, sideWalls);
              }

              door.ifPresent(point -> {
                Rooms.findRoom(rooms, point).ifPresent(found -> {
                  group.add(found);

                  if (linkedRooms.removeAll(group)) {
                    linkedRooms.addAll(group);
                  }
                });
              });

              sideWalls.clear();
            }
          }
        });

        if (!linkedRooms.contains(room)) {
          unlinkedRooms.addAll(group);
        }
      }
    }

    if (rooms.size() != linkedRooms.size()) {
      throw new IllegalStateException();
    }
  }

  private void generateExits(
    final Map<Point, Monster> monsters,
    final Map<Point, Weapon> weapons,
    final Map<Point, Gold> golds,
    final List<Point> exits,
    final List<Room> rooms) {

    final List<Room> choices = new LinkedList<>();
    final Set<Point> items = new HashSet<>();

    choices.addAll(rooms);
    items.addAll(monsters.keySet());
    items.addAll(weapons.keySet());
    items.addAll(golds.keySet());

    for (int count = 0; count < this.exitsNumber; count++) {
      final Room room = choices.get(this.randomInt(choices.size()));
      final List<Point> points = this.getRoomPoints(room);

      if (choices.size() > 1) {
        choices.remove(room);
      }

      items.forEach(point -> points.remove(point));
      exits.add(points.get(this.randomInt(points.size())));
    }
  }

  private LooseFloor generateFloor() {
    final Map<Point, Monster> monsters = new HashMap<>();
    final Map<Point, Item> items = new HashMap<>();
    final Map<Point, Weapon> weapons = new HashMap<>();
    final Map<Point, Gold> golds = new HashMap<>();
    final List<Point> exits = new ArrayList<>();
    final List<Room> sections = new ArrayList<>();
    final List<Room> rooms = new ArrayList<>();

    final int xSections = this.randomInt(this.size / this.minSectionSize
      - this.minSideSections + 1) + this.minSideSections;

    final int ySections = this.randomInt(this.size / this.minSectionSize
      - this.minSideSections + 1) + this.minSideSections;

    final int xBound = this.size / xSections - this.minSectionSize + 1;
    final int yBound = this.size / ySections - this.minSectionSize + 1;
    int xBase = 0;
    int yBase = 0;
    int xSize = 0;
    int ySize = 0;

    for (int x = 0; x < xSections; x++) {
      xBase += xSize;
      yBase = 0;
      ySize = 0;

      if (x < xSections - 1) {
        xSize = this.randomInt(xBound) + this.minSectionSize;
      } else {
        xSize = this.size - xBase;
      }

      for (int y = 0; y < ySections; y++) {
        yBase += ySize;

        if (y < ySections - 1) {
          ySize = this.randomInt(yBound) + this.minSectionSize;
        } else {
          ySize = this.size - yBase;
        }

        final Room section = new ImmutableRoom(
          new ImmutablePoint(xBase, yBase),
          new ImmutablePoint(xSize, ySize));

        sections.add(section);
        this.generateRoom(items, section, rooms);
        this.generateInstances(rooms.get(rooms.size() - 1),
          monsters, weapons, golds);
      }
    }

    this.generateTunnels(items, sections, rooms);
    this.generateExits(monsters, weapons, golds, exits, rooms);

    return new LooseFloor(monsters, weapons, golds, items, exits, rooms);
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  @Override
  public Floor getNew(
    final List<Entry<String, String>> floors,
    final List<Player> players) {

    Objects.requireNonNull(floors);
    Objects.requireNonNull(players);

    final int floorsNumber = floors.size();
    final List<LooseFloor> inputFloors = new ArrayList<>();
    final List<Floor> outputFloors = new ArrayList<>();
    final FloorFactory factory = new ImmutableFloorFactory();

    for (int index = 0; index < floorsNumber; index++) {
      inputFloors.add(this.generateFloor());
      outputFloors.add(inputFloors.get(index).getFloor(
        floors.get(index).getKey(),
        floors.get(index).getValue()));
    }

    for (int count = 0; count < floorsNumber; count++) {
      final Map<Point, Floor> exits = inputFloors.get(count).getExits();
      final List<Point> exitPoints = inputFloors.get(count).getExitPoints();
      final Point entry = exitPoints.get(0);
      final Point exit = exitPoints.get(1);

      exits.put(entry, factory.getEntryFloor());
      exits.put(exit, factory.getExitFloor());

      if (count > 0) {
        exits.put(entry, outputFloors.get(count - 1));
      }

      if (count < floorsNumber - 1) {
        exits.put(exit, outputFloors.get(count + 1));
      }
    }

    final Floor first = outputFloors.get(0);

    first.getExits().forEach((point, floor) -> {
      if (Type.ENTRY.equals(floor.getType())) {
        first.enter(players.get(0), floor);
      }
    });

    return first;
  }

  private static final class LooseFloor {

    private final Map<Point, Player> players;
    private final Map<Point, Monster> monsters;
    private final Map<Point, Weapon> weapons;
    private final Map<Point, Gold> golds;
    private final Map<Point, Floor> exits;
    private final Map<Point, Item> items;
    private final List<Point> exitPoints;
    private final List<Room> rooms;

    private LooseFloor(
      final Map<Point, Monster> monsters,
      final Map<Point, Weapon> weapons,
      final Map<Point, Gold> golds,
      final Map<Point, Item> items,
      final List<Point> exits,
      final List<Room> rooms) {

      this.players = new HashMap<>();
      this.monsters = Objects.requireNonNull(monsters);
      this.weapons = Objects.requireNonNull(weapons);
      this.golds = Objects.requireNonNull(golds);
      this.exits = new HashMap<>();
      this.items = Objects.requireNonNull(items);
      this.exitPoints = Objects.requireNonNull(exits);
      this.rooms = Objects.requireNonNull(rooms);
    }

    private Map<Point, Floor> getExits() {
      return this.exits;
    }

    private List<Point> getExitPoints() {
      return this.exitPoints;
    }

    private Floor getFloor(final String name, final String description) {
      return new NormalFloor(
        Objects.requireNonNull(name),
        Objects.requireNonNull(description),
        this.players,
        this.monsters,
        this.weapons,
        this.golds,
        this.exits,
        this.items,
        this.rooms);
    }
  }

  /**
   * The Builder of {@code ImmutableFloorGenerator}.
   */
  public static final class Builder {

    private Optional<Integer> seed = Optional.empty();
    private Optional<Integer> size = Optional.empty();
    private Optional<Integer> minSideSections = Optional.empty();
    private Optional<Integer> maxWallDistance = Optional.empty();
    private Optional<Integer> minRoomSize = Optional.empty();
    private Optional<Integer> maxRoomTunnels = Optional.empty();
    private Optional<Integer> minRoomTunnels = Optional.empty();
    private Optional<Integer> maxSideTunnels = Optional.empty();
    private Optional<Integer> maxRoomMonsters = Optional.empty();
    private Optional<Integer> minRoomMonsters = Optional.empty();
    private Optional<Integer> maxRoomWeapons = Optional.empty();
    private Optional<Integer> maxRoomGolds = Optional.empty();
    private Optional<Integer> exitsNumber = Optional.empty();
    private Optional<List<Template<Monster>>> monsters = Optional.empty();
    private Optional<List<Template<Item>>> items = Optional.empty();
    private Optional<List<Template<Weapon>>> weapons = Optional.empty();
    private Optional<List<Template<Gold>>> golds = Optional.empty();

    /**
     * Sets the seed.
     *
     * @param value the value
     * @return this object
     */
    public Builder seed(final int value) {
      this.seed = Optional.of(value);
      return this;
    }

    /**
     * Sets the size of the floor.
     *
     * @param value the value
     * @return this object
     */
    public Builder size(final int value) {
      this.size = Optional.of(value);
      return this;
    }

    /**
     * Sets the minimum sections number of each side.
     *
     * @param value the value
     * @return this object
     */
    public Builder minSideSections(final int value) {
      this.minSideSections = Optional.of(value);
      return this;
    }

    /**
     * Sets the maximum wall distance from the section base.
     *
     * @param value the value
     * @return this object
     */
    public Builder maxWallDistance(final int value) {
      this.maxWallDistance = Optional.of(value);
      return this;
    }

    /**
     * Sets the minimum room size.
     *
     * @param value the value
     * @return this object
     */
    public Builder minRoomSize(final int value) {
      this.minRoomSize = Optional.of(value);
      return this;
    }

    /**
     * Sets the maximum number of tunnels from a room.
     *
     * @param value the value
     * @return this object
     */
    public Builder maxRoomTunnels(final int value) {
      this.maxRoomTunnels = Optional.of(value);
      return this;
    }

    /**
     * Sets the minimum number of tunnels from a room.
     *
     * @param value the value
     * @return this object
     */
    public Builder minRoomTunnels(final int value) {
      this.minRoomTunnels = Optional.of(value);
      return this;
    }

    /**
     * Sets the maximum number of tunnels on a room side.
     *
     * @param value the value
     * @return this object
     */
    public Builder maxSideTunnels(final int value) {
      this.maxSideTunnels = Optional.of(value);
      return this;
    }

    /**
     * Sets the maximum number of monsters inside a room.
     *
     * @param value the value
     * @return this object
     */
    public Builder maxRoomMonsters(final int value) {
      this.maxRoomMonsters = Optional.of(value);
      return this;
    }

    /**
     * Sets the minimum number of monsters inside a room.
     *
     * @param value the value
     * @return this object
     */
    public Builder minRoomMonsters(final int value) {
      this.minRoomMonsters = Optional.of(value);
      return this;
    }

    /**
     * Sets the maximum number of weapons inside a room.
     *
     * @param value the value
     * @return this object
     */
    public Builder maxRoomWeapons(final int value) {
      this.maxRoomWeapons = Optional.of(value);
      return this;
    }

    /**
     * Sets the maximum number of golds inside a room.
     *
     * @param value the value
     * @return this object
     */
    public Builder maxRoomGolds(final int value) {
      this.maxRoomGolds = Optional.of(value);
      return this;
    }

    /**
     * Sets the number of exits in a floor.
     *
     * @param value the value
     * @return this object
     */
    public Builder exitsNumber(final int value) {
      this.exitsNumber = Optional.of(value);
      return this;
    }

    /**
     * Sets the list of monster templates.
     *
     * @param list the list
     * @return this object
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public Builder monsters(final List<Template<Monster>> list) {
      this.monsters = Optional.of(list);
      return this;
    }

    /**
     * Sets the list of item templates.
     *
     * @param list the list
     * @return this object
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public Builder items(final List<Template<Item>> list) {
      this.items = Optional.of(list);
      return this;
    }

    /**
     * Sets the list of weapon templates.
     *
     * @param list the list
     * @return this object
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public Builder weapons(final List<Template<Weapon>> list) {
      this.weapons = Optional.of(list);
      return this;
    }

    /**
     * Sets the list of gold templates.
     *
     * @param list the list
     * @return this object
     * @throws NullPointerException if {@code list} is {@code null}
     */
    public Builder golds(final List<Template<Gold>> list) {
      this.golds = Optional.of(list);
      return this;
    }

    /**
     * Builds a new {@code ImmutableFloorGenerator} with the builder settings.
     *
     * @return the generator
     * @throws NoSuchElementException if one of the settings is not set
     * @throws IllegalArgumentException if one of
     *         {@code minSideSections} or
     *         {@code maxSideTunnels} is &lt; 1
     * @throws IllegalArgumentException if one of
     *         {@code maxWallDistance},
     *         {@code minRoomSize},
     *         {@code maxRoomTunnels},
     *         {@code minRoomTunnels},
     *         {@code maxRoomMonsters},
     *         {@code maxRoomWeapons},
     *         {@code maxRoomGolds} or
     *         {@code exitsNumber} is &lt; 0
     * @throws IllegalArgumentException if {@code items} doesn't contain at
     *         least one blocking and one non-blocking item
     * @throws IllegalArgumentException if {@code size} is &lt; 16
     * @throws IllegalArgumentException if {@code size} is &lt;
     *         {@code maxWallDistance} + {@code minRoomSize}
     * @throws IllegalArgumentException if
     *         {@code size} / {@code minSideSections} is &lt;
     *         {@code maxWallDistance} + {@code minRoomSize}
     * @throws IllegalArgumentException if
     *         {@code maxRoomMonsters} + {@code maxRoomWeapons} +
     *         {@code maxRoomGolds} + 1 (exit) is &gt;
     *         ({@code minRoomSize} - 2) ^ 2
     * @throws IllegalArgumentException if
     *         {@code maxRoomMonsters} is &lt; {@code minRoomMonsters}
     * @throws IllegalArgumentException if
     *         {@code maxRoomTunnels} is &lt; {@code minRoomTunnels}
     */
    public ImmutableFloorGenerator build() {
      return new ImmutableFloorGenerator(
        this.seed.get(),
        this.size.get(),
        this.minSideSections.get(),
        this.maxWallDistance.get(),
        this.minRoomSize.get(),
        this.maxRoomTunnels.get(),
        this.minRoomTunnels.get(),
        this.maxSideTunnels.get(),
        this.maxRoomMonsters.get(),
        this.minRoomMonsters.get(),
        this.maxRoomWeapons.get(),
        this.maxRoomGolds.get(),
        this.exitsNumber.get(),
        this.monsters.get(),
        this.items.get(),
        this.weapons.get(),
        this.golds.get());
    }
  }
}
