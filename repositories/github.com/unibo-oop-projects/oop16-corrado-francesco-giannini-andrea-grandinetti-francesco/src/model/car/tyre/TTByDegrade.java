package model.car.tyre;


/**
 * Different type of tyres that the player can choose.
 *
 */
public enum TTByDegrade {
    /**
     * UltraSoft high worn out.
     */
    USA(1, 10, 100, null),
    /**
     * UltraSoft mid high worn out. 
     */
    USMA(3, 10, 75, USA),

    /**
     * UltraSoft mid low worn out. 
     */
    USMB(2, 10, 50, USMA),
    /**
     * UltraSoft low worn out. 
     */
    USB(4, 10, 25, USMB),
    /**
     * SuperSoft high worn out.
     */
    SSA(1, 9, 100, null),
    /**
     * SuperSoft mid high worn out.
     */
    SSMA(2, 9, 75, SSA),
    /**
     * SuperSoft mid low worn out.
     */
    SSMB(3, 9, 50, SSMA),
    /**
     * SuperSoft low worn out.
     */
    SSB(4, 9, 25, SSMB),
    /**
     * Soft high worn out.
     */
    SA(1, 8, 100, null),
    /**
     * Soft medium worn out.
     */
    SM(2, 8, 66, SA),
    /**
     * Soft low worn out.
     */
    SB(3, 8, 33, SM),
    /**
     * Medium high worn out.
     */
    MA(1, 7, 100, null),
    /**
     * Medium low worn out.
     */
    MB(3, 7, 50, MA),
    /**
     * Hard low worn out.
     */
    HA(1, 6, 100, null),
    /**
     * Hard high worn out.
     */
    HB(2, 6, 50, HA);
 
    private final int min;
    private final int max;
    private final int maxDeg;
    private final TTByDegrade nextTyre;

    TTByDegrade(final int min, final int max, final int maxDeg, final TTByDegrade nextTyre) {
        this.min = min;
        this.max = max;
        this.maxDeg = maxDeg;
        this.nextTyre = nextTyre;
    }

    /**
     * @return max value
     */
    public int getMax() {
        return this.max;
    }

    /**
     * @return min value
     */
    public int getMin() {
        return this.min;
    }
    /**
     * 
     * @return max Deg in which you change the tyre
     */
    public int getMaxDeg() {
        return maxDeg;
    }
    /**
     * 
     * @return the next tyre to be changed
     */
    public TTByDegrade getNextTyre() {
        return nextTyre;
    }
}

