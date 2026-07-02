package ala.views;

import ala.models.BackgroundPatternModel;
import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * PurgatoryBossBackgroundView class.
 * 
 */
public class PurgatoryBossBackgroundView extends GameObjectView {
private static final Image PURGATORY_BACKGROUND = new Image(PurgatoryBossBackgroundView.class.getResource("/PurgatoryBackground.png").toExternalForm());

    private BackgroundPatternModel backgroundPatternModel;
    /**
     * Constructor.
     * 
     * @param backgroundLayer
     * @param gameObjectModel
     */
    public PurgatoryBossBackgroundView(final Pane backgroundLayer, final GameObjectModel gameObjectModel) {
        super(backgroundLayer, PURGATORY_BACKGROUND, gameObjectModel);
    }

    //Getters&Setters:
    public final  BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public final void setBackgroundPatternModel(final BackgroundPatternModel backgroundPatternModel) {
        this.backgroundPatternModel = backgroundPatternModel;
    }
}
