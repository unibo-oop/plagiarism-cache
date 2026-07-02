package controller.file.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import enums.StageEnemies;
import model.enemy.Enemy;

/**
 * Class utility for load the enemies of the level. The class uses the Singleton
 * Pattern.
 */
public final class EnemyUtility {

    // Instance of the class.
    private static final EnemyUtility SINGLETON = new EnemyUtility();

    /**
     * Method that implements the Singleton Pattern. It returns only one instance of
     * the class, created only one time.
     * 
     * @return the instance of the class.
     */
    public static EnemyUtility getInstance() {
        return SINGLETON;
    }

    /*
     * Empty constructor of the class that load the enemies lists.
     */
    private EnemyUtility() {
    }

    /**
     * Method that return the requested enemy list. Given a Enemy enumeration, it
     * open the specified file and return it back.
     * 
     * @param stageEnemiesEnum the enumeration of the requested enemy list.
     * @return the requested list of enemies.
     */
    public Queue<Enemy> getEnemyList(final StageEnemies stageEnemiesEnum) {
        Queue<Enemy> enemyList = new LinkedList<Enemy>();
        final String path = stageEnemiesEnum.getPath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
            String line = null;
            line = br.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'A') {
                        enemyList.add(Enemy.ARMORED);
                    } else if (line.charAt(i) == 'F') {
                        enemyList.add(Enemy.FAST);
                    } else if (line.charAt(i) == 'N') {
                        enemyList.add(Enemy.NORMAL);
                    } else if (line.charAt(i) == 'P') {
                        enemyList.add(Enemy.POWER);
                    }
                }
                line = br.readLine();
            }
        } catch (final IOException ex) {
            enemyList = null;
            System.out.println("STAGE MAP " + stageEnemiesEnum + " NOT FOUND");
        }
        return enemyList;
    }

}
