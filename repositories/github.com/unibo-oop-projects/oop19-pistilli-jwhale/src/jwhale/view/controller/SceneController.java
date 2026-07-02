package jwhale.view.controller;

import jwhale.controller.Controller;
import jwhale.view.View;
/**
 * Scene controller interface.
 */
public interface SceneController {
    /**
     * Set controller instance.
     * @param controller
     *          controller instance
     */
    void setController(Controller controller);
    /**
     * Set view instance.
     * @param view
     *          view instance.
     */
    void setView(View view);
}
