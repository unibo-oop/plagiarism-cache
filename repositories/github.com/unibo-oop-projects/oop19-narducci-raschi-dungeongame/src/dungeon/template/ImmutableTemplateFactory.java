package dungeon.template;

import dungeon.Cell;
import dungeon.character.CharacterImpl;
import dungeon.character.monster.BadMonster;
import dungeon.character.monster.BossMonster;
import dungeon.character.monster.Monster;
import dungeon.character.stats.StatsBehaviorMonsterMedium;
import dungeon.character.stats.StatsBehaviorMonsterWeak;
import dungeon.character.stats.StatsImpl;
import dungeon.gold.Gold;
import dungeon.gold.ImmutableGold;
import dungeon.item.ImmutableItem;
import dungeon.item.Item;
import dungeon.weapon.Bow;
import dungeon.weapon.Sword;
import dungeon.weapon.Weapon;
import java.util.Optional;
import java.util.Set;

// You do not silence checkstyle like this. MagicNumber OFF

/**
 * An immutable implementation of {@code TemplateFactory}.
 */
public final class ImmutableTemplateFactory implements TemplateFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Item> getWall() {
    return new ImmutableItem("Wall", "A stone wall", true, Cell.WALL, 100);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Item> getFakeWall() {
    return new ImmutableItem("Wall", "A stone wall", false, Cell.WALL, 5);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Item> getDoor() {
    return new ImmutableItem("Door", "A wooden door", false, Cell.DOOR, 95);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getFists() {
    return new Sword(
      "Fists",
      "The player fists.",
      1, 100, 90, 0, 2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getBrokenSword() {
    return new Sword(
      "Broken Sword",
      "Probably a useless sword",
      1, 100, 100, 0, 0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getTrampSword() {
    return new Sword(
      "Tramp Sword",
      "A cheap sword, deals little damage and has low accuracy",
      1, 90, 50, 0, 3);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getWarriorSword() {
    return new Sword(
      "Warrior Sword",
      "A balanced sword with good accuracy and good damage",
      1, 75, 75, 2, 8);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getLongSword() {
    return new Sword(
      "Long Sword",
      "A higher range sword with better damage but less accuracy",
      2, 50, 65, 4, 12);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getSpectralSword() {
    return new Sword(
      "Spectral Sword",
      "A legendary sword, deals ranged massive damage with extreme accuracy",
      4, 25, 90, 8, 24);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getHunterBow() {
    return new Bow(
      "Hunter Bow",
      "A standard bow with good damage",
      10, 90, 3, 16);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getLongBow() {
    return new Bow(
      "Long Bow",
      "A bow with massive range",
      32, 50, 8, 32);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Gold> getSomeGold() {
    return new ImmutableGold(75, 25);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Gold> getMoreGold() {
    return new ImmutableGold(50, 375);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Gold> getMostGold() {
    return new ImmutableGold(10, 1200);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Monster> getSwordGoblin() {
    return new BadMonster(
      new CharacterImpl(
        new StatsImpl(4, 3, 4), "Goblin", "A weak Goblin with sword"),
      this.getWarriorSword().getInstance(Optional.empty()),
      new StatsBehaviorMonsterWeak(),
      5, 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Monster> getArcherGoblin() {
    return new BadMonster(
      new CharacterImpl(
        new StatsImpl(4, 3, 4), "Goblin", "A weak Goblin with arch"),
      this.getWarriorSword().getInstance(Optional.empty()),
      new StatsBehaviorMonsterWeak(),
      3, 2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getClaws() {
    return new Sword(
      "Fists",
      "The beast claws.",
      1, 100, 95, 5, 11);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Monster> getBeast() {
    return new BadMonster(
      new CharacterImpl(
        new StatsImpl(6, 4, 7), "Beast", "A strong beast with claws"),
      this.getWarriorSword().getInstance(Optional.empty()),
      new StatsBehaviorMonsterMedium(),
      2, 2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Monster> getDragon() {
    return new BossMonster(
      new CharacterImpl(
        new StatsImpl(7, 8, 5), "Dragon", "A huge and powerful beast"),
      1, 2,
      Set.of(
        this.getWarriorSword().getInstance(Optional.empty()),
        this.getDragonBreath().getInstance(Optional.empty())));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Template<Weapon> getDragonBreath() {
    return new Bow(
      "Dragon Breath",
      "A powerful fire ball",
      5, 0, 20, 16);
  }
}
