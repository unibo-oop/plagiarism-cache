package ala.controllers;

import ala.models.ArrowModel;

import ala.views.ArrowView;
/**
 * the arrow's controller class.
 * 
 */
public class ArrowController extends DynamicGameObjectController {
    /**
     * Constructor.
     * 
     * @param arrowModel
     * @param arrowView
     * 
     */
    public ArrowController(final ArrowModel arrowModel, final ArrowView arrowView) {
        super(arrowModel, arrowView);
    }
}
