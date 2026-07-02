package ala.controllers;

import ala.models.ScratchModel;
import ala.views.ScratchView;

/**
 * ScratchController class.
 * 
 */
public class ScratchController extends DynamicGameObjectController {
    //Attributes:
    private ScratchModel scratchModel;
    private ScratchView scratchView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param scratchModel
     * @param scratchView
     * 
     */
    public ScratchController(final ScratchModel scratchModel, final ScratchView scratchView) {
        super(scratchModel, scratchView);
        this.scratchModel = scratchModel;
        this.scratchView = scratchView;
    }

    //Getter&Setters:
    public final ScratchModel getScratchModel() {
        return scratchModel;
    }

    public final ScratchView getScratchView() {
        return scratchView;
    }

}
