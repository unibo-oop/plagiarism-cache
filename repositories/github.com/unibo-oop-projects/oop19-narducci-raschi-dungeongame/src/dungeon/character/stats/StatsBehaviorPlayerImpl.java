package dungeon.character.stats;
/**
 * An implementation of {@code StatsBehaviorPlayerImpl}.
 */
public final class StatsBehaviorPlayerImpl implements StatsBehaviorPlayer {

  /** The Constant HP_MODIFIER. */
  private static final int HP_MODIFIER = 5;

  /** The Constant EXP_LEVEL_UP. */
  private static final int EXP_LEVEL_UP = 10;


  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int expToLevelUp(final int level) {
    return level * EXP_LEVEL_UP; 
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void statsLevelUp(final Stats stats) {
    stats.setStats(stats.getAtk() + 1,
        stats.getDef() + 1, stats.getHp() + HP_MODIFIER);
  }

}
