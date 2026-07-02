package view.statistics;
/**
 * 
 *
 * @param <AfterBattleUIController> the controller of AfterBattle view
 */
public interface AfterBattleUI<AfterBattleUIController> extends StatisticUI<AfterBattleUIController> {
    /**
     * show the table with the statistic of player one.
     */
    void showPlayerOne();
    /**
     * show the table with the statistic of player two.
     */
    void showPlayerTwo();
}
