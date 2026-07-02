package model.statistic;

import java.time.LocalDateTime;
import model.match.ImmutableMatch;

/**
 * class that manage completely the game.
 *
 */
public final class StatisticMatchManagerImpl implements StatisticMatchManager {

    private final StatisticMatch playerOneStatistic;
    private final StatisticMatch playerTwoStatistic;

    /**
     * 
     * @param matchShow shows me the dynamics of the match.
     */
    public StatisticMatchManagerImpl(final ImmutableMatch matchShow) {
        if (matchShow == null) {
            throw new IllegalArgumentException("modelShow");
        }
        if (!matchShow.getGameDataP1().getPlayer().isPresent() || !matchShow.getGameDataP2().getPlayer().isPresent()) {
            throw new IllegalArgumentException("modelShow player");
        }
        if (!matchShow.getGameDataP1().getBattleGround().isPresent() || !matchShow.getGameDataP2().getBattleGround().isPresent()) {
            throw new IllegalArgumentException("modelShow battleground");
            }
        final LocalDateTime startDate = LocalDateTime.now();
            playerOneStatistic = new StatisticMatchImpl(
                matchShow.getGameDataP1().getPlayer().get().getName(),
                matchShow.getGameDataP2().getPlayer().get().getName(),
                startDate,
                matchShow.getGameDataP2().getBattleGround().get().getShootsHistory(),
                matchShow.getGameDataP1().getBattleGround().get().getShootsHistory()
            );

            playerTwoStatistic = new StatisticMatchImpl(
                matchShow.getGameDataP2().getPlayer().get().getName(),
                matchShow.getGameDataP1().getPlayer().get().getName(),
                startDate,
                matchShow.getGameDataP1().getBattleGround().get().getShootsHistory(),
                matchShow.getGameDataP2().getBattleGround().get().getShootsHistory()
            );

    }

    @Override
    public StatisticMatch getPlayerOneStatisticMatch() {
        return playerOneStatistic;
    }

    @Override
    public StatisticMatch getPlayerTwoStatisticMatch() {
        return playerTwoStatistic;
    }

    @Override
    public void update() {
        playerOneStatistic.update();
        playerTwoStatistic.update();
    }
}
