package dungeon.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import dungeon.character.CharacterImpl;
import dungeon.character.monster.BadMonster;
import dungeon.character.stats.StatsBehaviorMonsterWeak;
import dungeon.character.stats.StatsImpl;
import dungeon.template.ImmutableTemplateFactory;
//You do not silence checkstyle like this. MagicNumber OFF

public class TestMonster {

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new BadMonster(
          new CharacterImpl(new StatsImpl(2, 2, 2),
              "Goblin", "A weak Goblin with sword"),
            null,
            new StatsBehaviorMonsterWeak(),
             5, 1));

    assertThrows(IllegalArgumentException.class,
      () -> new BadMonster(
            new CharacterImpl(new StatsImpl(2, 2, 2),
                "Goblin", "A weak Goblin with sword"),
              new ImmutableTemplateFactory().getWarriorSword()
              .getInstance(Optional.empty()),
              new StatsBehaviorMonsterWeak(),
               -5, 1));

    assertThrows(NullPointerException.class,
      () -> new BadMonster(
            new CharacterImpl(new StatsImpl(2, 2, 2),
                "Goblin", "A weak Goblin with sword"),
              new ImmutableTemplateFactory().getWarriorSword()
              .getInstance(Optional.empty()),
              null,
               5, 1));
  }

}
