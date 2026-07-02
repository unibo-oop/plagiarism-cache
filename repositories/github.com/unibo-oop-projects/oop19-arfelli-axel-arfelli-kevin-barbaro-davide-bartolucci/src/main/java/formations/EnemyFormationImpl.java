package formations;

import java.util.Collections;
import java.util.Map;
import javafx.util.Pair;

/**
 * Basic implementation of the EnemyFormation interface.
 */
public class EnemyFormationImpl implements EnemyFormation {

    /**
     * A map containing Pairs that indicate the coordinates of the enemy
     * and EnemyType, the type of enemy with those coordinates.
     */
    private final Map<Pair<Integer, Integer>, EnemyType> formationMap;

    /**
     * This constructor method accepts a Map as parameter, where the key Pair indicates 
     * the coordinates of the enemy and the value EnemyType the type of enemy with those coordinates.
     * @param formationMap the Map containing coordinates and types of enemies.
     */
    public EnemyFormationImpl(final Map<Pair<Integer, Integer>, EnemyType> formationMap) {
        this.formationMap = formationMap;
    }

    @Override
    public final Map<Pair<Integer, Integer>, EnemyType> getFormationMap() {
        return Collections.unmodifiableMap(this.formationMap);
    }

    @Override
    public final String toString() {
        int index = 0;
        final StringBuilder str = new StringBuilder();
        for (final Pair<Integer, Integer> p : formationMap.keySet()) {
            str.append("Enemy n. " + index + " Type: " + formationMap.get(p) + " Coordinates: X = " + p.getKey()
                                    + "; Y = " + p.getValue() + "\n");
            index++;
        }
        return str.toString();
    }

}
