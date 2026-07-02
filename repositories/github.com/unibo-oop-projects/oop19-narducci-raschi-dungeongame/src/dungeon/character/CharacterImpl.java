package dungeon.character;

import java.util.Objects;

import dungeon.Utilities;
import dungeon.character.stats.Stats;
/**
 * An implementation of {@code Character}, create a base Character.
 */


/**
 * The Class CharacterImpl.
 */
public class CharacterImpl implements Character {

  /** The stats. */
  private final Stats stats;

  /** The health remained. */
  private int healthRemained;

  /** The name. */
  private final String name;

  /** The description. */
  private final String description;

  /**
   * Instantiates a new character impl.
   *
   * @param stats the stats
   * @param name the name
   * @param description the description
   */
  public CharacterImpl(final Stats stats, final String name, 
      final String description) {
    this.stats = Objects.requireNonNull(stats);
    this.healthRemained = stats.getHp();
    this.name = Objects.requireNonNull(name);
    this.description = Objects.requireNonNull(description);
  }

  /**
   * Instantiates a new character impl.
   *
   * @param stats the stats
   * @param name the name
   * @param description the description
   * @param hpRemained the hp remained
   */
  public CharacterImpl(final Stats stats, final String name,
      final String description, final int hpRemained) {
    this(stats, name, description);
    this.healthRemained = Objects.requireNonNull(hpRemained);
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public CharacterImpl getInstance(final int hpRemained) {
    return new CharacterImpl(stats, name, description, hpRemained);
  }


  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public boolean isBlocking() {
    return true;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public Stats getStats() {
    return stats;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getHealthRemained() {
    return healthRemained;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public boolean isDead() {
    return this.healthRemained <= 0 ? true : false;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setHealthRemained(final int damage) {
    Utilities.requirePositive(damage);
    healthRemained = this.healthRemained - damage;
  }
}
