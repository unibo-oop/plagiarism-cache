package game.enemy;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to check the enemies's coordinate, implements the interface {@link InterfaceModelEnemy}.
 *
 */
public class ModelEnemy implements InterfaceModelEnemy {

    private final List<Integer> listEnemyX;
    private final List<Integer> listEnemyY;
    private final int hit;
    /**
     * Classic constructor of {@link ModelEnemy}.
     * @param hit hitbox.
     */
    public ModelEnemy(final int hit) {
        this.hit = hit;
        listEnemyX = new LinkedList<>();
        listEnemyY = new LinkedList<>();
    }
    /**
     * Method of {@link InterfaceModelEnemy}.
     * @param n number to check.
     */
    @Override
    public boolean busyX(final int n) {
        for (final int x : listEnemyX) {
            if (x == n || (x >= n - hit && x <= n + hit)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method of {@link InterfaceModelEnemy}.
     * @param n number to check.
     */
    @Override
    public boolean busyY(final int n) {
        for (final int x : listEnemyY) {
            if (x == n || (x >= n - hit && x <= n + hit)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method of {@link InterfaceModelEnemy}.
     */
    @Override
    public void deleteList() {
        listEnemyX.clear();
        listEnemyY.clear();
    }
    /**
    * Method of {@link InterfaceModelEnemy}.
    * @param list list to add the number n to
    * @param n number to check.
    */
    @Override
    public void addNumber(final boolean list, final int n) {
        if (list) {
            listEnemyX.add(n);
        } else {
            listEnemyY.add(n);
        }
    }
}
