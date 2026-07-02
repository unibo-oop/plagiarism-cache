package talisman.model.battle;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that helps accessing enemy's informations.
 * 
 * @author Alice Girolomini
 *
 */
public final class EnemyInfos {
    private static List<EnemyModel> enemies = new ArrayList<>();

    private EnemyInfos() {

    }

    /**
     * Gets the enemy at the specified index.
     * 
     * @param index - index of the enemy
     * @return the enemy
     */
    public static EnemyModel getEnemyByIndex(final int index) {
        return EnemyInfos.enemies.get(index);
    }

    /**
     * Gets the enemy with the specified name.
     * 
     * @param name - the enemie's name
     * @return the enemy
     */
    public static EnemyModel getEnemyByName(final String name) {
        for (int i = 0; i < getEnemyCount(); i++) {
            if (getEnemyByIndex(i).getName().equals(name)) {
                return getEnemyByIndex(i);
            }
        }
        return null;
    }

    /**
     * Adds the enemy to the enemies' list.
     * 
     * @param enemy - the enemy to be add
     * @return the index of the new enemy
     */
    public static int addEnemy(final EnemyModel enemy) {
        EnemyInfos.enemies.add(enemy);
        return EnemyInfos.enemies.indexOf(enemy);
    }

    /**
     * Gets the number of enemies.
     * 
     * @return the value
     */
    public static int getEnemyCount() {
        return EnemyInfos.enemies.size();
    }

    /**
     * Checks whether the enemy uses strength or craft.
     * 
     * @param index - index of the enemy
     * @return true if the enemy uses strength
     */
    public static boolean hasStrength(final int index) {
        return (EnemyInfos.enemies.get(index).getStrength() > 0);
    }

}
