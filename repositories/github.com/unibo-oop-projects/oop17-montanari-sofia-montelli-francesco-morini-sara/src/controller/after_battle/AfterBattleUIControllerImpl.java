package controller.after_battle;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.AbstractController;
import model.data_access.FileDataAccessManager;
import model.data_access.FileDataAccessManagerImpl;
import model.statistic.Statistic;
import model.statistic.StatisticMatch;
import model.statistic.StatisticMatchManager;
import model.statistic.statistics.PercentageStatistic;
import model.statistic.statistics.ShotPerformedCountStatistic;
import model.statistic.statistics.ShotPerformedHitCountStatistic;
import model.statistic.statistics.ShotReceivedCountStatistic;
import model.statistic.statistics.ShotReceivedHitCountStatistic;
import model.statistic.statistics.WinnerStatistic;
/**
 * Class implementation of {@AfterBattleUIController}.
 */
public final class AfterBattleUIControllerImpl extends AbstractController implements AfterBattleUIController {
    /**
     * Field for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(FileDataAccessManagerImpl.class.getName());
    /**
     * Statistic match manager of a match.
     */
    private final StatisticMatchManager manager;
    /**
     * @param manager statistic of this a match 
     */
    public AfterBattleUIControllerImpl(final StatisticMatchManager manager) {
        super();
        if (manager == null) {
            throw new IllegalArgumentException("manager");
        }
        this.manager = manager;
        setWinners(manager);
        saveOnDatabase(manager);
    }

    private void setWinners(final StatisticMatchManager manager) {
        final boolean check = (manager.getPlayerOneStatisticMatch().getShotPerformedHitCount()) > (manager.getPlayerTwoStatisticMatch().getShotPerformedHitCount());
        manager.getPlayerOneStatisticMatch().setWinner(check);
        manager.getPlayerTwoStatisticMatch().setWinner(!check);
    }

    private void saveOnDatabase(final StatisticMatchManager manager) {
        try {

            final FileDataAccessManager<StatisticMatch> database = new FileDataAccessManagerImpl<StatisticMatch>(getModel().getStatisticsPath());

            if (!manager.getPlayerOneStatisticMatch().getPlayerName().equalsIgnoreCase("Anonymous")) {
                database.saveNewElement(manager.getPlayerOneStatisticMatch());
            }
            if (!manager.getPlayerTwoStatisticMatch().getPlayerName().equalsIgnoreCase("Anonymous")) {
                database.saveNewElement(manager.getPlayerTwoStatisticMatch());
            }
        } catch (Exception e) {
            LOGGER.trace("Could not save statistics on database");
        }
    }

    @Override
    public String getPlayerOneName() {
        return manager.getPlayerOneStatisticMatch().getPlayerName();
    }

    @Override
    public String getPlayerTwoName() {
        return manager.getPlayerTwoStatisticMatch().getPlayerName();
    }

    @Override
    public List<Statistic<?>> getPlayerOneStatistics() {
        return getStatistics(manager.getPlayerOneStatisticMatch());
    }

    @Override
    public List<Statistic<?>> getPlayerTwoStatistics() {
        return getStatistics(manager.getPlayerTwoStatisticMatch());
    }

    private List<Statistic<?>> getStatistics(final StatisticMatch match) {
        final List<Statistic<?>> result = new ArrayList<>();

        final Statistic<Integer> shotPerformed = new ShotPerformedCountStatistic(match);
        final Statistic<Integer> shotHitPerformed = new ShotPerformedHitCountStatistic(match);

        result.add(new WinnerStatistic(match));
        result.add(shotPerformed);
        result.add(shotHitPerformed);
        result.add(new ShotReceivedCountStatistic(match));
        result.add(new ShotReceivedHitCountStatistic(match));
        result.add(new PercentageStatistic(shotHitPerformed, shotPerformed, "Precision shots (PERCENTAGE)"));

        return result;
    }
}
