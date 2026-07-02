package controller.scene;

import controller.sound.SoundController;
import javafx.stage.Stage;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import view.PersonalViews;
import view.SceneLoader;
/**
 * This interface creates a standards rules for controller in main menu.*/
public interface FXMLMenuController {

    /**
     * This method allows to set all listeners for the view components.
     */
    void loadListener();

    /**
     * This method allows to set the fonts for the view components.
     */
    void loadFont();

    /**
     * This method allows to set the animations for the view components.
     */
    void loadAnimation();

    /**
     * This method allows to switch the current scene whit the next scene,
     * on the current stage.
     * @param currentStage - use to set the stage where the scene change.
     * @param scene - use to set the next scene.
     * @param style - use to set the style for the next scene.
     * @param width - use to set the width for next scene.
     * @param height - use to set the height for next scene.
     * @param resizable - use to understand if the next scene will be resizable or not.
     */
    static void switchScene(Stage currentStage, PersonalViews scene, PersonalStyle style, 
                            double width, double height, boolean resizable) {
        //Switch Scene
        SceneLoader.switchScene(currentStage, 
        scene.getURL(), 
        scene.getTitleScene(), 
        width, 
        height,
        style.getStylePath());
        currentStage.setResizable(resizable);
        //Use to play sound when change scene
        SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL());
    }

    /**
     *  This method allows to resize all components of the view.
     */
    void resizable();
}
