package formations;

/**
 * This interface provides a single method, used by external
 * classes to obtain the needed enemy formation.
 */
public interface EnemyFormationGenerator {

    /**
     * This method returns a new EnemyFormation.
     * @param quantity the number of enemies in the wanted EnemyFormation.
     * @return a new EnemyFormation.
     */
    EnemyFormation getEnemyFormation(Integer quantity);

    /**
     * This method returns a new EnemyFormation with only one
     * entry, whose EnemyType value is BOSS.
     * @return a new EnemyFormation with only a BOSS.
     */
    EnemyFormation getBossWave();
}
