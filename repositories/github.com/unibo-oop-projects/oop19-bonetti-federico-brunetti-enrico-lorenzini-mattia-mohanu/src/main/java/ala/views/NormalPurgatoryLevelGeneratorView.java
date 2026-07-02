package ala.views;

import ala.models.NormalPurgatoryLevelGeneratorModel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * NormalPurgatoryLevelGeneratorView class.
 * 
 */
public class NormalPurgatoryLevelGeneratorView extends LevelGeneratorPatternView {

    //Attributes:
    private NormalPurgatoryLevelGeneratorModel normalPurgatoryLevelgeneratorModel;

    //private BossBackgroundView bossBackgroundView; da mettere nel boss
    private PurgatoryLevelBackgroundView purgatoryLevelBackgroundView; //Da implementare. sar� identica al background del boss ma si 
                                                                       //potr� muovere quindi sara dinamica in vosta dello scorrimento del livello.
    /**
     * Constructor.
     * 
     * @param sceneRoot
     * @param gameLevelScene
     * @param stage
     * @param menuScene
     * @param normalPurgatoryLevelGeneratorModel
     * 
     */
    public NormalPurgatoryLevelGeneratorView(final Pane sceneRoot, final Scene gameLevelScene, final Stage stage, final Scene menuScene, final NormalPurgatoryLevelGeneratorModel normalPurgatoryLevelGeneratorModel) {
        super(sceneRoot, gameLevelScene, stage, menuScene, normalPurgatoryLevelGeneratorModel);

        this.normalPurgatoryLevelgeneratorModel = normalPurgatoryLevelGeneratorModel;

        //Set up Background components:
        purgatoryLevelBackgroundView = new PurgatoryLevelBackgroundView(getbackgroundLayer(), normalPurgatoryLevelGeneratorModel.getBackgroundPatternModel());
    }

    //getters&Setters:
    public final NormalPurgatoryLevelGeneratorModel getnormalPurgatoryLevelGeneratorModel() {
        return normalPurgatoryLevelgeneratorModel;
    }

    public final void setnormalPurgatoryLevelGeneratorModel(final NormalPurgatoryLevelGeneratorModel normalPurgatoryLevelGeneratorModel) {
        this.normalPurgatoryLevelgeneratorModel = normalPurgatoryLevelGeneratorModel;
    }


    public final PurgatoryLevelBackgroundView getPurgatoryLevelBackgroundView() {
        return purgatoryLevelBackgroundView;
    }

}
