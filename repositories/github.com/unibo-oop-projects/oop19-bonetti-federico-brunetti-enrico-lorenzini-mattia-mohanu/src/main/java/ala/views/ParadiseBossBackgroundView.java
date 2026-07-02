package ala.views;

import ala.models.BackgroundPatternModel;
import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * ParadiseBossBackgroundView class.
 * 
 */
public class ParadiseBossBackgroundView extends GameObjectView {

    private static final Image PARADISE_BOSS_BACKGROUND = new Image(ParadiseBossBackgroundView.class.getResource("/backgroundSky.png").toExternalForm());

    private BackgroundPatternModel backgroundPatternModel;
    /**
     * Constructor.
     * 
     * @param backgroundLayer
     * @param gameObjectModel
     * 
     */
    public ParadiseBossBackgroundView(final Pane backgroundLayer, final GameObjectModel gameObjectModel) {
        super(backgroundLayer, PARADISE_BOSS_BACKGROUND, gameObjectModel);
    }

    //Getters&Setters:
    public final BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public final void setBackgroundPatternModel(final BackgroundPatternModel backgroundPatternModel) {
        this.backgroundPatternModel = backgroundPatternModel;
    }
}
