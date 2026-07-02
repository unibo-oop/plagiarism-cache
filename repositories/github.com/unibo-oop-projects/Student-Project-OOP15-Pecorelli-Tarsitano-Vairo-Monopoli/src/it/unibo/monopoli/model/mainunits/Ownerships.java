package it.unibo.monopoli.model.mainunits;

import java.util.Optional;

/**
 * This is the enum of all the {@link Ownerships}s position.
 *
 */
public enum Ownerships {
    /**
     * Ownership 1.
     */
    OWNERSHIP1(60, 2, 50),
    /**
     * Ownership 2.
     */
    OWNERSHIP2(60, 4, 50),
    /**
     * Ownership 3.
     */
    OWNERSHIP3(200),
    /**
     * Ownership 4.
     */
    OWNERSHIP4(100, 6, 50),
    /**
     * Ownership 5.
     */
    OWNERSHIP5(100, 6, 50),
    /**
     * Ownership 6.
     */
    OWNERSHIP6(120, 8, 50),
    /**
     * Ownership 7.
     */
    OWNERSHIP7(140, 10, 100),
    /**
     * Ownership 8.
     */
    OWNERSHIP8(150),
    /**
     * Ownership 9.
     */
    OWNERSHIP9(140, 10, 100),
    /**
     * Ownership 10.
     */
    OWNERSHIP10(160, 12, 100),
    /**
     * Ownership 11.
     */
    OWNERSHIP11(200),
    /**
     * Ownership 12.
     */
    OWNERSHIP12(180, 14, 100),
    /**
     * Ownership 13.
     */
    OWNERSHIP13(180, 14, 100),
    /**
     * Ownership 14.
     */
    OWNERSHIP14(200, 16, 100),
    /**
     * Ownership 15.
     */
    OWNERSHIP15(220, 18, 150),
    /**
     * Ownership 16.
     */
    OWNERSHIP16(220, 18, 150),
    /**
     * Ownership 17.
     */
    OWNERSHIP17(240, 20, 150),
    /**
     * Ownership 18.
     */
    OWNERSHIP18(200),
    /**
     * Ownership 19.
     */
    OWNERSHIP19(260, 22, 150),
    /**
     * Ownership 20.
     */
    OWNERSHIP20(260, 22, 150),
    /**
     * Ownership 21.
     */
    OWNERSHIP21(150),
    /**
     * Ownership 22.
     */
    OWNERSHIP22(280, 24, 150),
    /**
     * Ownership 23.
     */
    OWNERSHIP23(300, 26, 200),
    /**
     * Ownership 24.
     */
    OWNERSHIP24(300, 26, 200),
    /**
     * Ownership 25.
     */
    OWNERSHIP25(320, 28, 200),
    /**
     * Ownership 26.
     */
    OWNERSHIP26(200),
    /**
     * Ownership 27.
     */
    OWNERSHIP27(350, 35, 200),
    /**
     * Ownership 28.
     */
    OWNERSHIP28(400, 50, 200);

    private final int cost;
    private final Optional<Integer> income;
    private final Optional<Integer> buildingCost;

    Ownerships(final int cost) {
        this.cost = cost;
        this.income = Optional.empty();
        this.buildingCost = Optional.empty();
    }

    Ownerships(final int cost, final int income, final int buildingCost) {
        this.cost = cost;
        this.income = Optional.of(income);
        this.buildingCost = Optional.of(buildingCost);
    }

    /**
     * Returns {@link Ownership}'s cost.
     * 
     * @return {@link Ownership}'s cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Return an {@link Optional} of the income value.
     * 
     * @return an {@link Optional} of the income value
     */
    public Optional<Integer> getIncome() {
        return this.income;
    }

    /**
     * Return an {@link Optional} of the building cost.
     * 
     * @return an {@link Optional} of the building cost
     */
    public Optional<Integer> getBuildingCost() {
        return this.buildingCost;
    }

}
