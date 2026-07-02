package starcatraz.model;

import java.time.LocalTime;

/**
 * Achievements implementation.
 */
public class AchievementsImpl implements Achievements {

    private final Statistics statistics;

    private static final int TEN_VICTORIES = 10;
    private static final int HUNDERD_VICTORIES = 100;
    private static final int FIFTY_GAME = 50;
    private static final int ROBOT_ATTACK_REJECTED = 10;
    private static final int HALF_DECK = 38;
    private static final LocalTime TWO_MINUTES = LocalTime.parse("00:02:00");
    private static final int SAME_COLOR_CARD = 6;

    /**
     * Constructor for AchievementsImpl.
     * @param statistics: the statistics to calculate achievements upon
     */
    public AchievementsImpl(final Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean isFirstVictoryCompleted() {
        return (statistics.getVictories() == 0 ? false : true);
    }

    @Override
    public boolean isFirstDefeatCompleted() {
        return (statistics.getDefeats() == 0 ? false : true);
    }

    @Override
    public boolean isFirstTenVictoriesCompleted() {
       return (statistics.getVictories() < TEN_VICTORIES ? false : true);
    }

    @Override
    public boolean isFirstHundredVictoriesCompleted() {
        return (statistics.getVictories() < HUNDERD_VICTORIES ? false : true);
    }

    @Override
    public boolean isFiftyGamePlayedCompleted() {
        return (statistics.getPlayedGames() < FIFTY_GAME) ? false : true;
    }

    @Override
    public boolean isWinWithHalfDeckLeftCompleted() {
        return (statistics.getVictoryWithFewestCards() > HALF_DECK) ? false : true;
    }

    @Override
    public boolean isWinInTwoMinutesCompleted() {
        return (statistics.getGameTimeRecord().isAfter(TWO_MINUTES)) ? false : true;
    }

    @Override
    public boolean isSixCardStreakCompleted() {
        return (statistics.getCardStreak() < SAME_COLOR_CARD) ? false : true;
    }

    @Override
    public boolean isRejectTenRobotAttacksCompleted() {
        return (statistics.getDefeatedRobotsCount() < ROBOT_ATTACK_REJECTED) ? false : true;
    }

}
