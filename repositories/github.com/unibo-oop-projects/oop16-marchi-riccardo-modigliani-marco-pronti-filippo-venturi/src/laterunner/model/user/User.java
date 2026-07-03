package laterunner.model.user;

import java.util.Optional;

import laterunner.model.shop.Shop;

/**
 * Game user.
 */
public final class User {

    private static final int INITIAL_USER_LIFE = 3;
    private static final int STARTING_MONEY = 1000;
    private static final int STARTING_LEVEL = 1;
    private static final double STARTING_SPEEDMUL = 1.0;
    private static final User SINGLETON = new User();

    private int money;
    private int levelReached;
    private double speedMul;
    private int userLives;

    private User() {
        this.money = STARTING_MONEY;
        this.levelReached = STARTING_LEVEL;
        this.speedMul = STARTING_SPEEDMUL;
        this.userLives = INITIAL_USER_LIFE;
    }

    /**
     * Returns the only class' instance.
     * 
     * @return
     *          user's instance
     */
    public static User getUser() {
        return SINGLETON;
    }

    /**
     * Returns user's money.
     * 
     * @return
     *          user's money
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Sets user's money amount.
     * 
     * @param amount
     *          new user's money amount
     */
    public void setMoney(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        } else {
            this.money = amount;
        }
    }

    /**
     * Returns user's level reached.
     * 
     * @return
     *          user's level reached
     */
    public int getLevelReached() {
        return this.levelReached;
    }

    /**
     * Sets user's level reached.
     * 
     * @param lvlReached
     *          new user's level reached
     */
    public void setLevelReached(final int lvlReached) {
        if (!(lvlReached >= 1)) {
            throw new IllegalArgumentException();
        } else {
            this.levelReached = lvlReached;
        }
    }

    /**
     * Returns user's multiplier.
     * 
     * @return
     *          user's speed multiplier
     */
    public double getSpeedMul() {
        return this.speedMul;
    }

    /**
     * Sets user's speed multiplier.
     * 
     * @param newMul
     *          new user's speed multiplier(1.0, 1.5 or 2.0)
     */
    public void setSpeedMul(final double newMul) {
        if (newMul != STARTING_SPEEDMUL && newMul != STARTING_SPEEDMUL + 0.5
                && newMul != STARTING_SPEEDMUL + 1.0) {
            throw new IllegalArgumentException();
        } else {
            this.speedMul = newMul;
        }
    }

    /**
     * Returns user's lives.
     * 
     * @return
     *          user's lives
     */
    public int getUserLives() {
        return this.userLives;
    }

    /**
     * Sets user's lives.
     * 
     * @param lives
     *          new user's lives
     */
    public void setUserLives(final int lives) {
        if (lives < 0) {
            this.userLives = 0;
        } else {
            this.userLives = lives;
        }
    }

    /**
     * Resets user's fields and shop's references.
     */
    public void reset() {
        this.money = STARTING_MONEY;
        this.levelReached = STARTING_LEVEL;
        this.speedMul = STARTING_SPEEDMUL;
        this.userLives = INITIAL_USER_LIFE;
        Shop.getShop().reset();
    }

    /**
     * User's statistic.
     */
    public static final class Statistic {

        private static Optional<Statistic> singleton = Optional.empty();

        private long motorbikeHits = 0;
        private long obstacleCarHits = 0;
        private long truckHits = 0;
        private long gamesPlayed = 0;
        private long lostLives = 0;
        private long survivalHighscore = 0;

        private Statistic() { }

        /**
         * Resets user's statistic.
         */
        public void resetStats() {
            this.motorbikeHits = 0;
            this.obstacleCarHits = 0;
            this.truckHits = 0;
            this.gamesPlayed = 0;
            this.lostLives = 0;
        }

        /**
         * Returns the only class' instance.
         * 
         * @return
         *          user's statistic instance.
         */
        public static Statistic getStatistic() {
            if (!singleton.isPresent()) {
                singleton = Optional.of(new Statistic());
            }
            return singleton.get();
        }

        /**
         * Returns motorbike hits' number.
         * 
         * @return
         *          motorbike hits' number
         */
        public long getMotorbikeHits() {
            return this.motorbikeHits;
        }

        /**
         * Sets new motorbike hits' number.
         * 
         * @param mtrHits
         *          new motorbike hits' number
         */
        public void setMotorbikeHits(final long mtrHits) {
            if (mtrHits < this.motorbikeHits) {
                throw new IllegalStateException();
            } else {
                this.motorbikeHits = mtrHits;
            }
        }

        /**
         * Returns obstacle car hits' number.
         * 
         * @return
         *          obstacle car hits' number
         */
        public long getObstacleCarHits() {
            return this.obstacleCarHits;
        }

        /**
         * Sets new obstacle car hits' number.
         * 
         * @param obtCarHits
         *          new obstacle car hits' number
         */
        public void setObstacleCarHits(final long obtCarHits) {
            if (obtCarHits < this.obstacleCarHits) {
                throw new IllegalStateException();
            } else {
                this.obstacleCarHits = obtCarHits;
            }
        }

        /**
         * Returns truck hits' number.
         * 
         * @return
         *          truck hits' number
         */
        public long getTruckHits() {
            return this.truckHits;
        }

        /**
         * Sets new truck hits' number.
         * 
         * @param trcHits
         *          new truck hits' number
         */
        public void setTruckHits(final long trcHits) {
            if (trcHits < this.truckHits) {
                throw new IllegalStateException();
            } else {
                this.truckHits = trcHits;
            }
        }

        /**
         * Returns games played number.
         * 
         * @return
         *          games played number
         */
        public long getGamesPlayed() {
            return this.gamesPlayed;
        }

        /**
         * Sets new games played number.
         * 
         * @param gmsPlayed
         *          new games played number
         */
        public void setGamesPlayed(final long gmsPlayed) {
            if (gmsPlayed < this.gamesPlayed) {
                throw new IllegalStateException();
            } else {
                this.gamesPlayed = gmsPlayed;
            }
        }

        /**
         * Returns lost lives' number.
         * 
         * @return
         *          lost lives' number
         */
        public long getLostLives() {
            return this.lostLives;
        }

        /**
         * Sets lost lives' number.
         * 
         * @param lstLives
         *          new lost lives' number
         */
        public void setLostLives(final long lstLives) {
            if (lstLives < this.lostLives) {
                throw new IllegalStateException();
            } else {
                this.lostLives = lstLives;
            }
        }

        /**
         * Returns survival highscore.
         * 
         * @return
         *          survival highscore number
         */
        public long getSurvivalHighscore() {
            return this.survivalHighscore;
        }

        /**
         * Sets survival highscore number.
         * 
         * @param highscore
         *          new survival highscore number
         */
        public void setSurvivalHighscore(final long highscore) {
            if (highscore < this.survivalHighscore) {
                throw new IllegalStateException();
            } else {
                this.survivalHighscore = highscore;
            }
        }
    }
}
