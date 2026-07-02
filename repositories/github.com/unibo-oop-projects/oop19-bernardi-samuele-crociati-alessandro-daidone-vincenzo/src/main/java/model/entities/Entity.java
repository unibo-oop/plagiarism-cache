/**
 * 
 */
package model.entities;

import java.util.List;

import javafx.util.Pair;

/**
 * This is an Entity.
 */
public interface Entity {

    /**
     * Moves entity to the new position.
     * 
     * @param newPosition
     *            the new position
     * @throws IllegalStateException
     *             if the Entity is set to not be moved
     */
    void move(Pair<Integer, Integer> newPosition);

    /**
     * Returns the info of this entity.
     * 
     * @return a list of information about the entity
     */

    List<String> getInfo();

    /**
     * @return the maxHP
     */
    int getMaxHP();

    /**
     * @return the currentHP
     */
    int getCurrentHP();

    /**
     * @return the position
     */
    Pair<Integer, Integer> getPosition();

    /**
     * @return the type
     */
    EntityType getType();

    /**
     * @return the status
     */
    EntityStatus getStatus();

    /**
     * inflicts damage to the entity.
     * 
     * @param incomingDamage
     *            the damage inflicted
     */
    void loseHP(int incomingDamage);

    /**
     * @param status
     *            the status to set
     */
    void setStatus(EntityStatus status);

    /**
     * @return the movement
     */
    MovementType getMovementType();

    /**
     * @return the attack type
     */
    AttackType getAttackType();

    /**
     * @return the attack
     */
    int getAttack();

    /**
     * @return the defense
     */
    int getDefense();

    /**
     * @return the attackStatus
     */
    AttackStatus getAttackStatus();

    /**
     * @param attackStatus
     *            the attackStatus to set
     */
    void setAttackStatus(AttackStatus attackStatus);

    /**
     * @return the movementStatus
     */
    MovementStatus getMovementStatus();

    /**
     * @param movementStatus
     *            the movementStatus to set
     */
    void setMovementStatus(MovementStatus movementStatus);

    /**
     * 
     * @return whether movement is exhausted or not.
     */
    boolean isMovementExhausted();

    /**
     * 
     * @return whether attack is exhausted or not
     */
    boolean isAttackExhausted();

    /**
     * Energizes an entity.
     */
    void energize();
}
