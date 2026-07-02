package view;

import view.elements.BackgroundView;
import view.elements.BonusView;
import view.elements.HawkView;
import view.elements.HeartView;
import view.elements.StuntmanView;
import view.elements.VaseView;
import view.elements.WindowView;

/**
 * Create the game view elements.
 */
public final class ElementsViewFactoryController implements ElementsViewFactoryObserver {

    @Override
    public StuntmanView createStuntman() {
        return new StuntmanView();
    }

    @Override
    public WindowView createWindow() {
        return new WindowView();
    }

    @Override
    public BackgroundView createBackground() {
        return new BackgroundView();
    }

    @Override
    public HeartView createHeart() {
        return new HeartView();
    }

    @Override
    public VaseView createVase() {
        return new VaseView();
    }

    @Override
    public HawkView createHawk() {
        return new HawkView();
    }

    @Override
    public BonusView createBonus() {
        return new BonusView();
    }
}
