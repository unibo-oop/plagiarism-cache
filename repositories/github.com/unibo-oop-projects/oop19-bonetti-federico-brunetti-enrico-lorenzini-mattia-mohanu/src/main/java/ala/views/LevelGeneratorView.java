package ala.views;

import ala.models.LuciferBasicModel;

public interface LevelGeneratorView {
    /**
     * Add layers to scene.
     * 
     */
    void addToScene();

    /**
     * Come back to menu scene.
     * 
     */
    void backToMenuScene();

    /**
     * Move Camera fallowing Lucifer realtime position.
     * 
     * @param luciferBasicModel
     * 
     */
    void moveCamera(LuciferBasicModel luciferBasicModel);
}
