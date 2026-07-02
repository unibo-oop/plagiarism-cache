package ala.views;

import ala.models.NormalParadiseLevelGeneratorModel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * NormalParadiseLevelGeneratorView class.
 * 
 */
public class NormalParadiseLevelGeneratorView extends LevelGeneratorPatternView {

    //Attributes:
    private NormalParadiseLevelGeneratorModel normalParadiseLevelGeneratorModel;

    //private BossBackgroundView bossBackgroundView; da mettere nel boss
    private ParadiseLevelBackgroundView paradiseLevelBackgroundView; //Da implementare. sar� identica al background del boss ma si 
                                                                     //potr� muovere quindi sara dinamica in vosta dello scorrimento del livello.
    /**
     * Constructor.
     * 
     * @param sceneRoot
     * @param gameLevelScene
     * @param stage
     * @param menuScene
     * @param normalParadiseLevelGeneratorModel
     * 
     */
    public NormalParadiseLevelGeneratorView(final Pane sceneRoot, final Scene gameLevelScene, final Stage stage, final Scene menuScene, final NormalParadiseLevelGeneratorModel normalParadiseLevelGeneratorModel) {
        super(sceneRoot, gameLevelScene, stage, menuScene, normalParadiseLevelGeneratorModel);

        this.normalParadiseLevelGeneratorModel = normalParadiseLevelGeneratorModel;
        //Set up Background components:
        paradiseLevelBackgroundView = new ParadiseLevelBackgroundView(getbackgroundLayer(), normalParadiseLevelGeneratorModel.getBackgroundPatternModel());
    }

    //getters&Setters:
    public final NormalParadiseLevelGeneratorModel getNormalParadiseLevelGeneratorModel() {
        return normalParadiseLevelGeneratorModel;
    }

    public final void setNormalParadiseLevelGeneratorModel(final NormalParadiseLevelGeneratorModel normalParadiseLevelGeneratorModel) {
        this.normalParadiseLevelGeneratorModel = normalParadiseLevelGeneratorModel;
    }


    public final ParadiseLevelBackgroundView getParadiseLevelBackgroundView() {
        return paradiseLevelBackgroundView;
    }
}
