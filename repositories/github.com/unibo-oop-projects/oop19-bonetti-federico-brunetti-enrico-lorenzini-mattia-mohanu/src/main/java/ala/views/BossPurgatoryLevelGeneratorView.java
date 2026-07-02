package ala.views;


import ala.models.BossPurgatoryLevelGeneratorModel;
import ala.models.LuciferModel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * BossPurgatoryLevelGeneratorView class.
 * 
 */
public class BossPurgatoryLevelGeneratorView extends NormalPurgatoryLevelGeneratorView {
    //Attributes:
    private static final double HEALTH_BAR_WIDTH = 140;
    private static final double HEALTH_BAR_HEIGHT = 50;

    private Pane bossLayer = new Pane();
    private Pane cainoHealthLayer = new Pane();
    private Pane moonLayer = new Pane();
    private Pane asteroidLayer = new Pane();

    private CainoView cainoView;

    private BossPurgatoryLevelGeneratorModel bossPurgatoryLevelGeneratorModel;

    //Cerbero Model Variables:
    private PurgatoryBossBackgroundView purgatoryBossBackgroundView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param sceneRoot
     * @param levelScene
     * @param stage
     * @param menuScene
     * @param purgatoryGameBossLevelModel
     * 
     */
    public BossPurgatoryLevelGeneratorView(final Pane sceneRoot, final Scene levelScene, final Stage stage, final Scene menuScene, final BossPurgatoryLevelGeneratorModel purgatoryGameBossLevelModel) {
        super(sceneRoot, levelScene, stage, menuScene, purgatoryGameBossLevelModel);

        this.bossPurgatoryLevelGeneratorModel = purgatoryGameBossLevelModel;

        this.purgatoryBossBackgroundView = new PurgatoryBossBackgroundView(getbackgroundLayer(), purgatoryGameBossLevelModel.getBackgroundPatternModel());

        purgatoryBossBackgroundView = new PurgatoryBossBackgroundView(getbackgroundLayer(), purgatoryGameBossLevelModel.getBackgroundPatternModel());

        sceneRoot.getChildren().add(moonLayer);
        sceneRoot.getChildren().add(bossLayer);
        sceneRoot.getChildren().add(cainoHealthLayer);
        sceneRoot.getChildren().add(asteroidLayer);

        Thread father = Thread.currentThread();
        this.cainoView = new CainoView(bossLayer, purgatoryGameBossLevelModel.getCainoModel(), (LuciferModel) purgatoryGameBossLevelModel.getLuciferBasicModel(), new MoonView(moonLayer, purgatoryGameBossLevelModel.getCainoModel().getMoonModel()), new AsteroidView(asteroidLayer, purgatoryGameBossLevelModel.getCainoModel().getAsteroidModel()), new BossHealthView(cainoHealthLayer, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT), father);

    }

    //Getters&Setters:
    public final Pane getbossLayer() {
        return bossLayer;
    }

    public final void setbossLayer(final Pane bossLayer) {
        this.bossLayer = bossLayer;
    }

    public final Pane getcainoHealthLayer() {
        return cainoHealthLayer;
    }

    public final void setcainoHealthLayer(final Pane cainoHealthLayer) {
        this.cainoHealthLayer = cainoHealthLayer;
    }

    public final Pane getasteroidLayer() {
        return asteroidLayer;
    }

    public final void setasteroidLayer(final Pane asteroidLayer) {
        this.asteroidLayer = asteroidLayer;
    }

    public final CainoView getCainoView() {
        return cainoView;
    }

    public final BossPurgatoryLevelGeneratorModel getBossPurgatoryLevelGeneratorModel() {
        return bossPurgatoryLevelGeneratorModel;
    }

    public final PurgatoryBossBackgroundView getPurgatoryBossBackgroundView() {
        return purgatoryBossBackgroundView;
    }
}
