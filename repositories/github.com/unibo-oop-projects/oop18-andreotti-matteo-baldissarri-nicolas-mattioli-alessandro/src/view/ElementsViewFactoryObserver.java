package view;

import view.elements.BackgroundView;
import view.elements.BonusView;
import view.elements.HawkView;
import view.elements.HeartView;
import view.elements.StuntmanView;
import view.elements.VaseView;
import view.elements.WindowView;

/**
 * Models the view elements creation.
 */
public interface ElementsViewFactoryObserver {

    /**
     * 
     * @return The Stuntman view
     */
    StuntmanView createStuntman();

    /**
     * 
     * @return The window view.
     */
    WindowView createWindow();

    /**
     * 
     * @return game background.
     */
    BackgroundView createBackground();

    /**
     * 
     * @return The life view.
     */
    HeartView createHeart();

    /**
     * 
     * @return the vase view.
     */
    VaseView createVase();

    /**
     * 
     * @return the hawk view.
     */
    HawkView createHawk();

    /**
     * 
     * @return the Bonus view.
     */
    BonusView createBonus();

}
