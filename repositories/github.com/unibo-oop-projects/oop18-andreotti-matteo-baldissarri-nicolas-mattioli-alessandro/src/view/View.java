package view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


/**
 * 
 * Interface of the Application view.
 *
 */
public interface View {

    /**
     * 
     * @param root The new scene root.
     */
    void setMenu(StackPane root);

    /**
     * 
     * @return
     *          game view root.
     */
    GameView setGameView();

    /**
     * 
     * @return the mainScene.
     */
    Scene getScene();
}
