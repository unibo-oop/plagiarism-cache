package model.objects.unit;

import java.util.Set;

import model.Cost;
import model.abilities.Ability;
import model.objects.GameObject;

/**
 * The Unit interface represent an unit. A unit is a GameObject that could be
 * created, moved and he can attack. The unit has a name, an unit type, a strength, an hp, a
 * movement range, an attack range, a cost of creation, a cost of unlock and
 * some abilities.
 */
public interface Unit extends GameObject {

    /**
     * @return unit's name.
     */
    String getName();

    /**
     * @return the type of the unit.
     */
    UnitType getUnitType();

    /**
     * @return unit's strength.
     */
    int getStrength();

    /**
     * Set unit's life.
     * 
     * @param hp is the unit's life.
     */
    void setHp(int hp);

    /**
     * Get unit's life.
     * 
     * @return unit's life.
     */
    int getHp();

    /**
     * Get initial life's value.
     * 
     * @return initialLife.
     */
    int getInitialHp();

    /**
     * Method that verify if the unit is dead.
     * 
     * @return true if it is dead
     */
    boolean isDead();

    /**
     * Get unit's attack range.
     * 
     * @return unit's attack range.
     */
    int getAttackRange();

    /**
     * Get unit's movement range.
     * 
     * @return unit's movement range.
     */
    int getMovementRange();

    /**
     * This method tell the unit it has moved and to reorganize it's internal state
     * consequently.
     */
    void move();

    /**
     * @return true if the unit can be move.
     */
    boolean canMove();

    /**
     * This method tell the unit it has attacked and to reorganize it's internal state
     * consequently.
     */
    void attack();

    /**
     * @return true if the unit can attack.
     */
    boolean canAttack();

    /**
     * @return true if, when the unit kills the opponent, he moves in the opponent's
     *         cell.
     */
    boolean movesAfterKill();

    /**
     * This method is used when the unit is hit by an enemy.
     * @param opponentsStrength damage inflicted by the opponent.
     */
    void takeDamage(int opponentsStrength);

    /**
     * This method resets the internal state of the unit as if it has never attacked or moved.
     */
    void reset();

    /**
     * @return the cost of the unit.
     */
    Cost getCost();

    /**
     * This method return a printable cost of the unit.
     * @return unit's cost to string.
     */
    String getCostToString();

    /**
     * @return the unlock cost of the unit.
     * 
     */
    Cost getUnlockCost();

    /**
     * This method return a printable unlock cost of the unit.
     * @return unit's cost to string.
     */
    String getUnlockCostToString();

    /**
     * Get all unit abilities.
     * @return the ability set of the unit
     */
    Set<Ability> getAbilities();

    /**
     * This method could be used to add an ability to the unit.
     * @param ability is an ability to add to abilities set.
     */
    void addAbility(Ability ability);

    /**
     * This method could be used to remove an ability to the unit.
     * @param ability is an ability to remove to abilities set.
     */
    void removeAbility(Ability ability);

}
