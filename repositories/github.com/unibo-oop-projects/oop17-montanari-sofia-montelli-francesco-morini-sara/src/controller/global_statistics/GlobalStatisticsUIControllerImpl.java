package controller.global_statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.AbstractController;
import model.data_access.FileDataAccessManagerImpl;
import model.statistic.Statistic;
import model.statistic.StatisticMatch;
import model.statistic.statistics.BestFrequencyWinnerPlayerGlobalStatistic;
import model.statistic.statistics.BestPrecisionPlayerGlobalStatistic;
import model.statistic.statistics.EnumStatistic;
import model.statistic.statistics.LostMatchesCountGlobalStatistic;
import model.statistic.statistics.MatchesCountStatistic;
import model.statistic.statistics.MatchesThisMonthStatistic;
import model.statistic.statistics.MatchesThisYearStatistic;
import model.statistic.statistics.MatchesTodayStatistic;
import model.statistic.statistics.MostFrequentOpponentGlobalStatistic;
import model.statistic.statistics.MostFrequentPlayerGlobalStatistic;
import model.statistic.statistics.MostFrequentPlayerDateTimeGlobalStatistic;
import model.statistic.statistics.MostFrequentPlayerYouWonOrLostGlobalStatistic;
import model.statistic.statistics.PercentageStatistic;
import model.statistic.statistics.ShotPerformedCountGlobalStatistic;
import model.statistic.statistics.ShotPerformedHitCountGlobalStatistic;
import model.statistic.statistics.WonMatchesCountGlobalStatistic;
import model.statistic.statistics.WonMatchesCountStatistic;
/**
 * Class that implements {@link GlobalStatisticsUIController}.
 */
public final class GlobalStatisticsUIControllerImpl extends AbstractController implements GlobalStatisticsUIController {

    /**
     * Field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(FileDataAccessManagerImpl.class.getName());

    /**
     * List of all matches statistic played.
     */
    private final List<StatisticMatch> matches;

    /**
     * Builder of GlobalStatisticsUIContollerImpl.
     */
    public GlobalStatisticsUIControllerImpl() {
        super();
        matches = new FileDataAccessManagerImpl<StatisticMatch>(getModel().getStatisticsPath()).getSet().stream().collect(Collectors.toList());
        LOGGER.trace("Success loading database");
    }

    @Override
    public List<Statistic<?>> getGlobalStatistics() {
        final List<Statistic<?>> result = new ArrayList<>();

        result.add(new MostFrequentPlayerGlobalStatistic(matches));
        result.add(new MostFrequentOpponentGlobalStatistic(matches));
        result.add(new MostFrequentPlayerDateTimeGlobalStatistic(matches, EnumStatistic.TODAY, "Player who has played more today"));
        result.add(new MostFrequentPlayerDateTimeGlobalStatistic(matches, EnumStatistic.MONTH, "Player who has played more this month"));
        result.add(new MostFrequentPlayerDateTimeGlobalStatistic(matches, EnumStatistic.YEAR, "Player who has played more this year"));
        result.add(new LostMatchesCountGlobalStatistic(matches));
        result.add(new WonMatchesCountGlobalStatistic(matches));
        result.add(new BestFrequencyWinnerPlayerGlobalStatistic(matches));
        result.add(new ShotPerformedHitCountGlobalStatistic(matches));
        result.add(new BestPrecisionPlayerGlobalStatistic(matches));

        return result;
    }

    @Override
    public List<Statistic<?>> getPlayerGlobalStatistics(final String playerName) {
        final List<Statistic<?>> result = new ArrayList<>();
        final List<StatisticMatch> playerMatches = matches.stream()
                                                          .filter(statistic -> statistic.getPlayerName().equals(playerName))
                                                          .collect(Collectors.toList());

        if (!playerMatches.isEmpty()) {
            final Statistic<Integer> matchesCount = new MatchesCountStatistic(playerMatches);
            final Statistic<Integer> matchesWonCount = new WonMatchesCountStatistic(playerMatches);
            final Statistic<Integer> shotPerformed = new ShotPerformedCountGlobalStatistic(playerMatches);
            final Statistic<Integer> shotPerformedHit = new ShotPerformedHitCountGlobalStatistic(playerMatches);

            result.add(matchesWonCount);
            result.add(matchesCount);
            result.add(new MatchesTodayStatistic(playerMatches));
            result.add(new MatchesThisMonthStatistic(playerMatches));
            result.add(new MatchesThisYearStatistic(playerMatches));
            result.add(new PercentageStatistic(matchesWonCount, matchesCount, "Victory frequency (PERCENTAGE)"));
            result.add(new MostFrequentOpponentGlobalStatistic(playerMatches));
            result.add(new MostFrequentPlayerYouWonOrLostGlobalStatistic(playerMatches, true, "Player with whom you have won more"));
            result.add(new MostFrequentPlayerYouWonOrLostGlobalStatistic(playerMatches, false, "Player with whom you have lost more"));
            result.add(shotPerformed);
            result.add(shotPerformedHit);
            result.add(new PercentageStatistic(shotPerformedHit, shotPerformed, "Precision shots (PERCENTAGE)"));
        }
        return result;
    }
}
