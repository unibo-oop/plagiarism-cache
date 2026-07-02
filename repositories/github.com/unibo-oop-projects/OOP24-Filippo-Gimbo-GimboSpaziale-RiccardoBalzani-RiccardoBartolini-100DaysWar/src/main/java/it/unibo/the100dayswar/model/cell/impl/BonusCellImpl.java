package it.unibo.the100dayswar.model.cell.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * Implementation of the BonusCell interface.
 */
public class BonusCellImpl extends CellImpl implements BonusCell {
    private static final long serialVersionUID = 1L;

    private final Set<Observer<ResourceGenerator>> observers;
    private static final int BONUS = 100;
    private boolean bonusActive;
    /**
     * Constructor of a cell that give a bonus to the player by a Buildable Cell given.
     * 
     * @param decoratedCell is the cell that will be decorated.
     */
    public BonusCellImpl(final Cell decoratedCell) {
        super(decoratedCell);
        this.observers = new HashSet<>();
        this.bonusActive = true;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public int getAmount() {
        return BONUS;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void attach(final Observer<ResourceGenerator> observer) {
        observers.add(observer);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void detach(final Observer<ResourceGenerator> observer) {
        observers.remove(observer);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void notify(final Observer<ResourceGenerator> observer) {
        if (isBonusActive() && !this.getUnit().isEmpty()) {
            observers.stream().filter(observer::equals).forEach(p -> p.update(this));
            setBonusActive(false);
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isBonusActive() {
        return this.bonusActive;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setBonusActive(final boolean bonusActive) {
        this.bonusActive = bonusActive;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public  boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BonusCellImpl)) {
            return false;
        }
        final BonusCellImpl other = (BonusCellImpl) obj;
        return super.equals(obj) && this.bonusActive == other.bonusActive;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() + Boolean.hashCode(this.bonusActive);
    }
}
