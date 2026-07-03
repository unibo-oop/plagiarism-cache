package model.car.tyre;

import utility.TyreType;

/**
 * Implements the interface of Tyre.
 */
public class TyreImpl implements Tyre {
    private static final int PERC = 100; 
    private static final TyreType INITIAL_TYPE = TyreType.SS;
    private int degrade;
    private TTByDegrade tyre;
    private TyreType type;
/**
 * Constructor if the tyre to change into is not specified.
 */
    public TyreImpl() {
        this(INITIAL_TYPE);
        }
/**
 * Constructor if the tyre to change into is specified.
 * @param type type of tyre to change into
 */
    public TyreImpl(final TyreType type) {
        this.degrade = type.getMaxDegrade();
        this.tyre = type.getFirstTyre();
        this.type = type;
        }
    @Override
    public void tyreDeg() {
        this.degrade--;
        if (this.degrade < 0) {
            this.degrade = 0;
        }
        if (this.getDeg() >= this.tyre.getMaxDeg() && this.degrade != 0) {
            this.tyre = this.tyre.getNextTyre();
        }
    }
    @Override
    public double getDeg() {
        final int max = this.type.getMaxDegrade();
        return (double) (max - this.degrade) / max * PERC;
    }

    @Override
    public void change(final TyreType newTyre) {
        this.tyre = newTyre.getFirstTyre();
        this.type = newTyre;
        this.degrade = this.type.getMaxDegrade();
    }
    @Override
    public TyreType getTyreType() {
        return this.type;
    }
    @Override
    public TTByDegrade getTyre() {
        return this.tyre;
    }
}