package view.controllers;

import model.score.Progress;

/** 
 * This interface represents the common parts of the handle data scene.
 *
 */
public interface FxHandleDataScene extends FxController {

    /**
     * Initialize the scene to show.
     * @param progress
     *             user's progress
     */
    void init(Progress progress);

}
