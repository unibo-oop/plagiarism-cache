package ala.views;

import ala.models.FireBallModel;
import ala.models.ScratchModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 *ScratchView, class that implements the graphic part of the Scratch attack. 
 */
public class ScratchView extends DynamicGameObjectView {
    //Attributes:
    private static final Image IMG_SCRATCH = new Image(FireBallModel.class.getResource("/scratch.gif").toExternalForm());
    private ScratchModel scratchModel;

    //Constructor:
    /**
     * 
     * @param layer
     * @param scratchModel
     */
    public ScratchView(final Pane layer, final ScratchModel scratchModel) {
        super(layer, IMG_SCRATCH, scratchModel);
        this.scratchModel = scratchModel;
    }

    public final ScratchModel getScratchModel() {
        return scratchModel;
    }
}
