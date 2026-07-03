package org.lkyhro.enemy;

/**
 * Created by  Migani Luca on 08/02/2016.
 */
public abstract class AbstractEnemy implements Enemy {
    private final String name;
    private final int attack;
    private final int defense;
    private final int healthPoints;
    private final int givenExperience;

    /**
     * Contructor method used by BasicMonster and Boss
     * @param name name of the monster
     * @param attack int value of attack
     * @param defense int value of defense
     * @param healthPoints int amount of health points
     * @param givenExperience int amount of experience given once defeated
     */
    public AbstractEnemy(String name, int attack, int defense, int healthPoints, int givenExperience) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.healthPoints = healthPoints;
        this.givenExperience = givenExperience;
    }

    /**
     *
     * @return String name of the monster
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return int value of attack of the monster
     */
    public int getAttack() {
        return attack;
    }

    /**
     *
     * @return int value of defense of the monster
     */
    public int getDefense() {
        return defense;
    }

    /**
     *
     * @return int amount of hp of the monster
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     *
     * @return int amount of experience given by the monster
     */
    public int getGivenExperience() {
        return givenExperience;
    }

    /**
     *
     * @return String representation of an AbstractEnemy
     */
    @Override
    public String toString() {
        return "org.lkyhro.enemy.AbstractEnemy{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                ", healthPoints=" + healthPoints +
                ", givenExperience=" + givenExperience +
                '}';
    }
}
