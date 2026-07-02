package vg.model.entity.boss;

import vg.utils.V2D;

/**
 * A factory for creating Boss model.
 */
public final class StaticFactoryBoss {
    private StaticFactoryBoss() {
    }

    /**
     * Create a new boss, for round 1.
     * @return boss model
     */
    public static BossModel createRound1() {
        return new BossImpl(new V2D(2, 2));
    }
    /**
     * Create a new boss, for round 2.
     * @return boss model
     */
    public static BossModel createRound2() {
        return new BossImpl(new V2D(1, 1));
    }
}
