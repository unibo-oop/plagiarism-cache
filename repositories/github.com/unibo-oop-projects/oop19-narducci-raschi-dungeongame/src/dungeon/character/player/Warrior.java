/*
 * 
 */
package dungeon.character.player;
import java.util.Objects;
import java.util.Optional;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.character.Character;
import dungeon.character.stats.Stats;
import dungeon.character.stats.StatsBehaviorPlayer;
import dungeon.character.stats.StatsBehaviorPlayerImpl;
import dungeon.floor.Floor;
import dungeon.point.Point;
import dungeon.template.ImmutableTemplateFactory;
import dungeon.weapon.HitCheck;
import dungeon.weapon.Weapon;

/**
 * An implementation of {@code Player}, make the player attack.
 */
public final class Warrior implements Player {

  /** The floor. */
  private Optional<Floor> floor;

  /** The point. */
  private Optional<Point> point;

  /** The inventory. */
  private Inventory inventory; 

  /** The breed. */
  private Character breed;

  /** The exp. */
  private int exp; 

  /** The weapon. */
  private Weapon weapon;

  /** The player mode. */
  private Mode playerMode;

  /** The factory. */
  private ImmutableTemplateFactory factory;

  /** The stats behavior. */
  private StatsBehaviorPlayer statsBehavior;

  /** The level. */
  private int level;

  /**
   * Instantiates a new warrior.
   *
   * @param breed the breed
   */
  public Warrior(final Character breed) {
    Objects.requireNonNull(breed);
    this.floor = Optional.empty();
    this.point = Optional.empty();
    factory = new ImmutableTemplateFactory();
    this.breed = breed;
    this.inventory = new InventoryImpl();
    this.exp = 0;
    weapon = factory.getWarriorSword().getInstance(Optional.empty());
    playerMode = new AttackMode();
    statsBehavior = new StatsBehaviorPlayerImpl();
    this.level = 1;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Point getPoint() {
    return this.point.get();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setFloor(final Floor floor) {
    Objects.requireNonNull(floor);
    this.floor = Optional.of(floor);
    this.floor.get().getMonsters().values()
      .forEach(monster -> monster.setFloor(floor));
    weapon = new HitCheck(floor, weapon);

  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setPoint(final Point point) {
    Objects.requireNonNull(point);
    this.point = Optional.of(point);
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Optional<Floor> move(final Point to) {
    Objects.requireNonNull(to);
    if (!this.getInventory().isOpen()) {
      Objects.requireNonNull(to);

      final Floor floor = this.floor.get();

      floor.move(this.point.get(), to);
      if (floor.getPlayers().get(this.point.get()) == null) {
        this.point = Optional.of(to);

      }
      return Optional.of(floor);
    }
    return Optional.empty();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeOrAttack(final Point pos) {
    Objects.requireNonNull(pos);
    if (!this.getInventory().isOpen()) {
      playerMode.takeAction(pos, this, floor.get());
    } 
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getExp() {
    return exp;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setExp(final int exp) {
    Utilities.requirePositive(exp);
    this.exp = exp;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Weapon getWeapon() {
    return weapon;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Character getBreed() {
    return breed;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void changeMode(final Mode mode) {
    this.playerMode = mode;
    System.out.println("Changed to " + mode.toString());
  }

 
  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void levelUp() {
    while (statsBehavior.expToLevelUp(level) < this.exp) {
      this.level = level + 1;
      this.exp = this.exp - statsBehavior.expToLevelUp(level - 1);
      statsBehavior.statsLevelUp(breed.getStats());
    }
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeItem(final int i) {
    if (i < this.getInventory().getWeaponInventory().size()) {
      Weapon weapon = this.getInventory().getWeaponInventory().get(i);
      this.setWeapon(weapon);
      this.getInventory().getWeaponInventory().remove(i);
    }
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setWeapon(final Weapon weapon) {
    Objects.requireNonNull(weapon);
    this.weapon = weapon;
    this.weapon = new HitCheck(floor.get(), weapon); 
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return breed.getName();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    return breed.getDescription();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public boolean isBlocking() {
    return breed.isBlocking();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Cell getCell() {
    return Cell.PLAYER;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void removeItem(final int i) {
    if (i < this.getInventory().getWeaponInventory().size()) {
      this.getInventory().getWeaponInventory().remove(i);
    }
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Stats getStats() {
    return breed.getStats();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getLevel() {
    return this.level;
  }

}
