package ala.views;

import ala.models.BackgroundPatternModel;
import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * HellLevelBackgroundView class.
 * 
 */
public final class HellLevelBackgroundView extends GameObjectView {

    private static final Image HELL_LEVEL_BACKGROUND = new Image(HellLevelBackgroundView.class.getResource("/HellBackground.png").toExternalForm());

    private BackgroundPatternModel backgroundPatternModel;

    /**
     * Constructor.
     * 
     * @param layer
     * @param gameObjectModel
     * 
     */
    public HellLevelBackgroundView(final Pane layer, final GameObjectModel gameObjectModel) {
        super(layer, HELL_LEVEL_BACKGROUND, gameObjectModel);
    }

    //Getters&Setters:
    public BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public void setBackgroundPatternModel(final BackgroundPatternModel backgroundPatternModel) {
        this.backgroundPatternModel = backgroundPatternModel;
    }

}
