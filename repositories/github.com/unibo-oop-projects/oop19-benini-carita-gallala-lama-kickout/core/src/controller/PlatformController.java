package controller;

import model.Platform;
import view.StageView;
/**
 * Controls the Platform linking model and view
 */
public class PlatformController {
    private Platform platformModel;
    private StageView platformView;
    /**
     * Links model and view
     * @param platformModel
     * 			The platform's model
     * @param platformView
     * 			The platform's view
     */
    public PlatformController(Platform platformModel, StageView platformView) {
        this.platformModel = platformModel;
        this.platformView = platformView;
        this.platformView.setPlatform(this.platformModel);
    }
    
}
