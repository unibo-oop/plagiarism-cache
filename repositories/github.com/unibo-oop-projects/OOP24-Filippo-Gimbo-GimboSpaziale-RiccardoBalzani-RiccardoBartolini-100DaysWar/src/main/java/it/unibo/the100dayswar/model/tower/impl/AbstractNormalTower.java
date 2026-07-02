package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.fight.impl.GenericBattleCommand;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Combatant;
import it.unibo.the100dayswar.model.unit.impl.UnitImpl;

/**
 * Abstract class representing a NormalTower in the game.
 */
public abstract class AbstractNormalTower extends UnitImpl implements Tower {
    private static final long serialVersionUID = 1L;

    /**
     * The the maximum level of the tower.
     */
    protected static final int MAX_LEVEL = 3;
    private final TowerType towerType;
    private final Cell position;
    /**
     * The damage dealt by the tower.
     */
    private int damage;

    /**
     * Constructs a Tower with the specified position, level, and tower type.
     *
     * @param towerType the type of the tower
     * @param owner the owner of the tower
     * @param health the health of the tower
     * @param position the position of the tower
     * @param costToBuy the cost to buy the tower
     * @param costToUpgrade the cost to upgrade the tower
     * @param damage the damage of the tower
     */
    public AbstractNormalTower(
        final TowerType towerType, 
        final Player owner, 
        final int health,
        final Cell position,
        final int costToBuy,
        final int costToUpgrade,
        final int damage
        ) {
        super(owner, health, costToBuy, costToUpgrade, MAX_LEVEL);
        this.position = new CellImpl(position);
        this.towerType = towerType;
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAttack(final Combatant target) {
        new GenericBattleCommand<>().execute(this, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getPosition() {
        return new CellImpl(this.position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TowerType getTowerType() {
        return this.towerType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * Gets the MAX_LEVEL of the tower.
     * 
     * @return the MAX_LEVEL of the tower
     */
    public static int getMaxLevel() {
        return MAX_LEVEL;
    }
}
