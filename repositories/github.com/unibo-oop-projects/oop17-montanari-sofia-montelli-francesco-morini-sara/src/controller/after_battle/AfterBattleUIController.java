package controller.after_battle;

import java.util.List;

import model.statistic.Statistic;
/**
 * Interface that represent the controller of After Battle UI.
 * {@link AfterBattleUI}.
 */
public interface AfterBattleUIController {
    /**
     * @return the list of statistics of the first player.
     */
    List<Statistic<?>> getPlayerOneStatistics();
    /**
     * @return the list of statistics of the first player.
     */
    List<Statistic<?>> getPlayerTwoStatistics();
    /**
     * @return the name of first player.
     */
    String getPlayerOneName();
    /**
     * @return the name of the second player.
     */
    String getPlayerTwoName();
}
