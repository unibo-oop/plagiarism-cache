package vg.view.transition;

import vg.view.AdaptableView;
import vg.view.gameOver.GameOverViewController;
import vg.view.utils.CountdownView;

public class TransitionView extends AdaptableView<TransitionViewController> implements CountdownView<TransitionViewController> {
    public TransitionView() {
        super("/layout/TransitionLevel.fxml");
    }

    @Override
    public void setCountdown(final int time) {
        this.getViewController().setCountdown(time);
    }
}
