package ala.views;

import ala.models.BackgroundPatternModel;
import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * HellBossBackgroundView class.
 * 
 */
public final class HellBossBackgroundView extends GameObjectView {

    private static final Image HELL_BACKGROUND = new Image(HellBossBackgroundView.class.getResource("/background.png").toExternalForm());

    private BackgroundPatternModel backgroundPatternModel;

    /**
     * Constructor.
     * 
     * @param backgroundLayer
     * @param gameObjectModel
     * 
     */
    public HellBossBackgroundView(final Pane backgroundLayer, final GameObjectModel gameObjectModel) {
        super(backgroundLayer, HELL_BACKGROUND, gameObjectModel);
    }

    //Getters&Setters:
    public BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public void setBackgroundPatternModel(final BackgroundPatternModel backgroundPatternModel) {
        this.backgroundPatternModel = backgroundPatternModel;
    }
}
