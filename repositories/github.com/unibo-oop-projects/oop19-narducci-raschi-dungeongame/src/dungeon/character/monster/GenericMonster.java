package dungeon.character.monster;

import java.util.Objects;

import dungeon.Utilities;
import dungeon.character.Character;
import dungeon.template.Template;

/**
 * The Class GenericMonster.
 */
public abstract class GenericMonster implements Template<Monster>, Monster {

  /** The Constant LEVEL_RANGE. */
  protected static final int LEVEL_RANGE = 2;

  /** The breed. */
  private final Character breed;

  /** The frequency. */
  private final int frequency;

  /** The level. */
  private int level;

  /**
   * Instantiates a new generic monster.
   *
   * @param breed the breed
   * @param frequency the frequency
   * @param level the level
   */
  public GenericMonster(final Character breed,
      final int frequency, final int level) {
    Objects.requireNonNull(breed);
    Utilities.requireFrequency(frequency);
    Utilities.requireGreaterThanZero(level);
    this.breed = breed;
    this.frequency = frequency;
    this.level = level;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public final Character getBreed() {
    return this.breed;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public final int getFrequency() {
    return frequency;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return this.breed.getName();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public final String getDescription() {
    return this.getBreed().getDescription();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public final boolean isBlocking() {
    return true;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  public final int getLevel() {
    return level;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  protected final void setLevel(final int level) {
    this.level = level;
  }

}
