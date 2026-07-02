package dungeon.logic;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import dungeon.Cell;
import dungeon.Direction;
import dungeon.GuiUtilities;
import dungeon.character.CharacterImpl;
import dungeon.character.monster.Monster;
import dungeon.character.player.Mode;
import dungeon.character.player.Player;
import dungeon.character.player.Warrior;
import dungeon.character.stats.StatsImpl;
import dungeon.floor.Floor;
import dungeon.floor.Floor.Type;
import dungeon.floor.FloorGenerator;
import dungeon.floor.ImmutableFloorGenerator;
import dungeon.gui.GuiLogic;
import dungeon.movelogic.Utilities;
import dungeon.point.Point;
import dungeon.template.ImmutableTemplateFactory;
import dungeon.template.TemplateFactory;

/**.
 * An implementation of {@code Logic}, is the Logic of the game
 */
public final class GameLogicImpl implements GameLogic {


  /** The player. */
  private Player player;

  /** The floors visited. */
  private Set<Floor> floorsVisited;

  /** The gui logic. */
  private final GuiLogic guiLogic;

  private boolean bossFaced;
  private final Random random;

  /**
   * Instantiates a new game logic.
   *
   * @param guiLogic the gui logic
   */
  public GameLogicImpl(final GuiLogic guiLogic) {
    this.random = new Random();
    this.guiLogic = guiLogic;
    floorsVisited = new HashSet<>();
    bossFaced = false;
  }

