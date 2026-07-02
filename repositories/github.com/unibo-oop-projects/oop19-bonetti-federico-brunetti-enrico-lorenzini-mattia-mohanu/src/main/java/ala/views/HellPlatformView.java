package ala.views;

import ala.models.PlatformModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * HellPlatformView class.
 * 
 */
public final class HellPlatformView extends GameObjectView {
    //Attributes:
    private static final Image IMG_HELL_PLATFORM = new Image(HellPlatformView.class.getResource("/HellPlatform.gif").toExternalForm());
    private PlatformModel platformModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param platformModel
     * 
     */
    public HellPlatformView(final Pane layer, final PlatformModel platformModel) {
        super(layer, IMG_HELL_PLATFORM, platformModel);
        this.platformModel = platformModel;
    }

    //Getters&Setters:
    public PlatformModel getPlatformModel() {
        return platformModel;
    }

    public void setPlatformModel(final PlatformModel platformModel) {
        this.platformModel = platformModel;
    }
}
