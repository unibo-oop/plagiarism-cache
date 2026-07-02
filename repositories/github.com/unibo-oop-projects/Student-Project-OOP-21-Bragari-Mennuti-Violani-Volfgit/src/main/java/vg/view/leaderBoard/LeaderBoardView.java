package vg.view.leaderBoard;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import vg.view.AdaptableView;
import vg.view.gameBoard.GameBoard;

import java.io.IOException;
import java.util.Optional;

public class LeaderBoardView extends AdaptableView<LeaderBoardViewController> {
    public LeaderBoardView() {
        super("/layout/LeaderBoard/LeaderBoard.fxml");
    }

    /**
     * Create list row of leaderboard with position, name, score and round.
     * @param pos Position in chart
     * @param name Name of player
     * @param score Reached score
     * @param round Reached round
     * @return Optional Node If error in loading occurs is empty
     */
    public static Optional<Node> listItem(final int pos, final String name, final int score, final int round) {
        Node item = null;
        FXMLLoader loader = new FXMLLoader(GameBoard.class.getResource("/layout/LeaderBoard/SingleItemList.fxml"));
        try {
            item = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SingleItemListController itemListController = loader.getController();
        itemListController.setValue(pos, name, score, round);
        return Optional.ofNullable(item);
    }
}
