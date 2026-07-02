package dungeon.character.stats;
/**
 * An implementation of {@code StatsBehaviorMonster}, f.
 */
public final class StatsBehaviorMonsterMedium implements StatsBehaviorMonster {

  /** The Constant HP_MODIFIER. */
  private static final int HP_MODIFIER = 3;

  /** The Constant EXP_LEAVED_MULTIPLIER. */
  private static final int EXP_LEAVED_MULTIPLIER = 7;

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void statsForLevel(final int level, final Stats stats) {
    stats.setStats(stats.getAtk() + level / 2,
        stats.getDef() + level / 2, stats.getHp() + HP_MODIFIER * (level - 1));
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
