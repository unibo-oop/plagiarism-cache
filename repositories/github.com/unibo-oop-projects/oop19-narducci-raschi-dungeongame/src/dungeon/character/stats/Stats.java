package dungeon.character.stats;

/**
 * The Interface Stats.
 */
public interface Stats {

  /**
   * Gets the def.
   *
   * @return the def
   */
  int getDef();

  /**
   * Gets the atk.
   *
   * @return the atk
   */
  int getAtk();

  /**
   * Gets the hp.
   *
   * @return the hp
   */
  int getHp();

  /**
   * Sets the def.
   *
   * @param def the new def
   */
  void setDef(int def);

  /**
   * Sets the atk.
   *
   * @param atk the new atk
   */
  void setAtk(int atk);

  /**
   * Sets the hp.
   *
   * @param hp the new hp
   */
  void setHp(int hp);

  /**
   * Sets the stats.
   *
   * @param atk the atk
   * @param def the def
   * @param hp the hp
   */
  void setStats(int atk, int def, int hp);
}
