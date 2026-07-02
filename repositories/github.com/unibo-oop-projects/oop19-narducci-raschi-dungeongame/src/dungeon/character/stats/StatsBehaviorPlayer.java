package dungeon.character.stats;

/**
 * The Interface StatsBehaviorPlayer.
 */
public interface StatsBehaviorPlayer {

  /**
   * Exp to level up.
   *
   * @param level the level
   * @return the int
   */
  int expToLevelUp(int level);

  /**
   * increase Stats after level up.
   *
   * @param stats the stats
   */
  void statsLevelUp(Stats stats);
}
