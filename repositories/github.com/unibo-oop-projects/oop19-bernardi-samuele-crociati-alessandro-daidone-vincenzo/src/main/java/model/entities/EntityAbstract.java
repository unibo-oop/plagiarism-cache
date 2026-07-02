package model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;

/**
 * Abstract entity.
 *
 */
public abstract class EntityAbstract implements Entity {

    private final String name;
    private final int maxHP;
    private int currentHP;
    private final int attack;
    private final int defense;
    private final EntityType entityType;
    private EntityStatus entityStatus;
    private final MovementType movementType;
    private final AttackType attackType;
    private AttackStatus attackStatus = AttackStatus.AVAILABLE;
    private MovementStatus movementStatus = MovementStatus.AVAILABLE;
    private Pair<Integer, Integer> position;

    /**
     * Defines an entity.
     */
    EntityAbstract(final String name, final int maxHP, final int attack, final int defense, final EntityType entityType,
            final EntityStatus entityStatus, final MovementType movementType, final AttackType attackType,
            final Pair<Integer, Integer> initialPosition) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = this.maxHP;
        this.attack = attack;
        this.defense = defense;
        this.entityType = entityType;
        this.entityStatus = entityStatus;
        this.movementType = movementType;
        this.attackType = attackType;
        this.position = new Pair<>(initialPosition.getKey(), initialPosition.getValue());
    }

    protected abstract int calculateDamage(int incomingDamage);

    @Override
    public final void loseHP(final int incomingDamage) {
        this.currentHP -= this.calculateDamage(incomingDamage);
    }

    @Override
    public final void move(final Pair<Integer, Integer> newPosition) {
        if (this.movementType != MovementType.NONE) {
            this.position = new Pair<>(newPosition.getKey(), newPosition.getValue());
        }
    }

    @Override
    public final List<String> getInfo() {
        return new ArrayList<String>(
                Arrays.asList(new String[] { this.name, String.valueOf(this.maxHP), String.valueOf(this.currentHP),
                        String.valueOf(this.attack), String.valueOf(this.defense), this.entityType.toString(),
                        this.entityStatus.toString(), this.movementType.toString(), this.attackType.toString(),
                        this.attackStatus.toString(), this.movementStatus.toString(), this.position.toString() }));
    }

    @Override
    public final int getMaxHP() {
        return this.maxHP;
    }

    @Override
    public final int getCurrentHP() {
        return this.currentHP;
    }

    @Override
    public final int getAttack() {
        return this.attack;
    }

    @Override
    public final int getDefense() {
        return this.defense;
    }

    @Override
    public final EntityType getType() {
        return this.entityType;
    }

    @Override
    public final EntityStatus getStatus() {
        return this.entityStatus;
    }

    @Override
    public final void setStatus(final EntityStatus status) {
        this.entityStatus = status;
    }

    @Override
    public final MovementType getMovementType() {
        return this.movementType;
    }

    @Override
    public final AttackType getAttackType() {
        return this.attackType;
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public final AttackStatus getAttackStatus() {
        return attackStatus;
    }

    @Override
    public final void setAttackStatus(final AttackStatus attackStatus) {
        this.attackStatus = attackStatus;
    }

    @Override
    public final MovementStatus getMovementStatus() {
        return movementStatus;
    }

    @Override
    public final void setMovementStatus(final MovementStatus movementStatus) {
        this.movementStatus = movementStatus;
    }

    @Override
    public final void energize() {
        this.attackStatus = AttackStatus.AVAILABLE;
        this.movementStatus = MovementStatus.AVAILABLE;
    }

    @Override
    public final boolean isMovementExhausted() {
        return this.movementStatus.equals(MovementStatus.EXHAUSTED);
    }

    @Override
    public final boolean isAttackExhausted() {
        return this.attackStatus.equals(AttackStatus.EXHAUSTED);
    }

}
