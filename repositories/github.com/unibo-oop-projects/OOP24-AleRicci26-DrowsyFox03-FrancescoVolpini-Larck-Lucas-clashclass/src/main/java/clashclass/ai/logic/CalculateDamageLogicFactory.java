package clashclass.ai.logic;

/**
 * Represents a {@link CalculateDamageLogic} factory.
 */
public interface CalculateDamageLogicFactory {
    /**
     * Creates a damage logic for barbarian.
     *
     * @return the damage logic
     */
    DamageLogicComponent createForBarbarian();

    /**
     * Creates a damage logic for archer.
     *
     * @return the damage logic
     */
    DamageLogicComponent createForArcher();

    /**
     * Creates a damage logic for cannon.
     *
     * @return the damage logic
     */
    DamageLogicComponent createForCannon();

    /**
     * Creates a damage logic for archer tower.
     *
     * @return the damage logic
     */
    DamageLogicComponent createForArcherTower();
}
