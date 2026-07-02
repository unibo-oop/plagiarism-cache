package ala.views;

import ala.models.PlatformModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * 
 * PurgatoryPlatformView class that implements the graphic functionality of the purgatory platform.
 *
 */
public class PurgatoryPlatformView extends GameObjectView {
    //Attributes:
    private static final Image IMG_PURGATORY_PLATFORM = new Image(HellPlatformView.class.getResource("/PurgatoryPlatform.png").toExternalForm());
    private PlatformModel platformModel;

    //Constructor:
    /**
     * 
     * @param layer
     * @param platformModel
     */
    public PurgatoryPlatformView(final Pane layer, final PlatformModel platformModel) {
        super(layer, IMG_PURGATORY_PLATFORM, platformModel);
        this.platformModel = platformModel;
    }

    //Getters&Setters:
    public final PlatformModel getPlatformModel() {
        return platformModel;
    }

    public final void setPlatformModel(final PlatformModel platformModel) {
        this.platformModel = platformModel;
    }
}
