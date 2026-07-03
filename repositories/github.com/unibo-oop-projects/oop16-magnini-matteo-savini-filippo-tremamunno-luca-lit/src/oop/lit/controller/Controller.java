package oop.lit.controller;

import oop.lit.controller.base.SelectionAndAction;
import oop.lit.controller.geometry.Transforms;

/**
 * Interface of Controller.
 */
public interface Controller {

    /**
     * @return a GeometryCreator.
     */
    SelectionAndAction getSelectionAndAction();

    /**
     * @return a BaseCreator.
     */
    Transforms getTransforms();
}
