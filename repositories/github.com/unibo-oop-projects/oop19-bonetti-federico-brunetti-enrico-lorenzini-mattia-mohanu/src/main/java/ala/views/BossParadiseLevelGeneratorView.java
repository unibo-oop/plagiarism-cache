package ala.views;

import ala.models.BossParadiseLevelGeneratorModel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * BossParadiseLevelGeneratorView class.
 * 
 */
public class BossParadiseLevelGeneratorView extends NormalParadiseLevelGeneratorView {
    //Attributes:
    private static final double HEALTH_BAR_WIDTH = 140; //DEVONO STARENEL MODEL
    private static final double HEALTH_BAR_HEIGHT = 50;

    private Pane bossLayer = new Pane(); //Layer that contains all the things related to enemy boss: life bar, attacks and himself.
    private Pane lightballLayer = new Pane();
    private Pane micheleHealthLayer = new Pane();

    private MicheleView micheleView;

    private BossParadiseLevelGeneratorModel bossParadiseLevelGeneratorModel;

    //private BossBackgroundView bossBackgroundView; da mettere nel boss
    private ParadiseBossBackgroundView paradiseBossBackgroundView;

    //Constructor:
    //Constructor:
    /**
      * Constructor.
      * 
      * @param sceneRoot
      * @param levelScene
      * @param stage
      * @param menuScene
      * @param bossParadiseLevelGeneratorModel
      * 
      */
    public BossParadiseLevelGeneratorView(final Pane sceneRoot, final Scene levelScene, final Stage stage, final Scene menuScene, final BossParadiseLevelGeneratorModel bossParadiseLevelGeneratorModel) {
        super(sceneRoot, levelScene, stage, menuScene, bossParadiseLevelGeneratorModel);

        this.bossParadiseLevelGeneratorModel = bossParadiseLevelGeneratorModel;

        paradiseBossBackgroundView = new ParadiseBossBackgroundView(getbackgroundLayer(), bossParadiseLevelGeneratorModel.getBackgroundPatternModel());

        sceneRoot.getChildren().add(micheleHealthLayer);
        sceneRoot.getChildren().add(bossLayer);
        sceneRoot.getChildren().add(lightballLayer);

        Thread father = Thread.currentThread();
        this.micheleView = new MicheleView(this.bossLayer, this.bossParadiseLevelGeneratorModel.getMicheleModel(), new LightBallView(this.lightballLayer, this.bossParadiseLevelGeneratorModel.getMicheleModel().getLightBallModel()), new BossHealthView(micheleHealthLayer, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT), father);
    }

    //Getters&Setters:
    public final Pane getbossLayer() {
        return bossLayer;
    }

    public final ParadiseBossBackgroundView getParadiseBossBackgroundView() {
        return paradiseBossBackgroundView;
    }


    public final void setParadiseBossBackgroundView(final ParadiseBossBackgroundView paradiseBossBackgroundView) {
        this.paradiseBossBackgroundView = paradiseBossBackgroundView;
    }


    public final void setMicheleView(final MicheleView micheleView) {
        this.micheleView = micheleView;
    }


    public final void setbossLayer(final Pane bossLayer) {
        this.bossLayer = bossLayer;
    }

    public final Pane getmicheleHealthLayer() {
        return micheleHealthLayer;
    }

    public final void setmicheleHealthLayer(final Pane micheleHealthLayer) {
        this.micheleHealthLayer = micheleHealthLayer;
    }

    public final MicheleView getMicheleView() {
        return micheleView;
    }
}
