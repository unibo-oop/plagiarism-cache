package dungeon.character.player;

import java.util.Optional;

import dungeon.Direction;
import dungeon.Utilities;
import dungeon.attack.Attack;
import dungeon.attack.Attack.Result;
import dungeon.character.monster.Monster;
import dungeon.floor.Floor;
import dungeon.point.Point;
import dungeon.template.ImmutableTemplateFactory;

/**
 * An implementation of {@code Mode}, make the player attack.
 */

public final class AttackMode implements Mode {


  /**
   * Calcolate extra damege.
   *
   * @param monster the monster
   * @param player the player
   * @return the int
   */
  private int calcolateExtraDamege(final Monster monster, final Player player) {
    int extraDamage = ((int) (player.getStats().getAtk() 
        * Utilities.ATTACK_MULTIPLIER
        - monster.getBreed().getStats().getDef()));
    extraDamage = extraDamage <= 0 ? 1 : extraDamage;
    return extraDamage;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Attack Mode";
  }

  private Optional<Point> getTartget(
      final Player player, final Point pos, final Floor floor) {
    Optional<Point> target = Optional.of(pos);
    Direction dir = player.getPoint().getDirection(pos);
    int i = 0;
    while (i < player.getWeapon().getRange() - 1 
        && !floor.getMonsters().containsKey(target.get())) {
      target = Optional.of(target.get().move(dir.getModifier()));
      i++;
    }
    return target;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeAction(final Point pos, 
      final Player player, final Floor floor) {
    Optional<Point> target = this.getTartget(player, pos, floor);
    if (floor.getMonsters().containsKey(target.get())) {
      Monster monster = floor.getMonsters().get(target.get());
      player.getWeapon()
        .setExtraDamage(this.calcolateExtraDamege(monster, player));
      Attack attack = player.getWeapon()
          .hit(player.getName(), target.get(), player.getPoint());

      if (attack.getResult().equals(Result.HIT)) {
        monster.getBreed().setHealthRemained(attack.getDamage().get());
        if (monster.getBreed().isDead()) {
          floor.remove(target.get());
          player.setExp(player.getExp() + monster.getReleasedExp());
          player.levelUp();
        }
        if (player.getWeapon().getAmmo().isPresent() 
            && player.getWeapon().getAmmo().get() == 0) {
          player.setWeapon(new ImmutableTemplateFactory()
              .getFists().getInstance(Optional.empty()));
        }
      } 
      System.out.println(attack.getDescription());
    }
  }
}
