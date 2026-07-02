package dungeon.template;

import dungeon.character.monster.Monster;
import dungeon.gold.Gold;
import dungeon.item.Item;
import dungeon.weapon.Weapon;

/**
 * An object able to create various {@code Template} objects.
 */
public interface TemplateFactory {

  /**
   * Returns a new template of a wall.
   *
   * @return the template
   */
  Template<Item> getWall();

  /**
   * Returns a new template of a non-blocking wall.
   *
   * @return the template
   */
  Template<Item> getFakeWall();

  /**
   * Returns a new template of a door.
   *
   * @return the template
   */
  Template<Item> getDoor();

  /**
   * Returns a new template of player fists.
   *
   * @return the template
   */
  Template<Weapon> getFists();

  /**
   * Returns a new template of a broken sword.
   *
   * @return the template
   */
  Template<Weapon> getBrokenSword();

  /**
   * Returns a new template of a tramp sword.
   *
   * @return the template
   */
  Template<Weapon> getTrampSword();

  /**
   * Returns a new template of a warrior sword.
   *
   * @return the template
   */
  Template<Weapon> getWarriorSword();

  /**
   * Returns a new template of a long sword.
   *
   * @return the template
   */
  Template<Weapon> getLongSword();

  /**
   * Returns a new template of a spectral sword.
   *
   * @return the template
   */
  Template<Weapon> getSpectralSword();

  /**
   * Returns a new template of a hunter bow.
   *
   * @return the template
   */
  Template<Weapon> getHunterBow();

  /**
   * Returns a new template of a long bow.
   *
   * @return the template
   */
  Template<Weapon> getLongBow();

  /**
   * Returns a new template of "some gold".
   *
   * @return the template
   */
  Template<Gold> getSomeGold();

  /**
   * Returns a new template of "more gold".
   *
   * @return the template
   */
  Template<Gold> getMoreGold();

  /**
   * Returns a new template of "most gold".
   *
   * @return the template
   */
  Template<Gold> getMostGold();

  /**
   * Returns a new template of sword goblin.
   *
   * @return the template
   */
  Template<Monster> getSwordGoblin();

  /**
   * Returns a new template of archer goblin.
   *
   * @return the template
   */
  Template<Monster> getArcherGoblin();

  /**
   * Returns a new template of claws.
   *
   * @return the template
   */
  Template<Weapon> getClaws();

  /**
   * Returns a new template of beast.
   *
   * @return the template
   */
  Template<Monster> getBeast();

  /**
   * Returns a new template of dragon.
   *
   * @return the template
   */
  Template<Monster> getDragon();

  /**
   * Returns a new template of dragon breath.
   *
   * @return the template
   */
  Template<Weapon> getDragonBreath();
}
