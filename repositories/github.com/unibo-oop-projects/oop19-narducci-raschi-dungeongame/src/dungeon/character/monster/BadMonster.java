package dungeon.character.monster;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.attack.Attack;
import dungeon.attack.Attack.Result;
import dungeon.character.Character;
import dungeon.character.player.Player;
import dungeon.character.stats.StatsBehaviorMonster;
import dungeon.floor.Floor;
import dungeon.movelogic.MoveLogic;
import dungeon.movelogic.MoveWhenSameRoom;
import dungeon.point.Point;
import dungeon.weapon.HitCheck;
import dungeon.weapon.Weapon;

/**
 * The Class BadMonster.
 */
public final class BadMonster extends GenericMonster {

  /** The behavior. */
  private final StatsBehaviorMonster behavior;

  /** The weapon. */
  private Weapon weapon;
  /** The point. */
  private Optional<Point> point;

  /** The floor. */
  private Optional<Floor> floor;

  /** The logic. */
  private Optional<MoveLogic> logic;

  /** The spawn. */
  private Optional<Point> spawn;

  /**
   * Instantiates a new bad monster.
   *
   * @param breed the breed
   * @param weapon the weapon
   * @param behavior the behavior
   * @param frequency the frequency
   * @param level the level
   */
  public BadMonster(final Character breed, final Weapon weapon,
      final StatsBehaviorMonster behavior,
      final int frequency, final int level) {
    super(breed, frequency, level);
    Objects.requireNonNull(weapon);
    Objects.requireNonNull(behavior);
    this.behavior = behavior;
    behavior.statsForLevel(level, breed.getStats());
    this.weapon = weapon;
    this.point = Optional.empty();
    this.floor = Optional.empty();
    this.logic = Optional.empty();
    this.spawn = Optional.empty();
  }

  /**
   * Instantiates a new bad monster.
   *
   * @param breed the breed
   * @param weapon the weapon
   * @param behavior the behavior
   * @param frequency the frequency
   * @param level the level
   * @param point the point
   */
  public BadMonster(final Character breed, final Weapon weapon,
      final StatsBehaviorMonster behavior,
      final int frequency, final int level,
      final Optional<Point> point) {

    this(breed, weapon, behavior, frequency, level);
    this.point = point;
    this.spawn = point;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Monster getInstance(final Optional<Point> point) {
    return new BadMonster(getBreed()
        .getInstance(getBreed().getHealthRemained()),
        weapon, behavior, getFrequency(), getLevel(),
        Objects.requireNonNull(point));
  }

  /**
   * Move.
   */
  private void move() {
    final Point point = this.point.get();
    this.point = Optional.of(this.logic.get().move());
    floor.get().move(point, this.point.get());
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getReleasedExp() {
    return behavior.expLeavedCalcolator(getLevel());
  }

  /**
   * Can hit.
   *
   * @param pointMob the point mob
   * @param pointPlayer the point player
   * @return true, if successful
   */
  private boolean canHit(final Point pointMob, final Point pointPlayer) {
    return pointMob.getX() == pointPlayer.getX()
        || pointMob.getY() == pointPlayer.getY();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeTurn(final Player player) {
    if (this.canHit(this.point.get(), player.getPoint())) {
      Attack attack = weapon.hit(getBreed().getName(),
          this.point.get(), player.getPoint());
      if (attack.getResult().equals(Result.INVALID)) {
        this.move();
      } else {
        System.out.println(this.attack(player, attack));
      }
    } else {
      this.move();
    }
  }

  /**
   * Calcolate extra damege.
   *
   * @param player the player
   * @return the int
   */
  private int calcolateExtraDamege(final Player player) {
    int extraDamage = ((int) (getBreed().getStats().getAtk() 
        * Utilities.ATTACK_MULTIPLIER
        - player.getBreed().getStats().getDef()));
    extraDamage = extraDamage <= 0 ? 1 : extraDamage;
    return extraDamage; 
  }

  /**
   * Attack.
   *
   * @param player the player
   * @param attack the attack
   * @return the string
   */
  private String attack(final Player player, final Attack attack) {
    weapon.setExtraDamage(this.calcolateExtraDamege(player));
    if (attack.getResult().equals(Result.HIT)) {
      player.getBreed().setHealthRemained(attack.getDamage().get());
    }
    return attack.getDescription();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setRandomLevel(final int nLevel) {
    Utilities.requireGreaterThanZero(nLevel);
    Random rand = new Random();
    super.setLevel(Math.abs(rand.nextInt() - GenericMonster.LEVEL_RANGE) 
        + GenericMonster.LEVEL_RANGE);
    behavior.statsForLevel(super.getLevel(), this.getBreed().getStats());
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setFloor(final Floor floor) {
    this.floor = Optional.of(floor);
    this.logic = Optional.of(
      new MoveWhenSameRoom(floor, this.point.get()));
    this.weapon = new HitCheck(floor, weapon);
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Cell getCell() {
    return Cell.MOB;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Point getSpawn() {
    return spawn.get();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setPoint(final Point spawn) {
    this.point = Optional.of(spawn);
  }
}
