package it.tbt.model.entities.characters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import it.tbt.model.entities.characters.skills.Skill;

/**
 * Character's Factory.
 */
public final class CharacterFactory {
    private static final Random RND = new Random();

    /**
     * No constructor, this is an utility class.
     */
    private CharacterFactory() {
        throw new UnsupportedOperationException("Utility class and cannot be instantiated");
    }

    /**
     * Create an ally with skills.
     *
     * @param name
     * @param health
     * @param attack
     * @param speed
     * @param skills
     * @return new ally
     */
    public static Ally createAlly(
            final String name,
            final int health,
            final int attack,
            final int speed,
            final Collection<Skill> skills) {
        return new Ally(name, health, attack, speed, skills);
    }

    /**
     * Create an ally without skills.
     *
     * @param name
     * @param health
     * @param attack
     * @param speed
     * @return new ally
     */
    public static Ally createAlly(
            final String name,
            final int health,
            final int attack,
            final int speed) {
        return new Ally(name, health, attack, speed, new ArrayList<Skill>());
    }

    /**
     * Create an enemy.
     *
     * @param name
     * @param health
     * @param attack
     * @param speed
     * @return new enemy
     */
    public static Enemy createEnemy(
            final String name,
            final int health,
            final int attack,
            final int speed) {
        return new Enemy(name, health, attack, speed);
    }

    /**
     * Create an enemy with random stats.
     * The sum of all the stats must be statSum (circa), this will help defining
     * how hard is defeating the enemy.
     * Every stats will be at least 10% of statsSum
     * 
     * @param name
     * @param statsSum
     * @return new random nemy
     */
    public static Enemy createRandomEnemy(final String name, final int statsSum) {
        final long tmpHealth = Math.abs((long) RND.nextInt());
        final long tmpAttack = Math.abs((long) RND.nextInt());
        final long tmpSpeed = Math.abs((long) RND.nextInt());
        final long sum = tmpHealth + tmpAttack + tmpSpeed;
        final int statsSumPart = (int) (statsSum * 0.7); // ~70%
        final int res = statsSum / 10; // ~10%
        return new Enemy(
                name,
                (int) (tmpHealth * statsSumPart / sum) + res + 1,
                (int) (tmpAttack * statsSumPart / sum) + res + 1,
                (int) (tmpSpeed * statsSumPart / sum) + res + 1);
    }
}
