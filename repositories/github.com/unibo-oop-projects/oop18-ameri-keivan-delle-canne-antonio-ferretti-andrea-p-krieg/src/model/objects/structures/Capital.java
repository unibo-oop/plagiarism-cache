package model.objects.structures;

import model.Cost;

/**
 * The Capital interface is an interface that extends the City interface. The
 * Capital interface represents an object similar to a City, but with come
 * differences, in fact, it provides a leveled structure and an attack reducer
 * that will be applied to attacks involving a unit that are defending it.
 */
public interface Capital extends City {

    /**
     * This method could be used to get the attack reduction that will be applied to
     * attacks that involve an unit that is defending his capital.
     * 
     * @return the enemy attack reduction of the damage;
     */
    double getEnemyAttackReduction();

    /**
     * This method could be used to get the unlock cost of this capital level.
     * @return the capital upgrade cost.
     */
    Cost getUnlockCost();

    /**
     * This method could be used to get the unlock cost of this capital level in string version.
     * @return the capital upgrade cost in string version.
     */
    String getUnlockCostToString();

}
