package dungeon.character.stats;

import dungeon.Utilities;

/**
 * The Class StatsImpl.
 */
public final class StatsImpl implements Stats {

  /** The hp. */
  private int hp;

  /** The atk. */
  private int atk;

  /** The def. */
  private int def;

  /**
   * Instantiates a new stats impl.
   *
   * @param hp the hp
   * @param atk the atk
   * @param def the def
   */
  public StatsImpl(final int hp, final int atk, final int def) {
    Utilities.requireGreaterThanZero(hp);
    Utilities.requireGreaterThanZero(atk);
    Utilities.requireGreaterThanZero(def);
    this.hp = hp;
    this.atk = atk;
    this.def = def;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getDef() {
    return def;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getAtk() {
    return atk;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getHp() {
    return hp;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setDef(final int def) {
    this.def = def;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setAtk(final int atk) {
    this.atk = atk;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setHp(final int hp) {
    this.hp = hp;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setStats(final int atk, final int def, final int hp) {
    this.atk = atk;
    this.hp = hp;
    this.def = def;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "HP: " + this.hp 
        + "\n ATK: " + this.atk 
        + "\n DEF: " + this.def + "\n";
  }

}
