package view;

import view.elements.BackgroundView;
import view.elements.BonusView;
import view.elements.HawkView;
import view.elements.HeartView;
import view.elements.StuntmanView;
import view.elements.VaseView;
import view.elements.WindowView;

/**
 * Builds and maintains elements of the view.
 */
public final class ElementsViewFactory {

    private final ElementsViewFactoryObserver factoryObserver;
    private StuntmanView stuntman;
    private WindowView window;
    private BackgroundView background;
    private HeartView heart;
    private VaseView vase;
    private HawkView hawk;
    private BonusView bonus;

    /**
     * Instantiate the factory controller and the elements of the view.
     */
    public ElementsViewFactory() {
        this.factoryObserver = new ElementsViewFactoryController();
        this.background();
        this.window();
        this.stuntman();
        this.heart();
        this.vase();
        this.hawk();
        this.bonus();
    }

    /**
     * Initialize the Background.
     */
    private void background() {
        this.background = this.factoryObserver.createBackground();
    }

    /**
     * Initialize the Palace.
     */
    private void window() {
        this.window = this.factoryObserver.createWindow();
    }

    /**
     * Initialize the Stuntman.
     */
    private void stuntman() {
        this.stuntman = this.factoryObserver.createStuntman();
    }

    /**
     * Initialize the heart.
     */
    private void heart() {
        this.heart = this.factoryObserver.createHeart();
    }

    /**
     * Initialize the vase.
     */
    private void vase() {
        this.vase = this.factoryObserver.createVase();
    }

    /**
     * Initialize the hawk.
     */
    private void hawk() {
        this.hawk = this.factoryObserver.createHawk();
    }

    /**
     * Initialize Bonus.
     */
    private void bonus() {
        this.bonus = this.factoryObserver.createBonus();
    }

    /**
     * @return The game background.
     */
    public BackgroundView getBackground() {
        return this.background;
    }

    /**
     * @return The Palace.
     */
    public WindowView getWindow() {
        return this.window;
    }

    /**
     * @return The Stuntman.
     */
    public StuntmanView getStuntman() {
        return this.stuntman;
    }

    /**
     * @return The heart.
     */
    public HeartView getHeart() {
        return this.heart;
    }

    /**
     * @return The vase.
     */
    public VaseView getVase() {
        return this.vase;
    }

    /**
     * @return The hawk.
     */
    public HawkView getHawk() {
        return this.hawk;
    }

    /**
     * @return The bonus.
     */
    public BonusView getBonus() {
        return this.bonus;
    }

}
