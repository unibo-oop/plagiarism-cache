package ala.views;

import ala.models.BackgroundPatternModel;
import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * ParadiseLEvelBackGroundView class.
 * 
 */
public class ParadiseLevelBackgroundView extends GameObjectView {

    private static final Image PARADISE_LEVEL_BACKGROUND = new Image(ParadiseLevelBackgroundView.class.getResource("/ParadiseNormalBackground.png").toExternalForm());

    private BackgroundPatternModel backgroundPatternModel;

    /**
     * Constructor.
     * 
     * @param layer
     * @param gameObjectModel
     * 
     */
    public ParadiseLevelBackgroundView(final Pane layer, final GameObjectModel gameObjectModel) {
        super(layer, PARADISE_LEVEL_BACKGROUND, gameObjectModel);
    }

    //Getters&Setters:
    public final BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public final void setBackgroundPatternModel(final BackgroundPatternModel backgroundPatternModel) {
        this.backgroundPatternModel = backgroundPatternModel;
    }
}
