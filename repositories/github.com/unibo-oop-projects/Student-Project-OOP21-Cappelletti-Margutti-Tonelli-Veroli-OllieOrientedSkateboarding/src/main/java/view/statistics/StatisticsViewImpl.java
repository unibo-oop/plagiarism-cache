package view.statistics;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.player.Player;
import model.statistic.Statistics;

/**
 * 
 * Class that render the statistics during the game. 
 *
 */
public final class StatisticsViewImpl implements StatisticsView {

    private static final int FONT_SIZE = 18;
    private static final int TEXT_XY = 25;

    private final Statistics statistics;
    private final Player player;
    private final Pane pane;

    /**
     * 
     * @param pane the pane where to render the statistics.
     * @param statistics the current statistics of the game. 
     * @param player the current player state. 
     */
    public StatisticsViewImpl(final Pane pane, final Statistics statistics, final Player player) {
        this.statistics = statistics;
        this.player = player;
        this.pane = pane;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        final Text text = new Text("Coin: " + statistics.getGameCoins() + "\nLives: " + player.getLives());
        text.setFont(new Font(FONT_SIZE));
        text.setX(TEXT_XY);
        text.setY(TEXT_XY);
        this.pane.getChildren().add(text);
    }

}
