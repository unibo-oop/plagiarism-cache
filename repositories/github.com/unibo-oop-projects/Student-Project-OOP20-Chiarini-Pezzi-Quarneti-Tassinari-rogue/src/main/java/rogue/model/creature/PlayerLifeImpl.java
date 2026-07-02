package rogue.model.creature;

import static rogue.model.creature.PlayerAttribute.HP;
import static rogue.model.creature.PlayerAttribute.MAX_HP;
import static rogue.model.creature.PlayerAttribute.COINS;
import static rogue.model.creature.PlayerAttribute.LEVEL;
import static rogue.model.creature.PlayerAttribute.STRENGTH;
import static rogue.model.creature.PlayerAttribute.EXPERIENCE;
import static rogue.model.creature.PlayerAttribute.FOOD;


import java.util.Set;

import javafx.util.Pair;
import rogue.model.events.EventSubscriber;
import rogue.model.events.LifeEvent;

/**
 * An implementation for a {@link PlayerLife}. 
 * Uses the pattern builder in order to create a new instance (even custom).
 */
public final class PlayerLifeImpl extends AbstractLife implements PlayerLife {

    private final LevelIncreaseStrategy levelStrategy;
    private final MaxHpIncreaseStrategy maxHpStrategy;

    private static final int MAX_FOOD = 100; // fixed
    private int maxHealthPoints; // changes dynamically during the game
    private int strength;
    private int leftFood;
    private int level;
    private int coins;

    private PlayerLifeImpl(final LevelIncreaseStrategy levelStrategy, final MaxHpIncreaseStrategy maxHpStrategy, final int healthPoints, 
            final int maxHealthPoints, final int experience, final int strength, final int food, final int level, final int coins) {
        super(healthPoints, experience);
        this.levelStrategy = levelStrategy;
        this.maxHpStrategy = maxHpStrategy;
        this.maxHealthPoints = maxHealthPoints;
        this.strength = strength;
        this.leftFood = food;
        this.level = level;
        this.coins = coins;
    }

    private int checkNotExceeding(final int val, final int max) {
        return val > max ? max : val;
    }

    private void postLifeChange(final PlayerAttribute attribute, final int value) {
        this.post(new LifeEvent<>(this, Set.of(new Pair<>(attribute, value))));
    }

    /**
     * Registers the given subscriber on {@link EventBus} and 
     * post the initial status!
     */
    @Override
    public void register(final EventSubscriber subscriber) {
        super.register(subscriber);
        Set.of(new Pair<>(HP, this.getHealthPoints()), new Pair<>(MAX_HP, this.maxHealthPoints), 
               new Pair<>(COINS, this.coins), new Pair<>(LEVEL, this.level),
               new Pair<>(STRENGTH, this.strength), new Pair<>(EXPERIENCE, this.getExperience()), 
               new Pair<>(FOOD, this.leftFood))
            .forEach(p -> this.postLifeChange(p.getKey(), p.getValue()));
    }

    @Override
    public void hurt(final int damage) {
        super.hurt(damage);
        this.postLifeChange(HP, this.getHealthPoints());
    }

    @Override
    public void powerUp(final int increment) {
        final var newHp = this.getHealthPoints() + increment;
        this.setHealthPoints(this.checkNotExceeding(newHp, this.maxHealthPoints));
        this.postLifeChange(HP, this.getHealthPoints());
    }

    @Override
    public void increaseExperience(final int increment) {
        this.setExperience(this.getExperience() + increment);
        final var newLevel = this.levelStrategy.getLevel(this.getExperience());
        if (this.level != newLevel) {
            this.setLevel(newLevel);
            this.setMaxHealthPoints(this.maxHpStrategy.getMaxHp(this.level));
        }
        this.postLifeChange(EXPERIENCE, this.getExperience());
    }

