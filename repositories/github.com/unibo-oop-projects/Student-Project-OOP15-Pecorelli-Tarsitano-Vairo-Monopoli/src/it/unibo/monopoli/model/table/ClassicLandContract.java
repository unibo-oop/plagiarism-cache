package it.unibo.monopoli.model.table;

/**
 * This is a implementation of {@link Land}s' {@link Contract}s.
 *
 */
public class ClassicLandContract extends ClassicContract implements LandContract {

    private final int buildingsCost;
    private final int landIncome;

    /**
     * Constructs an implementation of {@link ClassicLandContract}, It needs an
     * {@link Ownership} (the {@link Land}), its cost, its income and the cost
     * of its {@link Building}s.
     * 
     * @param ownership
     *            - the {@link Land}
     * @param cost
     *            - {@link Land}'s cost
     * @param landIncome
     *            - {@link Land}'s income
     * @param buildingsCost
     *            - cost of {@link Land}'s {@link Building}s
     */
    protected ClassicLandContract(final Ownership ownership, final int cost, final int landIncome,
            final int buildingsCost) {
        super(ownership, cost);
        this.buildingsCost = buildingsCost;
        this.landIncome = landIncome;

    }

    @Override
    public int getCostForEachBuilding() {
        return this.buildingsCost;
    }

    @Override
    public int getLandIncome() {
        return this.landIncome;
    }

    /**
     * This is the Builder of this class.
     *
     */
    public static class Builder {
        private Ownership landBuil;
        private int landBuildCost;
        private int landBuildIncome;
        private int landBuildingsCost;

        /**
         * This Builder get the {@link Land}.
         * 
         * @param l
         *            - the {@link Land}
         * @return it self
         * @throws IllegalArgumentException
         *             - if the {@link Ownership} in input is not a {@link Land}
         */
        public Builder land(final Ownership l) {
            this.landBuil = l;
            if (!(l instanceof Land)) {
                throw new IllegalArgumentException("Only for Lands");
            }
            return this;
        }

        /**
         * This Builder get the {@link Land}'s cost.
         * 
         * @param c
         *            - {@link Land}'s cost
         * @return it self
         */
        public Builder landCost(final int c) {
            this.landBuildCost = c;
            return this;
        }

        /**
         * This Builder get the {@link Land}'s income.
         * 
         * @param i
         *            - {@link Land}'s income
         * @return it self
         */
        public Builder landIncome(final int i) {
            this.landBuildIncome = i;
            return this;
        }

        /**
         * This Builder get the cost of {@link Land}'s {@link Building}s.
         * 
         * @param bc
         *            - the cost of {@link Land}'s {@link Building}s
         * @return it self
         */
        public Builder buildingCost(final int bc) {
            this.landBuildingsCost = bc;
            return this;
        }

        /**
         * Returns the {@link ClassicLandContract} built.
         * 
         * @return the instance of {@link ClassicLandContract}
         * @throws IllegalArgumentException
         *             - if one of the argument in the builder is null or zero
         */
        public ClassicLandContract build() throws IllegalArgumentException {
            if (this.landBuil == null || this.landBuildCost == 0 || this.landBuildIncome == 0
                    || this.landBuildingsCost == 0) {
                throw new IllegalArgumentException();
            }
            return new ClassicLandContract(this.landBuil, this.landBuildCost, this.landBuildIncome,
                    this.landBuildingsCost);
        }
    }
}
