package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;

/**
 * Implementation of the {@link GoalFactory} interface.
 */
public final class GoalFactoryImpl implements GoalFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Goal createGoal(final CellType type, final int targetAmount) {
        return new GoalImpl(type, targetAmount);
    }

    /**
     * Private inner class implementing the {@link Goal} interface.
     */
    private static class GoalImpl implements Goal {

        private final CellType type;
        private final int targetAmount;
        private int currentAmount;

        GoalImpl(final CellType type, final int targetAmount) {
            this.type = type;
            this.targetAmount = targetAmount;
            this.currentAmount = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public CellType getTargetType() {
            return this.type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTargetAmount() {
            return this.targetAmount;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getCurrentAmount() {
            return this.currentAmount;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addProgress(final int count) {
            if (count < 0) {
                throw new IllegalArgumentException("We cannot add negative progress");
            }
            this.currentAmount += count;

            if (this.currentAmount > this.targetAmount) {
                this.currentAmount = this.targetAmount;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isReached() {
            return this.currentAmount >= this.targetAmount;
        }

    }

}
