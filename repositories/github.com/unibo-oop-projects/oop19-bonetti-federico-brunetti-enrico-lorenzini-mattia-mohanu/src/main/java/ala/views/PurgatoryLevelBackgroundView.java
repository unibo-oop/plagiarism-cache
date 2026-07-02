package ala.views;

import ala.models.BackgroundPatternModel;
import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class PurgatoryLevelBackgroundView extends GameObjectView {
    //Attributes:
    private static final Image PURGATORY_BACKGROUND = new Image(PurgatoryLevelBackgroundView.class.getResource("/PurgatoryNormalBackground.png").toExternalForm());

    private BackgroundPatternModel backgroundPatternModel;

    //Constructor:
    /**
     * PurgatoryLevelBackgroundView class.
     * @param backgroundLayer
     * @param gameObjectModel
     */
    public PurgatoryLevelBackgroundView(final Pane backgroundLayer, final GameObjectModel gameObjectModel) {
        super(backgroundLayer, PURGATORY_BACKGROUND, gameObjectModel);
    }

    //Getters&Setters:
    public final BackgroundPatternModel getBackgroundPatternModel() {
        return backgroundPatternModel;
    }

    public final void setBackgroundPatternModel(final BackgroundPatternModel backgroundPatternModel) {
        this.backgroundPatternModel = backgroundPatternModel;
    }
}
