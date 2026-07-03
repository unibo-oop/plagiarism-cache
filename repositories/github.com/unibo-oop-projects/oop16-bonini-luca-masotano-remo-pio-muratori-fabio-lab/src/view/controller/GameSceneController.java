package view.controller;

/**
 * Specific interface containing game scene additional behaviour.
 */
public interface GameSceneController extends ControllerFXML {

    /**
     * Resize the displayed map.
     * 
     * @param magnify
     *            if true the map sizes will be increased, else the map size will be
     *            setted to default value
     */
    void resizeMap(boolean magnify);
}
