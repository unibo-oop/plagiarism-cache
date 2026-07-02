package dungeon.character.monster;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.attack.Attack;
import dungeon.attack.Attack.Result;
import dungeon.character.Character;
import dungeon.character.CharacterImpl;
import dungeon.character.Pair;
import dungeon.character.player.Player;
import dungeon.character.stats.StatsBehaviorMonsterBoss;
import dungeon.floor.Floor;
import dungeon.movelogic.MoveLogic;
import dungeon.movelogic.MoveWhenSameRoom;
import dungeon.point.Point;
import dungeon.weapon.HitCheck;
import dungeon.weapon.Weapon;


/**
 * The Class BossMonster.
 */
public final class BossMonster extends GenericMonster {

  /** The Constant MIN_CONSECUTIVE_TURN. */
  private static final int MIN_CONSECUTIVE_TURN = 3;
  /** The Constant MAX_CONSECUTIVE_TURN. */
  private static final int MAX_CONSECUTIVE_TURN = 5;

  /** The Constant REST_TURN. */
  private static final int REST_TURN = 2;

  /** The behavior. */
  private final StatsBehaviorMonsterBoss behavior;

  /** The weapon. */
  private Set<Weapon> weapon;

  /** The point. */
  private Optional<Point> point;

  /** The floor. */
  private Optional<Floor> floor;

  /** The logic. */
  private Optional<MoveLogic> logic;

  /** The turn remained. */
  private int turnRemained;

  /** The turn sleep. */
  private int turnSleep;

  /** The spawn. */
  private Optional<Point> spawn;

  /**
   * Instantiates a new boss monster.
   *
   * @param breed the breed
   * @param frequency the frequency
   * @param level the level
   * @param weapon the weapon
   */
  public BossMonster(final Character breed, final int frequency,
      final int level, final Set<Weapon> weapon) {
    super(breed, frequency, level);
    this.behavior = new StatsBehaviorMonsterBoss();
    behavior.statsForLevel(level, breed.getStats());
    this.weapon = weapon;
    this.point = Optional.empty();
    this.floor = Optional.empty();
    this.logic = Optional.empty();
    turnRemained = this.calculateTurnNumber();
    turnSleep = 0;
    this.spawn = Optional.empty();
  }

  /**
   * Calculate turn number.
   *
   * @return the int
   */
  private int calculateTurnNumber() {
    Random x = new Random();
    return x.nextInt(MAX_CONSECUTIVE_TURN 
        - MIN_CONSECUTIVE_TURN) + MIN_CONSECUTIVE_TURN;
  }

  /**
   * Instantiates a new boss monster.
   *
   * @param breed the breed
   * @param frequency the frequency
   * @param level the level
   * @param weapon the weapon
   * @param point the point
   */
  public BossMonster(final CharacterImpl breed, final int frequency,
      final int level, final Set<Weapon> weapon,
      final Optional<Point> point) {
    this(breed, frequency, level, weapon);
    this.point = point;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Monster getInstance(final Optional<Point> point) {
    return new BossMonster(getBreed()
        .getInstance(getBreed().getHealthRemained()),
        getFrequency(), getLevel(), weapon, Objects.requireNonNull(point));
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
      new MoveWhenSameRoom(this.floor.get(), this.point.get()));
    this.weapon.stream().forEach(a -> new HitCheck(floor, a));
  }

  /**
   * Move.
   */
  private void move() {
    final Point point = this.point.get();
    this.point = Optional.of(this.logic.get().move());
    this.floor.get().move(point, this.point.get());
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
    if (turnRemained == 0 && turnSleep == 0) {
      turnRemained = this.calculateTurnNumber();
      turnSleep = REST_TURN;
    }
    if (turnRemained != 0) {
      System.out.println(this.getName() + "Is awake ");
      turnRemained--;
      if (!this.canHit(this.point.get(), player.getPoint())) {
        this.move();
        return;
      }
      Optional<Pair<Weapon, Attack>> attack = weapon.stream()
          .map(a -> new Pair<Weapon, Attack>(a,
              a.hit(getBreed().getName(), point.get(), player.getPoint()))) 
          .filter(a -> (a.getValue().getResult().equals(Result.HIT)
              || a.getValue().getResult().equals(Result.MISS)))
          .findAny();
      if (attack.isEmpty()) {
        this.move();
      } else {
        this.attack(player, attack.get());
      }
    } else {
      turnSleep--;
      System.out.println(super.getBreed().getName() + " is tired ");
    }
  }

  /**
   * Calcolate extra damege.
   *
   * @param player the player
   * @return the int
   */
  private int calcolateExtraDamege(final Player player) {
    int extraDamage = ((int) (this.getBreed().getStats().getAtk() 
        * Utilities.ATTACK_MULTIPLIER
        - player.getStats().getDef()));
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
  private String attack(final Player player,
      final Pair<Weapon, Attack> attack) {
    attack.getKey().setExtraDamage(this.calcolateExtraDamege(player));
    if (attack.getValue().getResult().equals(Result.HIT)) {
      player.getBreed().setHealthRemained(attack.getValue().getDamage().get());
    } 
    return attack.getValue().getDescription();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setRandomLevel(final int nLevel) {
    Random rand = new Random();
    super.setLevel(rand.nextInt(
        Math.abs(nLevel - GenericMonster.LEVEL_RANGE) + 1) 
        + GenericMonster.LEVEL_RANGE);
    behavior.statsForLevel(super.getLevel(), this.getBreed().getStats());
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Cell getCell() {
    return Cell.BOSS;
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
