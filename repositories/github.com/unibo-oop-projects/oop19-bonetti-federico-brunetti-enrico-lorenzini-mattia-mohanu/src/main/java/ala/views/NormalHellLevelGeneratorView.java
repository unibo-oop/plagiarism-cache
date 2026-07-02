package ala.views;

import ala.models.NormalHellLevelGeneratorModel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * NormalHellLevelGeneratorView class.
 * 
 */
public class NormalHellLevelGeneratorView extends LevelGeneratorPatternView {

    //Attributes:
    private NormalHellLevelGeneratorModel normalHellLevelgeneratorModel;

    //private BossBackgroundView bossBackgroundView; da mettere nel boss
    private HellLevelBackgroundView hellLevelBackgroundView; //Da implementare. sar� identica al background del boss ma si 
                                                             //potr� muovere quindi sara dinamica in vosta dello scorrimento del livello.
    /**
     * Constructor.
     * 
     * @param sceneRoot
     * @param gameLevelScene
     * @param stage
     * @param menuScene
     * @param normalHellLevelGeneratorModel
     * 
     */
    public NormalHellLevelGeneratorView(final Pane sceneRoot, final Scene gameLevelScene, final Stage stage, final Scene menuScene, final NormalHellLevelGeneratorModel normalHellLevelGeneratorModel) {
        super(sceneRoot, gameLevelScene, stage, menuScene, normalHellLevelGeneratorModel);

        this.normalHellLevelgeneratorModel = normalHellLevelGeneratorModel;
        //Set up Background components:
        hellLevelBackgroundView = new HellLevelBackgroundView(getbackgroundLayer(), normalHellLevelGeneratorModel.getBackgroundPatternModel());

    }

    //getters&Setters:
    public final NormalHellLevelGeneratorModel getNormalHellLevelgeneratorModel() {
        return normalHellLevelgeneratorModel;
    }

    public final void setNormalHellLevelgeneratorModel(final NormalHellLevelGeneratorModel normalHellLevelgeneratorModel) {
        this.normalHellLevelgeneratorModel = normalHellLevelgeneratorModel;
    }

    public final HellLevelBackgroundView getHellLevelBackgroundView() {
        return hellLevelBackgroundView;
    }

    public final void setHellLevelBackgroundView(final HellLevelBackgroundView hellLevelBackgroundView) {
        this.hellLevelBackgroundView = hellLevelBackgroundView;
    }

}
