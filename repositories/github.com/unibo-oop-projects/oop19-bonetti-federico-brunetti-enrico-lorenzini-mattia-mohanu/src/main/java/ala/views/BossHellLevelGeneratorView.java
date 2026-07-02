package ala.views;

import ala.models.BossHellLevelGeneratorModel;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * BossHellLevelGeneratorView class.
 * 
 */
public class BossHellLevelGeneratorView extends NormalHellLevelGeneratorView {
    //Attributes:
    private static final double HEALTH_BAR_WIDTH = 140; //these are parameters of the life's rectangle
    private static final double HEALTH_BAR_HEIGHT = 50;

    private BossHellLevelGeneratorModel bossHellLevelGeneratorModel;

    private Pane bossLayer = new Pane(); //Layer that contains all the things related to enemy boss: life bar, attacks and himself.
    private Pane fireballCerberoLayer = new Pane(); //SHOULD USE A SINGLE LAYER
    private Pane firerainCerberoLayer = new Pane();
    private Pane healthCerberoLayer = new Pane();

    private HellBossBackgroundView hellBossBackgroundView;

    //Cerbero Model Variables:
    private CerberoView cerberoView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param sceneRoot
     * @param levelScene
     * @param stage
     * @param menuScene
     * @param bossHellLevelGeneratorModel
     * 
     */
    public BossHellLevelGeneratorView(final Pane sceneRoot, final Scene levelScene, final Stage stage, final Scene menuScene, final BossHellLevelGeneratorModel bossHellLevelGeneratorModel) {
        super(sceneRoot, levelScene, stage, menuScene, bossHellLevelGeneratorModel);

        this.setHellBossBackgroundView(new HellBossBackgroundView(getbackgroundLayer(), bossHellLevelGeneratorModel.getBackgroundPatternModel()));

        //Set up Cerbero Model variables:
        this.bossHellLevelGeneratorModel = bossHellLevelGeneratorModel;

        sceneRoot.getChildren().add(bossLayer);
        sceneRoot.getChildren().add(firerainCerberoLayer);
        sceneRoot.getChildren().add(fireballCerberoLayer);
        sceneRoot.getChildren().add(healthCerberoLayer);

        Thread father = Thread.currentThread();
        this.cerberoView = new CerberoView(bossLayer, this.bossHellLevelGeneratorModel.getCerberoModel(), new FireBallView(fireballCerberoLayer, this.bossHellLevelGeneratorModel.getCerberoModel().getFireBallModel()), new FireRainView(firerainCerberoLayer, this.bossHellLevelGeneratorModel.getCerberoModel().getFireRainModel()), new BossHealthView(healthCerberoLayer, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT), father);
    }

    //Getters&Setters:
    public final Pane getbossLayer() {
        return bossLayer;
    }

    public final void setbossLayer(final Pane bossLayer) {
        this.bossLayer = bossLayer;
    }

    public final Pane getfireballCerberoLayer() {
        return fireballCerberoLayer;
    }

    public final void setfireballCerberoLayer(final Pane fireballCerberoLayer) {
        this.fireballCerberoLayer = fireballCerberoLayer;
    }

    public final Pane getfirerainCerberoLayer() {
        return firerainCerberoLayer;
    }

    public final void setfirerainCerberoLayer(final Pane firerainCerberoLayer) {
        this.firerainCerberoLayer = firerainCerberoLayer;
    }

    public final Pane getHealthCerberoLayer() {
        return healthCerberoLayer;
    }

    public final void setHealthCerberoLayer(final Pane healthCerberoLayer) {
        this.healthCerberoLayer = healthCerberoLayer;
    }

    public final CerberoView getCerberoView() {
        return cerberoView;
    }

    public final void setCerberoView(final CerberoView cerberoView) {
        this.cerberoView = cerberoView;
    }

    public final BossHellLevelGeneratorModel getBossHellLevelGeneratorModel() {
        return bossHellLevelGeneratorModel;
    }

    public final void setBossHellLevelGeneratorModel(final BossHellLevelGeneratorModel bossHellLevelGeneratorModel) {
        this.bossHellLevelGeneratorModel = bossHellLevelGeneratorModel;
    }

    public final HellBossBackgroundView getHellBossBackgroundView() {
        return hellBossBackgroundView;
    }

    public final void setHellBossBackgroundView(final HellBossBackgroundView hellBossBackgroundView) {
        this.hellBossBackgroundView = hellBossBackgroundView;
    }
}
