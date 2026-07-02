package view.scene;

/**
* This interface is used to switch Scenes on the active Stage.
*/
public interface SceneLoader {

    /**
     * This method switches the active Scene to the one passed as parameter.
     * @param nextScene - the name of the Scene you want to load.
     */
    void switchScene(SceneName nextScene);

}
