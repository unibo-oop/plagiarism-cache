package ala.controllers;

import ala.models.CerberoImportantHeadModel;
import ala.views.CerberoBodyPartView;

/**
 * Cerbero's important Head controller class.
 * 
 */
public final class CerberoImportantHeadController extends GameObjectAliveController {
    //Attributes:
    private CerberoImportantHeadModel importantHeadModel;
    private CerberoBodyPartView importantHeadView;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param importantHeadModel
     * @param importantHeadView
     * 
     */
    public CerberoImportantHeadController(final CerberoImportantHeadModel importantHeadModel,
            final CerberoBodyPartView importantHeadView) {
        super(importantHeadModel, importantHeadView);
        this.importantHeadModel = importantHeadModel;
        this.importantHeadView = importantHeadView;
    }

    //Getters&Setters:
    public CerberoImportantHeadModel getImportantHeadModel() {
        return importantHeadModel;
    }

    public CerberoBodyPartView getImportantHeadView() {
        return importantHeadView;
    }
}
