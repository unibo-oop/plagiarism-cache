package model.mission;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import model.Model;
import model.player.Player;
import model.statistic.Statistics;

/**
 * 
 * Implementation of {@link MissionFactory}.
 *
 */
public class MissionFactoryImpl implements MissionFactory {

    private static final int REWARD_AMOUNT = 20;
    private static final int MAX_DISTANCE_MISSION = 1000;
    private static final int MIN_DISTANCE_MISSION = 200;
    private static final int MAX_COINS_TO_COLLECT_MISSION = 50;
    private static final int MIN_COINS_TO_COLLECT_MISSION = 20;
    private static final int MAX_JUMP_NUMBER_MISSION = 50;
    private static final int MIN_JUMP_NUMBER_MISSION = 20;
    private final Statistics statistics;
    private final Player player;
    private final SecureRandom random;

    /**
     * Creates a new MissionFactoryImpl.
     * @param model the {@link Model}.
     */
    public MissionFactoryImpl(final Model model) {
        super();
        this.statistics = model.getStatistics();
        this.player = model.getGameState().getPlayer();
        this.random = new SecureRandom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission createMission() {
        final List<Mission> missions = List.of(this.createCollectedCoinMission(), this.createDistanceMission(), this.createNumberOfJumpMission());
        return missions.stream().skip(random.nextInt(missions.size())).findFirst().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission createCollectedCoinMission() {
        final int goal = this.getGoal(MAX_COINS_TO_COLLECT_MISSION, MIN_COINS_TO_COLLECT_MISSION);
        return this.createGeneralised(x -> x >= goal, () -> this.statistics.getGameCoins(), "Collect " + goal + " coins: ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission createDistanceMission() {
        final int goal = this.getGoal(MAX_DISTANCE_MISSION, MIN_DISTANCE_MISSION);
        return this.createGeneralised(x -> x >= goal, () -> this.statistics.getDistance(), "Reach distance " + goal + ": ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission createNumberOfJumpMission() {
        final int goal = this.getGoal(MAX_JUMP_NUMBER_MISSION, MIN_JUMP_NUMBER_MISSION);
        return this.createGeneralised(x -> x >= goal, () -> this.player.getJumpCounter(), "Jump " + goal + " times: ");
    }

    /**
     * Creates a general {@link Mission}.
     * @param goal a {@link Predicate} that decide when a mission is completed.
     * @param supplier a {@link Supplier} that updates counter value.
     * @param quest a String with a description of the mission.
     * @return a general {@link Mission}.
     */
    private Mission createGeneralised(final Predicate<Integer> goal, final Supplier<Integer> supplier, final String quest) {
        return new Mission() {

            private int counter;

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean isCompleted() {
                return goal.test(this.counter);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void updateCounter() {
                this.counter = supplier.get();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public int getCounter() {
                return this.counter;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public int getReward() {
                return REWARD_AMOUNT;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String toString() {
                return quest + (this.isCompleted() ? " COMPLETED" : " Not completed yet");
            }

        };
    }

    /**
     * Gets a random value for a {@link Mission} goal.
     * @param maxLimit the maximum limit of goal's value.
     * @param minLimit the minimum limit of goal's value. 
     * @return a random value for a {@link Mission} goal.
     */
    private int getGoal(final int maxLimit, final int minLimit) {
        final int goal = (int) (Math.random() * (maxLimit - minLimit) + minLimit);
        return goal - goal % 10;
    }

}
