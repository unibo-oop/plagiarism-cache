package starcatraz.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import starcatraz.model.Statistics;

/**
 * Statistics controller implementation.
 */
public class StatisticsControllerImpl extends StarcatrazController implements Initializable, StatisticsController {

    @FXML
    private AnchorPane statisticsRoot;
    @FXML
    private Text playedGamesText;
    @FXML
    private Text victoriesText;
    @FXML
    private Text defeatsText;
    @FXML
    private Text defeatedRobotsText;
    @FXML
    private Text cardStreakText;
    @FXML
    private Text fewestCardsVictoryText;
    @FXML
    private Text fastestVictoryText;

    /**
     * User statistics.
     */
    private Statistics statistics;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {}

    @Override
    public void closeStatisticsButtonClick() {
        statisticsRoot.setVisible(false);
    }

    @Override
    public void setStatistics(final Statistics statistics) {
        this.statistics = statistics;
        playedGamesText.setText(Integer.toString(this.statistics.getPlayedGames()));
        victoriesText.setText(Integer.toString(this.statistics.getVictories()));
        defeatsText.setText(Integer.toString(this.statistics.getDefeats()));
        defeatedRobotsText.setText(Integer.toString(this.statistics.getDefeatedRobotsCount()));
        cardStreakText.setText(Integer.toString(this.statistics.getCardStreak()));
        fewestCardsVictoryText.setText(Integer.toString(this.statistics.getVictoryWithFewestCards()));
        fastestVictoryText.setText(this.statistics.getGameTimeRecord().toString());
    }
}
