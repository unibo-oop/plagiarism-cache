package formations;

import java.util.Map;
import javafx.util.Pair;

/**
 * Interface describing the type EnemyFormation.
 */
public interface EnemyFormation {

    /** 
     * Returns the Enemy Formation Map, containing coordinates 
     * paired with the type of enemy that should appear
     * in those coordinates.
     * @return the Enemy Formation Map.
     */
    Map<Pair<Integer, Integer>, EnemyType> getFormationMap();
}
