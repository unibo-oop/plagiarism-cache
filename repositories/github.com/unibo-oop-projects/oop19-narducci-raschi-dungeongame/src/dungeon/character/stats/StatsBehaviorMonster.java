package dungeon.character.stats;

/**
 * The Interface StatsBehaviorMonster.
 */
public interface StatsBehaviorMonster {

  /**
   * Exp leaved calcolator.
   *
   * @param level the level
   * @return the experience acquired
   */
  int expLeavedCalcolator(int level);

  /**
   * Stats for level.
   *
   * @param level the level
   * @param stats the stats
   */
  void statsForLevel(int level, Stats stats);
}