  /**
   * Gets the player position.
   *
   * @return the player position
   */
  private Point getPlayerPosition() {
    return player.getPoint();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void movePlayer(final Direction direction) {
    Point to = this.getPlayerPosition().move(direction.getModifier());
    Optional<Floor> floor = player.move(to);
    if (floor.isPresent()) {
      this.increaseLevelDungeon(to, floor);
    }
    if (this.getPlayerPosition() == to) {
      moveMonster(floor.get());
      if (player.getBreed().isDead()) {
        System.out.println(player.getBreed().getName() + " is dead");
        this.playerDead(floor);
      }
    }
    guiLogic.updatePlayerInfo(player.getName(),
        player.getStats().toString(),
        String.valueOf(player.getInventory().getGold()),
        player.getWeapon().getName(),
        String.valueOf(player.getBreed().getHealthRemained()));
  }


  private void playerDead(final Optional<Floor> floor) {
    floor.get().remove(player.getPoint());
    guiLogic.drawEndGame("Oh no " + player.getName() + "has lost");
  }


  private void moveMonster(final Floor floor) {
    final List<Monster> monsters = new ArrayList<>();
    floor.getMonsters().values().forEach(monster -> monsters.add(monster));
    monsters.forEach(monster -> monster.takeTurn(player));
  }


  private void increaseLevelDungeon(final Point point,
      final Optional<Floor> floor) {
    if (floor.get().getExits().containsKey(point)) {
      Floor.Type exit = floor.get().getExits().get(point).getType();
      if (floor.get().getMonsters().values().stream()
          .anyMatch(a -> a.getCell().equals(Cell.BOSS))) {
        System.out.println("Beat the boss first");
        return;
      }
      if (exit.equals(Type.ENTRY)
          && !floorsVisited.contains(floor.get())) {
        return;
      }
      if (exit.equals(Type.EXIT) && !bossFaced) {
        bossFaced = true;
        this.generateBossSet();
      } else if (exit.equals(Type.EXIT) 
          && bossFaced) {
        guiLogic.drawEndGame("Good job, " + player.getName() + " has won");
      } else {
        floor.get().exit(this.getPlayerPosition(), point);
        this.checkMonsterPosition(floor.get());
      }
      floorsVisited.add(floor.get());
    }
    return;
  }

  private void checkMonsterPosition(final Floor floor) {
    Utilities.repositionMonsters(floor);
  }

  private void generateBossSet() {
    guiLogic.clearMap();
    final TemplateFactory factory = new ImmutableTemplateFactory();
    FloorGenerator generator = new ImmutableFloorGenerator.Builder()
        .seed(random.nextInt())
        .size(GuiUtilities.SIZE)
        .minSideSections(1)
        .maxWallDistance(2)
        .minRoomSize(GuiUtilities.SIZE - 2)
        .maxRoomTunnels(0)
        .minRoomTunnels(0)
        .maxSideTunnels(1)
        .maxRoomMonsters(1)
        .minRoomMonsters(1)
        .maxRoomWeapons(0)
        .maxRoomGolds(0)
        .exitsNumber(2)
        .monsters(List.of(factory.getDragon()))
        .items(List.of())
        .items(List.of(
          factory.getWall(),
          factory.getFakeWall(),
          factory.getDoor()))
        .weapons(List.of())
        .golds(List.of())
        .build();
    final Floor floor = guiLogic.generateLevel(
        generator.getNew(List.of(Map.entry("Boss Floor", "The Boss Floor")),
        List.of(player)));

    player.setFloor(floor); 
    guiLogic.revalidateMap();
    floor.getMonsters().values()
    .forEach(a -> a.setRandomLevel(floorsVisited.size()));
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeOrAttack(final Direction direction) {
    player.takeOrAttack(this.getPlayerPosition().move(direction.getModifier()));
    this.updatePlayerInfo();
  } 

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setPlayerMode(final Mode mode) {
    player.changeMode(mode);
    this.updatePlayerInfo();
  }

  private void updatePlayerInfo() {
    guiLogic.updatePlayerInfo(player.getName(),
        player.getStats().toString(),
        String.valueOf(player.getInventory().getGold()),
        player.getWeapon().getName(),
        String.valueOf(player.getBreed().getHealthRemained()));
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void removeItem(final int i) {
    player.removeItem(i);
    this.updatePlayerInfo();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeItem(final int i) {
    player.takeItem(i);
    this.updatePlayerInfo();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Optional<URL> getUrl(final int i) {
    if (i < player.getInventory().getNelement()) {
      return Optional.of(player.getInventory().getWeaponInventory()
          .get(i).getCell().getImageURL());
    }
    return Optional.empty();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String getItemDescription(final int i) {
    return player.getInventory().getWeaponInventory().get(i).getDescription();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setInventoryOpen() {
    player.getInventory().setOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Floor createNew(final String name) {
    List<Entry<String, String>> floors = List.of(
        Map.entry("First Floor", "The initial floor"),
        Map.entry("Second Floor", "The middle floor"),
        Map.entry("Third Floor", "The last floor"));

    final TemplateFactory factory = new ImmutableTemplateFactory();

    FloorGenerator generator = new ImmutableFloorGenerator.Builder()
      .seed(random.nextInt())
      .size(GuiUtilities.SIZE)
      .minSideSections(2)
      .maxWallDistance(2)
      .minRoomSize(6)
      .maxRoomTunnels(3)
      .minRoomTunnels(1)
      .maxSideTunnels(2)
      .minRoomMonsters(0)
      .maxRoomMonsters(2)
      .maxRoomWeapons(2)
      .maxRoomGolds(2)
      .exitsNumber(2)
      .monsters(List.of(factory.getArcherGoblin(),
          factory.getSwordGoblin(),
          factory.getBeast()))
      .items(List.of(
        factory.getWall(),
        factory.getFakeWall(),
        factory.getDoor()))
      .weapons(List.of(
        factory.getBrokenSword(),
        factory.getWarriorSword(),
        factory.getHunterBow()))
      .golds(List.of(factory.getSomeGold(), factory.getMoreGold()))
      .build();

    Player player = new Warrior(new CharacterImpl(
        new StatsImpl(8000, 11, 12), name, "A proud fighter"));

    this.player = player;
    return generator.getNew(floors, List.of(player));
  }
}
