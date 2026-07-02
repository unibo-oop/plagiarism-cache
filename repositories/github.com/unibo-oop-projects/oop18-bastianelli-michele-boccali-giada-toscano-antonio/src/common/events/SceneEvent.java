package common.events;

import enumerators.SceneType;

/**
 * Event that contains a SceneType.
 */

public class SceneEvent implements Event {

    private final SceneType scene;

    /**
     * Constructor of a SceneEvent.
     * 
     * @param scene : the scene type.
     */
    public SceneEvent(final SceneType scene) {
        super();
        this.scene = scene;
    }

    /**
     * Returns the SceneType from the event.
     * 
     * @return the SceneType.
     */
    public final SceneType getSceneType() {
        return scene;
    }
}