    @Override
    public void addStrength(final int increment) {
        this.strength = this.strength + increment;
        this.postLifeChange(STRENGTH, this.strength);
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    private void updateFood(final int amount) {
        final var newFood = this.leftFood + amount;
        this.leftFood = this.checkNotNegative(this.checkNotExceeding(newFood, MAX_FOOD));
        this.postLifeChange(FOOD, this.leftFood);
    }

    @Override
    public void increaseFood(final int amount) {
        this.updateFood(amount);
    }

    @Override
    public void decreaseFood(final int amount) {
        this.updateFood(-amount);
    }

    @Override
    public int getFood() {
        return this.leftFood;
    }

    @Override
    public boolean isDead() {
        return super.isDead() || this.leftFood == 0;
    }

    private void updateCoins(final int amount) {
        this.coins = this.checkNotNegative(this.coins + amount);
        this.postLifeChange(COINS, this.coins);
    }

    @Override
    public void addCoins(final int amount) {
        this.updateCoins(amount);
    }

    @Override
    public void subCoins(final int amount) {
        this.updateCoins(-amount);
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    private void setLevel(final int level) {
        this.level = level;
        this.postLifeChange(LEVEL, this.level);
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    private void setMaxHealthPoints(final int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
        this.postLifeChange(MAX_HP, this.maxHealthPoints);
    }

    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

    @Override
    public int getMaxFood() {
        return MAX_FOOD;
    }

    @Override
    public String toString() {
        return "PlayerLifeImpl [maxHealthPoints=" + maxHealthPoints + ", strength="
        + strength + ", leftFood=" + leftFood + ", level=" + level + ", coins=" + coins + ", toString()="
        + super.toString() + "]";
    }

    public static class Builder {

        /**
         * Default values.
         */
        private static final int MAX_HEALTH_POINTS = 12;
        private static final int HEALTH_POINTS = 12;
        private static final int FOOD = 50; 
        private static final int EXPERIENCE = 0;
        private static final int STRENGTH = 16;
        private static final int COINS = 0;
        private static final int LEVEL = 1;

        private LevelIncreaseStrategy levelStrategy = new StandardLevelIncreaseStrategy();
        private MaxHpIncreaseStrategy maxHpStrategy = new StandardMaxHpIncreaseStrategy();

        private int maxHealthPoints = MAX_HEALTH_POINTS;
        private int healthPoints = HEALTH_POINTS;
        private int food = FOOD;
        private int experience = EXPERIENCE;
        private int strength = STRENGTH;
        private int level = LEVEL;
        private int coins = COINS;
        private boolean consumed;

        /**
         * Initialize the level increase strategy.
         * @param levelStrategy
         *      the {@link LevelIncreaseStrategy} to use
         * @return this Builder for chaining
         */
        public Builder initLevelStrategy(final LevelIncreaseStrategy levelStrategy) {
            this.levelStrategy = levelStrategy;
            return this;
        }

        /**
         * Initialize the maximum health points increase strategy.
         * @param maxHpStrategy
         *      the {@link MaxHpIncreaseStrategy} to use
         * @return this Builder for chaining
         */
        public Builder initMaxHpStrategy(final MaxHpIncreaseStrategy maxHpStrategy) {
            this.maxHpStrategy = maxHpStrategy;
            return this;
        }

        /**
         * Initialize the food.
         * @param food
         *      the initial food quantity
         * @return this Builder for chaining
         */
        public Builder initFood(final int food) {
            this.food = food;
            return this;
        }

        /**
         * Initialize the player experience.
         * @param experience
         *      the initial player experience
         * @return this Builder for chaining
         */
        public Builder initExperience(final int experience) {
            this.experience = experience;
            return this;
        }

        /**
         * Initialize the player health points.
         * @param healthPoints
         *      the initial player health points.
         * @return this Builder for chaining
         */
        public Builder initHealthPoints(final int healthPoints) {
            this.healthPoints = healthPoints;
            return this;
        }

        /**
         * Initialize the player maximum health points.
         * @param maxHealthPoints
         *      the max hp value
         * @return this Builder for chaining
         */
        public Builder initMaxHealthPoints(final int maxHealthPoints) {
            this.maxHealthPoints = maxHealthPoints;
            return this;
        }

        /**
         * Initialize the player experience.
         * @param strength
         *      the initial player strength
         * @return this Builder for chaining
         */
        public Builder initStrength(final int strength) {
            this.strength = strength;
            return this;
        }

        /**
         * Initialize the player level.
         * @param level
         *      the initial player level
         * @return this Builder for chaining
         */
        public Builder initLevel(final int level) {
            this.level = level;
            return this;
        }

        /**
         * Initialize the player level.
         * @param coins
         *      the initial amount of player coins
         * @return this Builder for chaining
         */
        public Builder initCoins(final int coins) {
            this.coins = coins;
            return this;
        }

        /**
         * @return a player life 
         */
        public final PlayerLifeImpl build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new PlayerLifeImpl(levelStrategy, maxHpStrategy, healthPoints, 
                    maxHealthPoints, experience, strength, food, level, coins);
        }
    }

}
