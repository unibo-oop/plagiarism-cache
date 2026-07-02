package model.objects.unit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import model.Cost;
import model.abilities.Ability;
import model.abilities.BasicAbilities;
import model.objects.AbstractGameObject;
import model.player.Player;

/**
 * The UnitImpl class extends AbstractGameObject and implements Unit. It
 * represent a GameObject that can be created, moved and that can fight with
 * other unit. The units have particular attributes. Each unit has a name, an
 * unit type, a strength, a hp, a movement range, an attack range, a cost, an
 * unlock cost and some abilities. When a player create an unit, in this turn,
 * the unit can't be move. Every unit can be move one time par turn, but there
 * are some unit that can also do a second movement after an attack. If an unit
 * attack before move, then i can't do its first movement. If an unit that can
 * move after and that has more then one possible attack par turn, do an attack
 * and then move, then it can't do the other attack. Could be some units that,
 * after killing an enemy, move in enemy position.
 *
 */
public class UnitImpl extends AbstractGameObject implements Unit {

    private final String name;
    private final int strength;
    private final int initialHp;
    private int hp;
    private final int movementRange;
    private final int attackRange;
    private final int possibleAttack;
    private int attackCount;
    private boolean didFirstMovement;
    private boolean movedAfterAttack;
    private final boolean canMoveAfterAttack;
    private final boolean moveOnKill;
    private final UnitType unitType;
    private final Cost unitCost;
    private final Cost unitUnlockCost;
    private final Set<Ability> abilities;

    /**
     * UnitImpl constructor with owner.
     * 
     * @param owner              unit's owner. It the Optional is empty it means
     *                           that the unit doesn't have an owner.
     * @param name               unit's name.
     * @param strength           unit's strength.
     * @param initialHp          unit's initial life.
     * @param movementRange      unit's movement range.
     * @param attackRange        unit's attack range.
     * @param possibleAttack     unit's possible attack in a turn.
     * @param canMoveAfterAttack if the unit can move after he has attacked someone.
     * @param moveOnKill         if the unit kills the opponent, he moves in the
     *                           opponent's cell.
     * @param unitCost           unit's cost.
     * @param unitUnlockCost     unit's unlock cost.
     * @param unitType           unit's type.
     */
    public UnitImpl(final Optional<Player> owner, final String name, final int strength, final int initialHp,
            final int movementRange, final int attackRange, final int possibleAttack, final boolean canMoveAfterAttack,
            final boolean moveOnKill, final Cost unitCost, final Cost unitUnlockCost, final UnitType unitType) {
        set(owner);
        this.name = name;
        this.strength = strength;
        this.initialHp = initialHp;
        this.hp = initialHp;
        this.movementRange = movementRange;
        this.attackRange = attackRange;
        this.possibleAttack = possibleAttack;
        this.canMoveAfterAttack = canMoveAfterAttack;
        this.moveOnKill = moveOnKill;
        this.unitCost = unitCost;
        this.unitUnlockCost = unitUnlockCost;
        this.movedAfterAttack = true;
        this.didFirstMovement = true;
        this.unitType = unitType;
        this.attackCount = possibleAttack;
        this.abilities = new HashSet<>();
        this.abilities.add(BasicAbilities.WALKONLAND);
    }

    /** {@inheritDoc} **/
    @Override
    public String getName() {
        return this.name;
    }

    /** {@inheritDoc} **/
    @Override
    public UnitType getUnitType() {
        return unitType;
    }

    /** {@inheritDoc} **/
    @Override
    public int getStrength() {
        return this.strength;
    }

    /** {@inheritDoc} **/
    @Override
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /** {@inheritDoc} **/
    @Override
    public int getHp() {
        return this.hp;
    }

    /** {@inheritDoc} **/
    @Override
    public int getInitialHp() {
        return this.initialHp;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }

    /** {@inheritDoc} **/
    @Override
    public int getAttackRange() {
        return this.attackRange;
    }

    /** {@inheritDoc} **/
    @Override
    public int getMovementRange() {
        return this.movementRange;
    }

    /** {@inheritDoc} **/
    @Override
    public void move() {
        if (canMove()) {
            if (!this.didFirstMovement) {
                this.didFirstMovement = true;
            } else if (this.canMoveAfterAttack) {
                this.movedAfterAttack = true;
                this.attackCount = this.possibleAttack;
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canMove() {
        return (!this.didFirstMovement && this.attackCount == 0)
                || (this.canMoveAfterAttack && !this.movedAfterAttack && this.attackCount != 0);
    }

    /** {@inheritDoc} **/
    @Override
    public void attack() {
        if (canAttack()) {
            if (!this.didFirstMovement) {
                this.didFirstMovement = true;
            }
            this.attackCount++;
            if (this.canMoveAfterAttack) {
                this.movedAfterAttack = false;
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canAttack() {
        return possibleAttack > attackCount;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean movesAfterKill() {
        return this.moveOnKill;
    }

    /** {@inheritDoc} **/
    @Override
    public void takeDamage(final int opponentsStrength) {
        if (this.hp < opponentsStrength) {
            hp = 0;
        }
        hp -= opponentsStrength;
    }

    /** {@inheritDoc} **/
    @Override
    public void reset() {
        this.didFirstMovement = false;
        if (this.canMoveAfterAttack) {
            this.movedAfterAttack = true;
        }
        this.attackCount = 0;
    }

    /** {@inheritDoc} **/
    @Override
    public Set<Ability> getAbilities() {
        return Collections.unmodifiableSet(this.abilities);
    }

    /** {@inheritDoc} **/
    @Override
    public void addAbility(final Ability ability) {
        if (!abilities.contains(ability)) {
            this.abilities.add(ability);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public void removeAbility(final Ability ability) {
        if (abilities.contains(ability)) {
            this.abilities.remove(ability);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return this.name + isHero() + "\nStrength: " + this.strength + "\nHp: " + this.hp + "/" + this.initialHp
                + "\nMovement Range: " + this.movementRange + "\nAttack Range: " + (this.attackRange)
                + "\nRemainig Attacks: " + (this.possibleAttack - this.attackCount) + "\nCan move: " + canMove()
                + "\nCan move after attacking: " + this.canMoveAfterAttack + "\nMove on kill: " + this.moveOnKill;
    }

    private String isHero() {
        return (unitType.getGenericUnitType().equals(GenericUnitType.HERO))
                ? ((unitType.equals(UnitType.HERO_CLOSE_FIGHTER)) ? "\n(Close Hero)" : "\n(Distance Hero)")
                : "";
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getCost() {
        return this.unitCost;
    }

    /** {@inheritDoc} **/
    @Override
    public String getCostToString() {
        return this.unitCost.toString();

    }

    /** {@inheritDoc} **/
    @Override
    public Cost getUnlockCost() {
        return this.unitUnlockCost;
    }

    /** {@inheritDoc} **/
    @Override
    public String getUnlockCostToString() {
        return this.unitUnlockCost.toString();
    }

}
