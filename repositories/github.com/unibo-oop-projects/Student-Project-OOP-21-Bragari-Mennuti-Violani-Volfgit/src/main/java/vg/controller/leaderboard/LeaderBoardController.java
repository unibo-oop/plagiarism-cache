package vg.controller.leaderboard;

import vg.controller.Controller;
import vg.view.ViewManager;
import vg.view.leaderBoard.LeaderBoardView;
import vg.view.utils.KeyAction;

public class LeaderBoardController extends Controller<LeaderBoardView> {

    private boolean escWasPressed = false;

    public LeaderBoardController(final LeaderBoardView view, final ViewManager viewManager) {
        super(view, viewManager);
    }

    @Override
    public void keyTapped(final KeyAction k) {
    }

    @Override
    public void keyPressed(final KeyAction k) {
        if (k == KeyAction.ENTER && this.escWasPressed) {
            this.getView().getViewController().closeBtnPressing();
        } else if (k == KeyAction.ESCAPE) {
            this.escWasPressed = true;
        }
    }

    @Override
    public void keyReleased(final KeyAction k) {
        if (k == KeyAction.ENTER && this.escWasPressed) {
            this.getViewManager().popView();
        }
    }
}
