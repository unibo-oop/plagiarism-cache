package dungeon.character.stats;

/**
 * An implementation of {@code StatsBehaviorMonster}, for boss.
 */
public final class StatsBehaviorMonsterBoss implements StatsBehaviorMonster {

  /** The Constant EXP_LEAVED_MULTIPLIER. */
  private static final int EXP_LEAVED_MULTIPLIER = 10;

  /** The Constant HP_MODIFIER. */
  private static final int HP_MODIFIER = 4;

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void statsForLevel(final int level, final Stats stats) {
    stats.setStats(stats.getAtk() + level,
       stats.getDef() + level, stats.getHp() + HP_MODIFIER * (level - 1));
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int expLeavedCalcolator(final int level) {
    return level * EXP_LEAVED_MULTIPLIER;
  }
}
