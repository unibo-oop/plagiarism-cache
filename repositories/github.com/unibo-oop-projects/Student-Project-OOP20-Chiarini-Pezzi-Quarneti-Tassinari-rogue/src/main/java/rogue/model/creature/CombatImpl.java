package rogue.model.creature;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rogue.model.items.inventory.InventoryIsFullException;
import rogue.model.items.weapons.Weapon.Use;

public class CombatImpl implements Combat {

    private static final Logger LOG = LoggerFactory.getLogger(CombatImpl.class);

    private static final int DICE = 20;
    private static final int POISON_DAMAGE_MAX = 2;
    private static final int FLYING_MONSTER_MALUS = 2;

    private int getModStrength(final Player player) {
        return (player.getLife().getStrength() - 10) / 2;
    }

    private int playerAttackRoll(final Player player) {
        return ThreadLocalRandom.current().nextInt(0, DICE) + player.getEquipment().getWeapon().getAccuracy() + this.getModStrength(player);
    }

    private int monsterAttackRoll() {
        return ThreadLocalRandom.current().nextInt(0, DICE);
    }

    private boolean hit(final int attackRoll, final int enemyAC) {
        return DICE - attackRoll <= enemyAC;
    }

    private int poisonDamage() {
        return ThreadLocalRandom.current().nextInt(0, POISON_DAMAGE_MAX);
    }

    private void drop(final Player player, final Monster monster) {
        player.getLife().addCoins(monster.getMoney());
        player.getLife().increaseExperience(monster.getLife().getExperience());
        if (monster.getItemChange() <= monster.dropItem() && monster.getItemChange() != 0) {
            try {
                player.getInventory().addItem(monster.getItem());
            } catch (InventoryIsFullException e) {
                LOG.info("The Inventory was full you can't pick up the item");
            }
        }
    }


    private Result playerAttack(final Player player, final Monster monster) {
        monster.getSpecial().becomeHostile();
        int malus = 0;
        if (monster.getSpecial().isFlying()) {
            malus = FLYING_MONSTER_MALUS;
        }
        if (this.hit(playerAttackRoll(player) - malus, monster.getAC())) {
                monster.getLife().hurt(player.getEquipment().getWeapon().getDamage(Use.HANDLED));
                if (monster.getLife().isDead()) {
                    this.drop(player, monster);
                    return Result.DEAD;
                } else {
                    return Result.HIT;
                }
       }
        return Result.MISS;
    }

    private Result monsterAttack(final Monster monster, final Player player) {
        if (this.hit(monsterAttackRoll(), player.getEquipment().getArmor().getAC())) {
            player.getLife().hurt(monster.attackDamage());
            if (monster.getSpecial().isDrainLife()) {
                monster.getLife().setHealthPoints(monster.getLife().getHealthPoints() + monster.attackDamage());
                return Result.DRAINLIFE;
            }
            if (monster.getSpecial().isPoisonous()) {
                player.getLife().hurt(this.poisonDamage());
                return Result.POISON;
            }
            if (monster.getLife().isDead()) {
                this.drop(player, monster);
                return Result.DEAD;
            } else {
                return Result.HIT;
            }
        }
        return Result.MISS;
    }

    /**
     * 
     * @param attacker
     *          who made the attack
     * @param defender
     *         who defends himself from the attack
     */
    @Override
    public Result attack(final Creature<?> attacker, final Creature<?> defender) {
        if (attacker instanceof Player && defender instanceof Monster) {
            return this.playerAttack((Player) attacker, (Monster) defender);
        } 
        if (attacker instanceof Monster && defender instanceof Player) {
            return this.monsterAttack((Monster) attacker, (Player) defender);
        }
        return null;
    }
}
