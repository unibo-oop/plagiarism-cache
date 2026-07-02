package justanotherchessgame.view.main;

import javafx.scene.layout.Pane;
import justanotherchessgame.view.ResizableGraphicComponent;

/**
 * Interface used to create the main menu view.
 */
public interface MainMenuView extends ResizableGraphicComponent {
    /**
     * Function used to create the main menu view.
     * @return the main view.
     */
    Pane createContent();
}
