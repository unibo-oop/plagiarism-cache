package vg.view.gameOver;

import vg.view.AdaptableView;
import vg.view.utils.CountdownView;

/**
 * Game-over screen where is aksed name of player and is showed round and score reached.
 */
public class GameOverView extends AdaptableView<GameOverViewController> {
    public GameOverView() {
        super("/layout/GameOver.fxml");
    }
}
